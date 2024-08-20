package com.soop.pages.honeypot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LinkBeeUserDTO {

    private int userCode;           // 유저 코드
    private String nickname;        // 닉네임
    private String email;           // 이메일
    private String gender;          // 성별
    private String profilePic;      // 프로필사진
    private String userRole;        // 권한(회원/관리자)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date signupDate;        // 가입일
    private String signupPlatform;  // 가입 플랫폼

}
