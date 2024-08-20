package com.soop.pages.mypage.controller;

import com.soop.pages.honeypot.controller.HoneypotResponseMessage;
import com.soop.pages.mypage.model.dto.*;
import com.soop.pages.mypage.model.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "마이페이지 API", description = "마이페이지 관련 기능을 제공하는 API입니다.")
@RestController
@RequestMapping("/mypage")
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    // 유저평가 조회
    @Operation(summary = "유저 평가 조회", description = "특정 유저의 평가 정보를 조회합니다.")
    @GetMapping("/rating/{userCode}")
    public ResponseEntity<List<UserRatingDTO>> getLoggedInUserRating(@PathVariable int userCode) {
        List<UserRatingDTO> loggedInUserRatingList = myPageService.getLoggedInUserRating(userCode);
        return ResponseEntity.ok(loggedInUserRatingList);
    }

    // 유저평가 중복확인
    @GetMapping("/userrating")
    public ResponseEntity<List<UserRatingDTO>> getUserRatingList(@RequestParam int honeypotCode,
                                                                 @RequestParam int raterCode) {
        List<UserRatingDTO> ratings = myPageService.getUserRatings(honeypotCode, raterCode);
        return ResponseEntity.ok(ratings);
    }

    // 평점 항목 조회
    @Operation(summary = "평점 항목 조회", description = "평점 항목 리스트를 조회합니다.")
    @GetMapping("/rating")
    public ResponseEntity<List<RatingDTO>> getRatingList() {
        List<RatingDTO> ratingList = myPageService.getRatingList();
        return ResponseEntity.ok(ratingList);
    }

    // 유저평점 등록(평가하기)
    @Operation(summary = "유저 평점 등록", description = "새로운 유저 평가를 등록합니다.")
    @PostMapping("/userrating")
    public ResponseEntity<EvaluateUserRatingDTO> evaluateUserRating(@RequestBody EvaluateUserRatingDTO newEvaluate) {
        EvaluateUserRatingDTO evaluateUserRating = myPageService.evaluateUserRating(newEvaluate);
        return ResponseEntity.ok(evaluateUserRating);
    }

    // 내가 만든 허니팟 조회
    @Operation(summary = "내가 만든 허니팟 조회", description = "사용자가 만든 허니팟 목록을 조회합니다.")
    @GetMapping("/myhoneypots")
    public ResponseEntity<List<MyHoneypotDTO>> getMyHoneypotsList() {
        List<MyHoneypotDTO> myHoneypotList = myPageService.getMyHoneypotList();
        return ResponseEntity.ok(myHoneypotList);
    }

    // 참여중인 허니팟 조회
    @Operation(summary = "참여중인 허니팟 조회", description = "사용자가 참여중인 허니팟 목록을 조회합니다.")
    @GetMapping("/participated")
    public ResponseEntity<List<ParticipatingHoneypotDTO>> getParticipatingHoneypotList() {
        List<ParticipatingHoneypotDTO> participatingHoneypotList = myPageService.getParticipatingHoneypotList();
        return ResponseEntity.ok(participatingHoneypotList);
    }

    // 유저 프로필 조회(닉네임, 사진, 자기소개, 관심사)
    @Operation(summary = "유저 프로필 조회", description = "특정 유저의 프로필 정보를 조회합니다.")
    @GetMapping("/{userCode}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Integer userCode) {
//        try {
        UserProfileDTO userProfile = myPageService.getUserProfile(userCode);
        return ResponseEntity.ok(userProfile);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
    }
    // 유저 프로필 수정(닉네임, 사진, 자기소개, 관심사)
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    @PutMapping("/{userCode}")
//    public ResponseEntity<String> updateUserProfile(@PathVariable Integer userCode, @RequestBody UserProfileUpdateDTO dto) {
//        try {
//            myPageService.updateUserProfile(userCode, dto);
//            return ResponseEntity.ok("프로필이 업데이트되었습니다.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("프로필 업데이트에 실패했습니다.");
//        }
//    }
    public ResponseEntity<HoneypotResponseMessage> updateProfile(@PathVariable Integer userCode, @RequestBody UserProfileDTO dto) {
        // 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // 프로필 정보 수정
        UserProfileDTO updateProfile = myPageService.updateProfile(userCode, dto);

        // 응답 데이터 설정
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("updateProflie", updateProfile);

        // ResponseEntity로 응답 반환
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new HoneypotResponseMessage(200, "수정 성공", responseMap));
    }

    // 관심사 항목 조회
    @Operation(summary = "관심사 항목 조회", description = "관심사 항목 리스트를 조회합니다.")
    @GetMapping("/interest")
    public ResponseEntity<List<InterestDTO>> getInterest() {
        List<InterestDTO> interestList = myPageService.getInterest();
        return ResponseEntity.ok(interestList);
    }

    // 댓글 조회
    @Operation(summary = "내 댓글 조회", description = "사용자가 작성한 댓글 목록을 조회합니다.")
    @GetMapping("/mycomment")
    public ResponseEntity<List<MyCommentDTO>> getMyComments() {
        List<MyCommentDTO> myCommentList = myPageService.getMyComments();
        return ResponseEntity.ok(myCommentList);
    }

    // 문의 내역 조회
    @Operation(summary = "문의 내역 조회", description = "사용자의 문의 내역을 조회합니다.")
    @GetMapping("/myinquiry/{userCode}")
    public ResponseEntity<List<MyInquiryDTO>> getMyInquiry(@PathVariable int userCode) {
        List<MyInquiryDTO> myInquiryList = myPageService.getMyInquiry(userCode);
        return ResponseEntity.ok(myInquiryList);
    }

    // 진행완료된 허니팟 조회(유저평가멤버조회)
    @Operation(summary = "진행완료된 허니팟 조회", description = "진행이 완료된 허니팟 목록을 조회합니다.")
    @GetMapping("/finished")
    public ResponseEntity<List<FinishedHoneypotDTO>> getFinishedHoneypot() {
        List<FinishedHoneypotDTO> finishedHoneypotList = myPageService.getFinishedHoneypot();
        return ResponseEntity.ok(finishedHoneypotList);
    }

    // 로그인 유저 리프레쉬 조회
    @Operation(summary = "로그인 유저 리프레시 조회", description = "로그인한 유저의 리프레시 토큰 정보를 조회합니다.")
    @GetMapping("logincheck")
    public ResponseEntity<List<RefreshDTO>> getUserRef() {
        List<RefreshDTO> refreshInfo = myPageService.getUserRef();
        System.out.println("컨트롤러" + refreshInfo);
        return ResponseEntity.ok(refreshInfo);
    }

    // 프로필 사진 수정
//    @PutMapping("/profile-pic/{userCode}")
//    public ResponseEntity<?> updateProfilePic(@PathVariable Integer userCode,
//                                              @RequestParam("file") MultipartFile file) {
//
//        try {
//            String profilePicUrl = myPageService.updateProfilePic(userCode, file);
//            return ResponseEntity.ok(profilePicUrl);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile picture");
//        }
//    }
    @Operation(summary = "프로필 사진 수정", description = "사용자의 프로필 사진을 수정합니다.")
    @PutMapping("/profile-pic/{userCode}")
    public ResponseEntity<?> updateProfilePic(@PathVariable Integer userCode,
                                              @RequestBody Map<String, String> payload) {
        try {
            String profilePicUrl = payload.get("profilePicUrl");
            myPageService.updateProfilePicture(userCode, profilePicUrl);
            return ResponseEntity.ok(profilePicUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile picture");
        }
    }
}
