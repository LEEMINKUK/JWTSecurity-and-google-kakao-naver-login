package com.soop.pages.mypage.model.dao;

import com.soop.pages.mypage.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {

    // 로그인한 사용자의 평가 데이터와 평점을 조회
    List<UserRatingDTO> getLoggedInUserRating(int userCode);

    // 사용자의 평가를 저장
    void evaluateUserRating(EvaluateUserRatingDTO newEvaluate);

    // 평가 항목 리스트를 조회
    List<RatingDTO> getRatingList();

    // 사용자가 만든 허니팟 리스트를 조회
    List<MyHoneypotDTO> getMyHoneypotList();

    // 사용자가 참여 중인 허니팟 리스트를 조회
    List<ParticipatingHoneypotDTO> getParticipatingHoneypotList();

    // 사용자가 작성한 댓글 리스트를 조회
    List<MyCommentDTO> getMyComments();

    // 사용자가 작성한 문의사항 리스트를 조회
    List<MyInquiryDTO> getMyInquiry(int userCode);

    // 완료된 허니팟 리스트를 조회
    List<FinishedHoneypotDTO> getFinishedHoneypot();

    // 관심사 리스트를 조회
    List<InterestDTO> getInterest();

    // 사용자 프로필 정보를 조회
    UserProfileDTO getUserProfile(Integer userCode);

    // 사용자 프로필을 업데이트
    void updateUserProfile(Integer userCode, String nickname, String aboutme, String profilePic);

    // 사용자의 관심사를 삭제
    void deleteUserInterests(Integer userCode);

    // 사용자 참조 정보를 조회
    List<RefreshDTO> getUserRef();

    // 사용자 프로필 사진을 업데이트
    void updateProfilePic(ProfilePicUpdateDTO profilePicUpdateDTO);

    // 사용자 프로필 정보를 업데이트
    void updateProfile(UserProfileDTO updateProfile);

    // 사용자의 관심사를 추가
    void insertUserInterests(@Param("userCode") Integer userCode, @Param("interestCodes") List<Integer> interestCodes);

    // 사용자 프로필 사진 파일명을 조회
    String getProfilePicFileName(Integer userCode);

    // 프로필 사진 URL을 업데이트
    void updateProfilePicture(Integer userCode, String profilePicUrl);

    // 특정 허니팟에 대한 사용자의 평가를 조회
    List<UserRatingDTO> getUserRatings(@Param("honeypotCode") int honeypotCode, @Param("raterCode") int raterCode);
}
