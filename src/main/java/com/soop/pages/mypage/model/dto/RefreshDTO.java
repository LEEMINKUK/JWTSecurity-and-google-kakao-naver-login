package com.soop.pages.mypage.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefreshDTO {

    private int userCode;
    private String signupPlatform;
    private String refresh;

}
