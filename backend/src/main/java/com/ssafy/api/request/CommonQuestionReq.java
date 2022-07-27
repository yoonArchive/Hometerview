package com.ssafy.api.request;

import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.QuestionType;
import com.ssafy.db.entity.ReviewType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("CommonQuestionRequest")
public class CommonQuestionReq {


    @NotEmpty
    @ApiModelProperty(name = "질문 내용", example = "질문 내용입니다.")
    String contents;

    @ValidEnum(enumClass = QuestionType.class)
    @ApiModelProperty(name = "질문 카테고리", example = "JOB")
    QuestionType questionType;

}
