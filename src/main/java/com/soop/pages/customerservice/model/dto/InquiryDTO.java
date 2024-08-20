package com.soop.pages.customerservice.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InquiryDTO {

    private int inquiryCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private Date inquiryDate;
    private int adminCode;
    private String answerStatus;
    private String answer;
    private Date answerDate;

}
