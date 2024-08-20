package com.soop.pages.honeypot.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterestDTO {
    private int interestCode;       // 관심사(장르) 코드
    private String interestName;    // 관심사(장르) 이름
}
