package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("CreateRecruitPostRequest")
public class RecruitReq {

    @ApiModelProperty(name = "모집글 정보 객체")
    RecruitInfoReq recruitInfoReq;

    @ApiModelProperty(name = "Recruit thumbnail")
    MultipartFile multipartFile;

}
