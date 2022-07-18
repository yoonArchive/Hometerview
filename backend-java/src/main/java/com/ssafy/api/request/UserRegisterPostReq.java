package com.ssafy.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.db.entity.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * 유저 회원가입 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserRegisterPostRequest")
public class UserRegisterPostReq {

    @ApiModelProperty(name = "유저 ID", example = "your_id")
    String userId;

    @ApiModelProperty(name = "유저 Password", example = "your_password")
    String userPw;

    @ApiModelProperty(name = "유저 Name", example = "김싸피")
    String userName;

    @ApiModelProperty(name = "유저 Email", example = "ssafy@ssafy.com")
    String userEmail;

}
