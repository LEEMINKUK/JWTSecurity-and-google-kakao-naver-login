package com.soop.pages.mypage.model.service;

import com.soop.pages.mypage.model.dao.MyPageMapper;
import com.soop.pages.mypage.model.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MyPageService {
    private final MyPageMapper myPageMapper;
    private static final Logger logger = LoggerFactory.getLogger(MyPageService.class);

    // 생성자 주입을 통해 MyPageMapper를 초기화
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }

    // 로그인한 사용자의 평가 정보를 조회
    public List<UserRatingDTO> getLoggedInUserRating(int userCode) {
        return myPageMapper.getLoggedInUserRating(userCode);
    }

    // 사용자의 평가를 추가
    public EvaluateUserRatingDTO evaluateUserRating(EvaluateUserRatingDTO newEvaluate) {
        myPageMapper.evaluateUserRating(newEvaluate);
        return newEvaluate;
    }

    // 평가 항목 리스트를 조회
    public List<RatingDTO> getRatingList() {
        return myPageMapper.getRatingList();
    }

    // 사용자가 만든 허니팟 리스트를 조회
    public List<MyHoneypotDTO> getMyHoneypotList() {
        return myPageMapper.getMyHoneypotList();
    }

    // 사용자가 참여 중인 허니팟 리스트를 조회
    public List<ParticipatingHoneypotDTO> getParticipatingHoneypotList() {
        return myPageMapper.getParticipatingHoneypotList();
    }

    // 사용자가 작성한 댓글 리스트를 조회
    public List<MyCommentDTO> getMyComments() {
        return myPageMapper.getMyComments();
    }

    // 사용자가 작성한 문의 내역을 조회
    public List<MyInquiryDTO> getMyInquiry(int userCode) {
        return myPageMapper.getMyInquiry(userCode);
    }

    // 완료된 허니팟 리스트를 조회
    public List<FinishedHoneypotDTO> getFinishedHoneypot() {
        return myPageMapper.getFinishedHoneypot();
    }

    // 관심사 리스트를 조회
    public List<InterestDTO> getInterest() {
        return myPageMapper.getInterest();
    }

    // 사용자 프로필 정보를 조회
    public UserProfileDTO getUserProfile(Integer userCode) {
        return myPageMapper.getUserProfile(userCode);
    }

//    @Transactional
//    public void updateUserProfile(Integer userCode, UserProfileUpdateDTO dto) {
//        // 사용자 정보 업데이트
//        myPageMapper.updateUserProfile(userCode, dto.getNickname(), dto.getAboutme(), dto.getProfilePic());
//
//        // 기존 관심사 삭제
//        myPageMapper.deleteUserInterests(userCode);
//
//        // 새 관심사 추가
//        if (dto.getInterests() != null && !dto.getInterests().isEmpty()) {
//            myPageMapper.insertUserInterests(userCode, dto.getInterests());
//        }
//    }

    public List<RefreshDTO> getUserRef() {
        // 사용자 참조 정보를 조회
        return myPageMapper.getUserRef();
    }

    @Value("${file.upload-dir}")
    private String uploadDir;
    public String updateProfilePic(Integer userCode, MultipartFile file) throws IOException {
        // 파일 이름 생성 (UUID와 원본 파일 이름을 조합)
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 업로드 디렉토리 경로 설정 및 디렉토리가 존재하지 않으면 생성
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 새 파일 저장
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 프로필 사진 URL 생성
        String profilePicUrl = "/images/mypage/saveProfilePic/" + fileName;

        // 프로필 사진 정보를 DTO에 설정하고 DB를 업데이트
        ProfilePicUpdateDTO profileUpdateDTO = new ProfilePicUpdateDTO();
        profileUpdateDTO.setUserCode(userCode);
        profileUpdateDTO.setProfilePic(profilePicUrl);
        myPageMapper.updateProfilePic(profileUpdateDTO);

        return profilePicUrl;
    }

    @Transactional
//    public void updateUserProfile(Integer userCode, UserProfileUpdateDTO dto) {
//        // 사용자 정보 업데이트
//        myPageMapper.updateUserProfile(userCode, dto.getNickname(), dto.getAboutme(), dto.getProfilePic());
//
//        // 기존 관심사 삭제
//        myPageMapper.deleteUserInterests(userCode);
//
//        // 새 관심사 추가
//        if (dto.getInterests() != null && !dto.getInterests().isEmpty()) {
//            myPageMapper.insertUserInterests(userCode, dto.getInterests());
//        }
//    }
    public UserProfileDTO updateProfile(Integer userCode, UserProfileDTO dto) {

        // 기존 유저 프로필 정보 가져오기
        UserProfileDTO updateProfile = myPageMapper.getUserProfile(userCode);

        // 수정할 항목들
        updateProfile.setNickname(dto.getNickname());
        updateProfile.setAboutme(dto.getAboutme());
        updateProfile.setInterests(dto.getInterests());

        // 사용자 프로필을 DB에서 업데이트
        myPageMapper.updateProfile(updateProfile);

        // 관심사 업데이트
        myPageMapper.deleteUserInterests(userCode);
        if (dto.getInterests() != null && !dto.getInterests().isEmpty()) {
            List<Integer> interestCodes = dto.getInterests().stream()
                    .map(InterestDTO::getInterestCode)
                    .collect(Collectors.toList());
            myPageMapper.insertUserInterests(userCode, interestCodes);
        }
        // 업데이트된 사용자 프로필 정보를 반환
        return myPageMapper.getUserProfile(userCode);
    }

    public void updateProfilePicture(Integer userCode, String profilePicUrl) {
        // 프로필 사진 URL을 업데이트
        ProfilePicUpdateDTO dto = new ProfilePicUpdateDTO();
        dto.setUserCode(userCode);
        dto.setProfilePic(profilePicUrl);
        myPageMapper.updateProfilePic(dto);
    }

    public List<UserRatingDTO> getUserRatings(int honeypotCode, int raterCode) {
        // 특정 허니팟과 평가자를 기준으로 사용자 평가 리스트를 조회
        return myPageMapper.getUserRatings(honeypotCode, raterCode);
    }
}
