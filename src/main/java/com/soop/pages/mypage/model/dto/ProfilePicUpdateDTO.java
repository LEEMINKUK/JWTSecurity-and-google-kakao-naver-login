package com.soop.pages.mypage.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfilePicUpdateDTO {

    private Integer userCode;
    private String profilePic;

}
