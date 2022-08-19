package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("UpdateUserProfileRequest")
public class UpdateUserProfileReq {

    @NotEmpty
    @ApiModelProperty(name = "updateUserProfileReq.userName")
    String userName;

    @NotEmpty
    @ApiModelProperty(name = "updateUserProfileReq.userEmail")
    String userEmail;

}
