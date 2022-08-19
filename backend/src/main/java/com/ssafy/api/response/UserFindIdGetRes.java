package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindIdGetResponse")
public class UserFindIdGetRes extends BaseResponseBody {
    @ApiModelProperty(name = "User ID")
    String userId;

    public static UserFindIdGetRes of(Integer statusCode, String message, String userId) {
        UserFindIdGetRes res = new UserFindIdGetRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserId(userId);
        return res;
    }

}
