package com.soop.pages.mypage.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyHoneypotDTO {

    private int hostCode;
    private String nickname;
    private int interestCode;
    private String interestName;
    private int honeypotCode;
    private String honeypotTitle;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date eventDate;
    private String region;
    private int approvedCount;
    private int totalMember;
    private String visibilityStatus;
    private String closureStatus;

}
