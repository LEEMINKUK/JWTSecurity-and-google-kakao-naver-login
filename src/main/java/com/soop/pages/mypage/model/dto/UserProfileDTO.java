package com.soop.pages.mypage.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileDTO {

    private Integer userCode;
    private String nickname;
    private String profilePic;
    private String aboutme;
    private List<InterestDTO> interests;

}
