package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicationDTO {

    private int applicationCode; // 참가신청 번호
    private HoneypotDTO honeypotCategory; // 허니팟 코드 포함 정보
    private LinkBeeUserDTO userCategory; // 신청 유저 정보
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date applicationDate; // 신청일자
}
