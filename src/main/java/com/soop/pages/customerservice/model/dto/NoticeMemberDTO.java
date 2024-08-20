package com.soop.pages.customerservice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticeMemberDTO {

    private int noticeCode;
    private String category;
    private String title;
    private String content;
    private int userCode;
    private String regDate;

    private MemberDTO memberDTO;
}
