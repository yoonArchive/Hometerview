package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
    void createUser(UserRegisterPostReq userRegisterInfo);

    User getByUserId(String userId);

    User getByUserEmail(String userEmail);

    User getByUserNameAndUserEmail(String userName, String userEmail);

    User getByUserNo(Long userNo);

}
