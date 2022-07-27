package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.PersonalQuestion;
import com.ssafy.db.entity.Recruit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("PersonalQuestionListResponse")
public class PersonalQuestionListRes extends BaseResponseBody {

    @ApiModelProperty(name = "개인질문 목록")
    List<PersonalQuestion> personalQuestions;

    @ApiModelProperty(name = "질문 갯수")
    int count;

    public static PersonalQuestionListRes of(List<PersonalQuestion> personalQuestions, int count, Integer statusCode, String message) {
        PersonalQuestionListRes res = new PersonalQuestionListRes();
        res.setPersonalQuestions(personalQuestions);
        res.setCount(count);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
