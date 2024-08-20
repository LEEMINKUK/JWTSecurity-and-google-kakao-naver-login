package com.soop.pages.customerservice.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private int userCode;
    private String nickname;
    private String email;
    private String gender;
    private String profilePic;
    private String aboutMe;
    private String userRole;
    private Date signupDate;
    private String signupPlatform;

}
