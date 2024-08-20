package com.soop.pages.customerservice.controller;

import com.soop.pages.customerservice.model.dto.FileDTO;
import com.soop.pages.customerservice.model.dto.InquiryDTO;
import com.soop.pages.customerservice.model.dto.NoticeMemberDTO;
import com.soop.pages.customerservice.model.service.CustomerServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "고객센터", description = "클라이언트용 고객센터 API입니다.")
@RestController
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerServiceService;

    // 고객센터 페이지에 최신순으로 5개의 공지사항을 조회하는 API
    @Operation(summary = "고객센터 페이지 공지사항 조회", description = "고객센터 페이지에 들어갈 공지사항을 최신순으로 5개 조회합니다.")
    @GetMapping("/help")
    public ResponseEntity<Map<String, Object>> csMainNoticeList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 공지사항 리스트 조회
        List<NoticeMemberDTO> mainNoticeList = customerServiceService.csMainNoticeList();

        Map<String, Object> result = new HashMap<>();
        result.put("mainNoticeList", mainNoticeList);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    // 전체 공지사항을 조회하는 API
    @Operation(summary = "공지사항 전체 조회", description = "공지사항을 전체 조회합니다.")
    @GetMapping("/notice")
    public ResponseEntity<Map<String, Object>> noticeList() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 공지사항 리스트 조회
        List<NoticeMemberDTO> mainNoticeList = customerServiceService.noticeList();

        Map<String, Object> result = new HashMap<>();
        result.put("mainNoticeList", mainNoticeList);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    // 특정 공지사항의 상세 정보를 조회하는 API
    @Operation(summary = "공지사항 상세 조회", description = "공지사항의 상세 내용을 조회합니다.")
    @Parameter(name = "code", description = "공지사항 번호", in = ParameterIn.PATH)
    @GetMapping("/notice/{code}")
    public ResponseEntity<Map<String, Object>> noticeDetail(@PathVariable("code") String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 공지사항 상세 정보 및 첨부 파일 조회
        NoticeMemberDTO mainNoticeList = customerServiceService.noticeDetail(code);
        FileDTO noticeFile = customerServiceService.noticeFile(code);

        Map<String, Object> result = new HashMap<>();

        result.put("mainNoticeList", mainNoticeList);
        result.put("noticeFile", noticeFile);

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    // 1:1 문의를 등록하는 API
    @Operation(summary = "1:1문의 등록", description = "1:1문의 정보를 전달받아 저장합니다.")
    @Schema(description = "1:1 정보", allowableValues = {"category", "title", "context"})
    @PostMapping("/inquiry")
    public ResponseEntity<?> inquiry(@RequestBody InquiryDTO newInquiry) {

        // 문의 정보를 저장
        customerServiceService.inquiry(newInquiry);

        return ResponseEntity
                .created(URI.create("inquiry"))
                .build();
    }
}
