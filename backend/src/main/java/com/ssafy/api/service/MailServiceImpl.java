package com.ssafy.api.service;

import com.ssafy.api.dto.Mail;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    private static final String fromAddress = "giyoontest@gmail.com";

    @Override
    public Mail createTempPwMail(String tmpPw, String userEmail) {

        Mail mail = Mail.builder()
                .toAddress(userEmail)
                .title("[study] 임시 비밀번호 안내 이메일입니다.")
                .message("안녕하세요. [study] 임시 비밀번호 안내 메일입니다. "
                        +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n" + tmpPw)
                .fromAddress(fromAddress)
                .build();
        return mail;
    }

    @Override
    public Mail createAuthMail(String userEmail){
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);

        Mail mail = Mail.builder()
                .toAddress(userEmail)
                .title("[study] 회원가입 이메일 인증번호입니다.")
                .message("안녕하세요. [study]  회원가입 이메일 인증번호 안내 메일입니다. "
                        +"\n" + "인증번호는 아래와 같습니다."+"\n" + authKey)
                .fromAddress(fromAddress)
                .build();
        // 유효시간 5분 동안 저장
        redisUtil.setDataExpire(authKey, userEmail, 60 * 5L);
        return mail;
    }

    public String checkAuthKey(String authKey){
        return redisUtil.getData(authKey);
    }

    public void deleteAuthKey(String authKey){
        redisUtil.deleteData(authKey);
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

    public String getTmpPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";
        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }
        return pwd;
    }

}
