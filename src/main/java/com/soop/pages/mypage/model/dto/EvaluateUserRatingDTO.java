package com.soop.pages.mypage.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EvaluateUserRatingDTO {

    private int userRatingCode;     // 평가 코드
    private int honeypotCode;       // 허니팟 코드
    private int raterCode;          // 평가자 코드
    private int rateeCode;          // 피평가자 코드
    private int ratingCode;         // 평가항목 코드

}
