package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UpdateStdEndDatePutRequest")
public class UpdateStdEndDatePutReq {

    @ApiModelProperty(name = "스터디 종료일 변경", example = "스터디 종료일 변경입니다.")
    String newEndDate;
}
