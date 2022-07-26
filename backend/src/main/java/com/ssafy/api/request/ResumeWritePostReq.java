package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("ResumeWritePostRequest")
public class ResumeWritePostReq {

    @NotNull
    @ApiModelProperty(name = "자기소개서번호", example = "1")
    Long resumeNo;

    @NotNull
    @ApiModelProperty(name = "항목번호", example = "1")
    Long itemNo;

    @NotEmpty
    @ApiModelProperty(name = "항목내용", example = "항목내용입니다.")
    String item;

    @NotEmpty
    @ApiModelProperty(name = "답변", example = "답변입니다.")
    String answer;

}
