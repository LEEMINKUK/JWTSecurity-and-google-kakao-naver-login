package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HoneypotDTO {

//    private int honeypotCode;               // 허니팟 코드
//    private int interestCode;               // 관심사 코드
//    private String honeypotTitle;           // 제목
//    private String honeypotContent;         // 내용(소개글
//    private int hostCode;                   // 작성자 코드
//    private String poster;                  // 포스터(썸네일)
//    private String region;                  // 지역
//    private int totalMember;                // 총인원
//    private Date regDate;                   // 등록일
//    private Date eventDate;                 // 모임일
//    private Date endDate;                   // 모집종료일
//    private String visibilityStatus;        // 활성화 여부
//    private String closureStatus;           // 마감 여부
//    private int reportCount;                // 신고 횟수

    private int honeypotCode;
    private int interestCode;
    private String honeypotTitle;
    private String honeypotContent;
    private String hostCode;
    private String poster;
    private String region;
    private int totalMember;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date eventDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;
    private String visibilityStatus;
    private String closureStatus;
    private int reportCount;
    private int seqNo;

}
