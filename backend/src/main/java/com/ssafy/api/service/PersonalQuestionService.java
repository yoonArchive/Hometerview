package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;
import com.ssafy.api.request.QuestionUpdateReq;
import com.ssafy.db.entity.PersonalQuestion;

import java.util.List;

public interface PersonalQuestionService {

    void registerQuestion(Long userNo, Long detailNo, QuestionReq questionReq);

    List<PersonalQuestion> getList(Long detailNo);

    PersonalQuestion getPersonalQuestion(Long questionNo, Long detailNo, Long userNo);

    void updatePersonalQuestion(PersonalQuestion personalQuestion, QuestionUpdateReq questionUpdateReq);

    int deletePersonalQuestion(Long questionNo);

    void updateSavedStatus(PersonalQuestion personalQuestion);

}
