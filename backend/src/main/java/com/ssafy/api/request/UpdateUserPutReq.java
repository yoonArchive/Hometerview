package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("UpdateUserPutRequest")
public class UpdateUserPutReq {

    @ApiModelProperty(name = "유저 이름 이메일 데이터 객체")
    UpdateUserProfileReq updateUserProfileReq;

    @ApiModelProperty(name = "User Img")
    MultipartFile multipartFile;

}
