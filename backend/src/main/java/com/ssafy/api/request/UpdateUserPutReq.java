package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UpdateUserPutRequest")
public class UpdateUserPutReq {
    @ApiModelProperty(name = "User Name")
    String userName;

    @ApiModelProperty(name = "User Email")
    String userEmail;

    @ApiModelProperty(name = "User Img")
    String userImg;
}
