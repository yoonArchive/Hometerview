package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UpdateStdDayPutRequest")
public class UpdateStdDayPutReq {

    @ApiModelProperty(name = "스터디 진행일시 변경", example = "스터디 진행일시 변경입니다.")
    String newStdDay;

}
