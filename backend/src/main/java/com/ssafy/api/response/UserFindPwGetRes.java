package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindPwGetResponse")
public class UserFindPwGetRes extends BaseResponseBody {
    @ApiModelProperty(name = "User PW")
    String userPw;

    public static UserFindPwGetRes of(Integer statusCode, String message, String userPw) {
        UserFindPwGetRes res = new UserFindPwGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserPw(userPw);
        return res;
    }

}
