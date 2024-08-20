package com.soop.pages.mypage.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RatingDTO {

    private int ratingCode;         // 평점 항목 코드
    private String ratingName;      // 평점 항목 이름
    private int score;              // 평점
    private String content;         // 평점 항목 내용

}
