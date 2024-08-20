package com.soop.pages.honeypot.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentAndLinkBeeUserDTO {

    private int commentCode;
    private int honeypotCode;
    private LinkBeeUserDTO writerInfo;
    private String content;
    private Date writingTime;
    private Date updateTime;

}
