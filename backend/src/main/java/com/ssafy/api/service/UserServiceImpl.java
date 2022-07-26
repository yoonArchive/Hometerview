package com.ssafy.api.service;

import com.ssafy.api.request.UpdateUserPutReq;
import com.ssafy.db.entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRegisterPostReq userRegisterInfo) {
        User user = User.builder()
                .userId(userRegisterInfo.getUserId())
                .userPw(passwordEncoder.encode(userRegisterInfo.getUserPw()))
                .userName(userRegisterInfo.getUserName())
                .userEmail(userRegisterInfo.getUserEmail())
                .userType(UserType.USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public User getByUserId(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public User getByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElse(null);
    }

    @Override
    public User getByUserNameAndUserEmail(String userName, String userEmail) {
        return userRepository.findByUserNameAndUserEmail(userName, userEmail).orElse(null);
    }

    @Override
    public User getByUserNameAndUserEmailAndUserId(String userName, String userEmail, String userId) {
        return userRepository.findByUserNameAndUserEmailAndUserId(userName, userEmail, userId).orElse(null);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void updatePassword(User user, String newPw) {
        user.updatePw(passwordEncoder.encode(newPw));
    }

    @Override
    @Transactional
    public void updateUser(User user, UpdateUserPutReq updateUserPutReq) {
        String userName = updateUserPutReq.getUserName();
        String userEmail = updateUserPutReq.getUserEmail();
        String userImg = updateUserPutReq.getUserImg();
        user.updateUser(userName, userEmail, userImg);
    }

}
