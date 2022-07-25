package com.ssafy.api.service;

import com.ssafy.api.request.UpdateUserPutReq;
import com.ssafy.db.entity.UserType;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserId(userRegisterInfo.getUserId());
        // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
        user.setUserPw(passwordEncoder.encode(userRegisterInfo.getUserPw()));
        user.setUserName(userRegisterInfo.getUserName());
        user.setUserEmail(userRegisterInfo.getUserEmail());
        user.setUserType(UserType.USER);
        userRepository.save(user);
    }

    @Override
    public User getByUserId(String userId) {
        // 디비에 유저 정보 조회 (userId를 통한 조회).
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
    public void updatePassword(User user, String temPw) {
        user.setUserPw(passwordEncoder.encode(temPw));
    }

    @Override
    @Transactional
    public void updateUser(User user, UpdateUserPutReq updateUserPutReq) {
        user.setUserName(updateUserPutReq.getUserName());
        user.setUserEmail(updateUserPutReq.getUserEmail());
        user.setUserImg(updateUserPutReq.getUserImg());
    }

}
