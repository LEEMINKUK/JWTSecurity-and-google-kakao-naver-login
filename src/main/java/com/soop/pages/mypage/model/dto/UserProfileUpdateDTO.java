package com.soop.pages.mypage.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileUpdateDTO {

    private String nickname;
    private String aboutme;
    private String profilePic;
    private List<Integer> interests;

}
