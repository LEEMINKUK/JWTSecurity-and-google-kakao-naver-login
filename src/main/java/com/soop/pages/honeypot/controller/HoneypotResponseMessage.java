package com.soop.pages.honeypot.controller;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HoneypotResponseMessage {

    private int httpStatusCode;
    private String message;
    private Map<String, Object> results;

}
