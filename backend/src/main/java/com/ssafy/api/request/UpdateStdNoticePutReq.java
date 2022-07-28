package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UpdateStdNoticePutRequest")
public class UpdateStdNoticePutReq {

    @ApiModelProperty(name = "스터디 공지사항 변경", example = "스터디 공지사항 변경입니다.")
    String newStdNotice;
}
