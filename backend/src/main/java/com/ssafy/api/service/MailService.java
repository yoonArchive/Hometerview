package com.ssafy.api.service;

import com.ssafy.api.dto.Mail;

public interface MailService {

    Mail createMail(String tmpPassword, String userEmail);

    void sendMail(Mail mail);
}
