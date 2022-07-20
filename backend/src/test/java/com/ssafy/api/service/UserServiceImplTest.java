package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        UserRegisterPostReq user = new UserRegisterPostReq();
        user.setUserId("giyoon");
        user.setUserPw("qwert");
        user.setUserEmail("giyoon@naver.com");
        user.setUserName("박기윤");
        userService.createUser(user);
        assertEquals(user.getUserId(), userRepository.findByUserId(user.getUserId()).get().getUserId());
    }

    @Test
    public void 아이디찾기() throws Exception {
        UserRegisterPostReq user = new UserRegisterPostReq();
        user.setUserId("giyoon");
        user.setUserPw("qwert");
        user.setUserEmail("giyoon@naver.com");
        user.setUserName("박기윤");
        userService.createUser(user);
        String userId = userService.getByUserNameAndUserEmail("박기윤", "giyoon@naver.com");
        assertEquals(userId, "giyoon");
    }
    
}