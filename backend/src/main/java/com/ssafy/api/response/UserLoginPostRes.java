package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import com.ssafy.db.entity.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 유저 로그인 API ([POST] /api/v1/auth) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class UserLoginPostRes extends BaseResponseBody {

    @ApiModelProperty(name = "JWT 인증 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

    @ApiModelProperty(name = "회원 번호")
    Long userNo;

    @ApiModelProperty(name = "유저 타입")
    UserType userType;

    @ApiModelProperty(name = "회원 이름")
    String userName;

    @ApiModelProperty(name = "프로필 사진")
    String userImg;

    public static UserLoginPostRes of(User user, Integer statusCode, String message, String accessToken) {
        UserLoginPostRes res = new UserLoginPostRes();
        res.setStatusCode(statusCode);
        res.setUserNo(user.getUserNo());
        res.setUserName(user.getUserName());
        res.setUserType(user.getUserType());
        res.setUserImg(user.getUserImg());
        res.setMessage(message);
        res.setAccessToken(accessToken);
        return res;
    }

}
