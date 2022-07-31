package com.ssafy.api.request;

import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ConferenceRegisterPostRequest")
public class ConferencePostReq {

    @ApiModelProperty(name="스터디 번호", example="1")
    Long stdNo;

    @ApiModelProperty(name="스터디 이름", example="스터디 이름입니다.")
    String stdName;

    @ApiModelProperty(name="스터디 카테고리", example="COM")
    StdType stdType;

}
