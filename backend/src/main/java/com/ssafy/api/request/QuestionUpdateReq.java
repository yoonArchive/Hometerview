package com.ssafy.api.request;

import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.QuestionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("QuestionUpdateRequest")
public class QuestionUpdateReq {

    @ValidEnum(enumClass = QuestionType.class)
    @ApiModelProperty(name = "질문 카테고리", example = "PERSONALITY")
    QuestionType questionType;


    @NotEmpty
    @ApiModelProperty(name = "질문 내용", example = "질문 내용입니다.")
    String contents;

}
