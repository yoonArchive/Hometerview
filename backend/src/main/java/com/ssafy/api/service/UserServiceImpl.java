package com.ssafy.api.service;

import com.ssafy.api.request.UpdateUserPutReq;
import com.ssafy.db.entity.UserType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.UUID;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    String uploadPath = "C:\\intervenience";

    String uploadFolder = "userProfile";

    /*String uploadFolder = "upload";

    String uploadPath = "/home" + File.separator + "ubuntu" + File.separator + "S05P13A204"
            + File.separator + "backend"
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static";*/

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
    public int updateUser(User user, UpdateUserPutReq updateUserPutReq) {
        String userName = updateUserPutReq.getUpdateUserProfileReq().getUserName();
        String userEmail = updateUserPutReq.getUpdateUserProfileReq().getUserEmail();
        // String userImg = updateUserPutReq.getUserImg();
        try {
            user.updateUser(userName, userEmail);
        } catch (Exception e) {
            return 0;
        }
        int result = updateUserImg(user, updateUserPutReq.getMultipartFile());
        if (result == 0) return 1;
        return 2;
    }

    @Override
    public int updateUserImg(User user, MultipartFile multipartFile) {
        try {
            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();

            String preFileUrl = user.getUserImg();
            File file;
            if (preFileUrl != null) {
                file = new File(uploadPath + File.pathSeparator, preFileUrl);
                if (file.exists()) {
                    file.delete();
                }
            }
            if (multipartFile == null) {
                String fileUrl = "";
                user.updateUserImg(fileUrl);
                return 1;
            } else {
                String fileName = multipartFile.getOriginalFilename();
                String extension = FilenameUtils.getExtension(fileName).toLowerCase();
                UUID uuid = UUID.randomUUID();
                String destFileName = uuid + "." + extension;
                File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + destFileName);
                destFile.getParentFile().mkdirs();
                multipartFile.transferTo(destFile);
                // String fileUrl = "https://i7b105.p.ssafy.io:8080/static/" + uploadFolder + "/" + destFileName;
                String fileUrl = uploadPath + "/" + uploadFolder + "/" + destFileName;
                System.out.println("fileUrl : " + fileUrl);
                user.updateUserImg(fileUrl);
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

}
