package com.ohgiraffers.oauthjwt.entityDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEntity {


    private Long id;

    private String username;
    private String name;

    private String email;

    private String role;
}