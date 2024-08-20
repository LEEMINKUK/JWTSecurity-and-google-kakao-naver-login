package com.soop.pages.mypage.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRatingDTO {

    private int userCode;           // 로그인한 유저(피평가자)
    private String nickname;        // 로그인한 유저 닉네임
    private String profilePic;      // 로그인한 유저 프로필 사진
    private String aboutme;         // 로그인한 유저 자기소개
    private int userRatingCode;     // 로그인한 유저를 평가한 평가코드
    private int honeypotCode;       // 로그인한 유저가 있는 허니팟 코드
    private int rateeCode;          // 로그인한 유저를 평가한 유저
    private String rateeNickname;   // 로그인한 유저를 평가한 사람의 닉네임
    private int ratingCode;         // 평가자가 선택한 평점 코드
    private String ratingName;      // 평가자가 선택한 평점항목의 이름(좋았어요! 등)
    private int score;              // 평가자가 선택한 점수
    private String content;         // 평가자가 선택한 평가 내용(멘트)
    private double averageScore;    // 로그인한 유저의 총평점의 평균

}
