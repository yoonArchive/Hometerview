package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.CommonQuestion;
import com.ssafy.db.entity.PersonalQuestion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("CommonQuestionListResponse")
public class CommonQuestionListRes extends BaseResponseBody {

    @ApiModelProperty(name = "공통질문 목록")
    List<CommonQuestion> commonQuestions;

    public static CommonQuestionListRes of(List<CommonQuestion> commonQuestions, Integer statusCode, String message) {
        CommonQuestionListRes res = new CommonQuestionListRes();
        res.setCommonQuestions(commonQuestions);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
