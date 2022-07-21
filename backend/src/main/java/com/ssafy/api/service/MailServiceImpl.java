package com.ssafy.api.service;

import com.ssafy.api.dto.Mail;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {


    private final JavaMailSender mailSender;

    private static final String title = "[study] 임시 비밀번호 안내 이메일입니다.";
    private static final String message = "안녕하세요. [study] 임시 비밀번호 안내 메일입니다. "
            +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n";
    private static final String fromAddress = "giyoontest@gmail.com";

    @Override
    public Mail createMail(String tmpPw, String userEmail) {

        Mail mail = Mail.builder()
                .toAddress(userEmail)
                .title(title)
                .message(message + tmpPw)
                .fromAddress(fromAddress)
                .build();
        return mail;
    }

    @Override
    public void sendMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getToAddress());
        mailMessage.setSubject(mail.getTitle());
        mailMessage.setText(mail.getMessage());
        mailMessage.setFrom(mail.getFromAddress());
        mailMessage.setReplyTo(mail.getFromAddress());

        mailSender.send(mailMessage);
    }

}
