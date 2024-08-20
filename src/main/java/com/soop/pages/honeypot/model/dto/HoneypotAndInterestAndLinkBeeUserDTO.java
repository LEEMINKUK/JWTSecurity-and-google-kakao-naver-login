package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class HoneypotAndInterestAndLinkBeeUserDTO {

    private int honeypotCode;               // 허니팟 코드
    private InterestDTO interestCategory;   // 관심사 카테고리
    private String honeypotTitle;           // 제목
    private String honeypotContent;         // 내용(소개글
    private LinkBeeUserDTO hostInfo;        // 작성자 정보
    private String poster;                  // 포스터(썸네일)
    private String region;                  // 지역
    private int totalMember;                // 총인원
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regDate;                   // 등록일
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date eventDate;                 // 모임일
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;                   // 모집종료일
    private String visibilityStatus;        // 활성화 여부
    private String closureStatus;           // 마감 여부
    private int reportCount;                // 신고 횟수
    private int seqNo;                      // 시퀀스 번호
}
