package com.soop.pages.honeypot.model.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {

    private int commentCode;
    private int honeypotCode;
    private int userCode;
    private String content;
    private Date writingTime;
    private Date updateTime;

}
