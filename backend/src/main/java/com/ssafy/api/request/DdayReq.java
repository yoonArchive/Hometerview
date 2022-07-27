package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("DdayRequest")
public class DdayReq {

    @NotEmpty
    @ApiModelProperty(name = "D-day 제목", example = "삼성전자 면접")
    String ddayTitle;

    @NotEmpty
    @ApiModelProperty(name = "D-day 날짜", example = "2022-10-10")
    String ddayDate;

}
