package com.soop.pages.cultureinfo.controller;

import com.soop.pages.cultureinfo.model.dto.CultureInfoDTO;
import com.soop.pages.cultureinfo.model.service.CultureInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "얼리버드 공연/전시 정보", description = "관리자에서 등록한 얼리버드 공연/전시 정보를 조회하는 API입니다.")
@RestController
@RequestMapping("/cultureinfo")
public class CultureInfoController {

  // CultureInfoService를 사용하여 서비스 레이어와 상호작용
  private final CultureInfoService cultureInfoService;

  // CultureInfoService를 주입받는 생성자
  public CultureInfoController(CultureInfoService cultureInfoService) {
    this.cultureInfoService = cultureInfoService;
  }

  // CultureInfo 전체 리스트 조회
  @Operation(summary = "얼리버드 공연/전시 정보 전체 조회", description = "전체 얼리버드 공연/전시 정보를 조회합니다.")
  @GetMapping("/early")
  public ResponseEntity<Map<String, Object>> getEarlyList() {
    // 응답 헤더 설정 (JSON 형식과 UTF-8 인코딩)
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    // 서비스 레이어를 통해 전체 얼리버드 공연/전시 정보를 조회
    List<CultureInfoDTO> earlyBirdList = cultureInfoService.selectAllCultureInfo(); // 전체 리스트 조회
    Map<String, Object> responseMap = new HashMap<>();

    // 조회된 데이터를 응답 맵에 저장
    responseMap.put("earlyBirdList", earlyBirdList); // 응답 데이터 저장

    // 조회된 데이터와 설정된 헤더를 포함하여 OK 상태로 응답
    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }

  // 얼리버드 공연/전시 정보 상세 조회
  @Operation(summary = "얼리버드 공연/전시 정보 상세 조회", description = "얼리버드 공연/전시 정보 코드 번호를 통해 공연/전시 상세 정보를 조회합니다.")
  @Parameter(name = "earlyCode", description = "얼리버드 공연/전시 정보 코드 번호", in = ParameterIn.PATH)
  @GetMapping("/early/{earlyCode}")
  public ResponseEntity<Map<String, Object>> findEarlyInfoByCode(@PathVariable int earlyCode) {
    // 응답 헤더 설정 (JSON 형식과 UTF-8 인코딩)
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    // 서비스 레이어를 통해 코드에 해당하는 공연/전시 상세 정보 조회
    CultureInfoDTO foundEarlyInfo = cultureInfoService.selectEarlyInfoByEarlyBirdCode(earlyCode);

    // 조회된 상세 정보를 응답 맵에 저장
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("foundEarlyInfo", foundEarlyInfo);

    // 조회된 데이터와 설정된 헤더를 포함하여 200 상태로 응답
    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }

}
