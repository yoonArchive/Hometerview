package com.ssafy.api.service;

import com.ssafy.api.request.UpdateUserPutReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
    void createUser(UserRegisterPostReq userRegisterInfo);

    User getByUserId(String userId);

    User getByUserEmail(String userEmail);

    User getByUserNameAndUserEmail(String userName, String userEmail);

    void deleteUser(String userId);

    User getByUserNameAndUserEmailAndUserId(String userName, String userEmail, String userId);

    void updatePassword(User user, String tmpPw);

    int updateUser(User user, UpdateUserPutReq updateUserPutReq);

    int updateUserImg(User user, MultipartFile multipartFile);
}
