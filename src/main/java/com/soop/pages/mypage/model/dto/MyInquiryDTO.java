package com.soop.pages.mypage.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyInquiryDTO {

    private int inquiryCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date inquiryDate;
    private String answerStatus;
    private String answer;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date answerDate;

}
