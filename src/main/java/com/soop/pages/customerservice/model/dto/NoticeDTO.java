package com.soop.pages.customerservice.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticeDTO {

    private int noticeCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private Date regDate;

}
