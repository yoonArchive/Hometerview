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

    @ApiModelProperty(name = "모집글 목록")
    List<PersonalQuestion> personalQuestions;

    public static PersonalQuestionListRes of(List<PersonalQuestion> personalQuestions, Integer statusCode, String message) {
        PersonalQuestionListRes res = new PersonalQuestionListRes();
        res.setPersonalQuestions(personalQuestions);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
