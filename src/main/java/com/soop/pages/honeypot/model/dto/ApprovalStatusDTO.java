package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApprovalStatusDTO {

    private int applicationCode;                    // 승인신청코드
    private ApplicationDTO applicationCategory;     // 참가신청 코드
    private String decisionStatus;                  // 승인여부
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date decisionDate;                      // 결정일자

}
