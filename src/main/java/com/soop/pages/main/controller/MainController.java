package com.soop.pages.main.controller;

import com.soop.pages.main.model.dto.UserInterestDTO;
import com.soop.pages.main.model.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "회원 관심사 정보", description = "회원이 등록한 관심사를 조회하는 API입니다.")
@RestController
@RequestMapping("/main")
public class MainController {

  private final MainService mainService;

  // 생성자 주입을 통해 MainService를 주입
  public MainController(MainService mainService) {
    this.mainService = mainService;
  }

  @Operation(summary = "회원 관심사 조회", description = "회원이 등록한 관심사를 조회합니다.")
  @Parameter(name = "userCode", description = "로그인한 회원 코드 번호", in = ParameterIn.PATH)
  @GetMapping("/interest/{userCode}")
  public ResponseEntity<Map<String, Object>> getInterestList(@PathVariable int userCode) {
    // 응답 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    // 해당 회원의 관심사 리스트 조회
    List<UserInterestDTO> interestList = mainService.selectUserInterestByCode(userCode);

    // 응답 데이터 저장
    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("interestList", interestList);

    // 응답 반환
    return new ResponseEntity<>(responseMap, headers, HttpStatus.OK);
  }
}
