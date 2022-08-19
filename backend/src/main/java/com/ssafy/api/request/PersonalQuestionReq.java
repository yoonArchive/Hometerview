package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("QuestionRequest")
public class PersonalQuestionReq {

    @NotEmpty
    @ApiModelProperty(name = "질문 내용", example = "질문 내용입니다.")
    String contents;

}
