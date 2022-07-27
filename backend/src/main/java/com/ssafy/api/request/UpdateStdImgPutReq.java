package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UpdateStdImgPutRequest")
public class UpdateStdImgPutReq {

    @ApiModelProperty(name = "스터디 이미지 변경", example = "스터디 이미지 변경입니다.")
    String newStdImg;

}
