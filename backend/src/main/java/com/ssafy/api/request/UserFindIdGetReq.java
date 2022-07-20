package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindIdGetReq")
public class UserFindIdGetReq {
    @ApiModelProperty(name = "유저 Name", example = "your_name")
    String userName;

    @ApiModelProperty(name = "유저 Email", example = "your_email")
    String userEmail;
}
