package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.CommonQuestion;
import com.ssafy.db.entity.QuestionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("CommonQuestionResponse")
public class CommonQuestionRes extends BaseResponseBody {

    @ApiModelProperty(name = "공통 질문 번호")
    private Long questionNo;

    @ApiModelProperty(name = "질문 내용")
    private String contents;

    @ApiModelProperty(name = "작성자 번호")
    private Long writerNo;

    @ApiModelProperty(name = "카테고리")
    private QuestionType questionType;

    public static CommonQuestionRes of(CommonQuestion commonQuestion, Integer statusCode, String message) {
        CommonQuestionRes res = new CommonQuestionRes();
        res.setQuestionNo(commonQuestion.getQuestionNo());
        res.setContents(commonQuestion.getContents());
        res.setWriterNo(commonQuestion.getWriterNo());
        res.setQuestionType(commonQuestion.getQuestionType());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
