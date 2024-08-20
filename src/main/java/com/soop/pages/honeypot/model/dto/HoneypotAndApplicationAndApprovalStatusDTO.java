package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HoneypotAndApplicationAndApprovalStatusDTO {

    // TEST 작성
    private Integer honeypotCode;
    private Integer interestCode;
    private String honeypotTitle;
    private String honeypotContent;
    private Integer userCode;
    private String poster;
    private String region;
    private Integer totalMember;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date regDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date eventDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;
    private String visibilityStatus;
    private String closureStatus;
    private Integer reportCount;
    private Integer seqNo;
    private Integer approvedCount;
    private String hostNickname;
    private String hostProfilePic;
    private String interestName;

}
