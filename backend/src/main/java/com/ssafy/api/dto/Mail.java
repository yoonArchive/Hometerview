package com.ssafy.api.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail{

    private String toAddress; // 받는 이메일 주소

    private String title; // 이메일 제목

    private String message; // 이메일 내용

    private String fromAddress; // 보내는 이메일 주소

}