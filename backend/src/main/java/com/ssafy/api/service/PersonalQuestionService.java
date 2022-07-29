package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;
import com.ssafy.api.request.QuestionUpdateReq;
import com.ssafy.db.entity.PersonalQuestion;

import java.util.List;

public interface PersonalQuestionService {

    void registerQuestion(Long userNo, Long stdNo, Long detailNo, QuestionReq questionReq);

    List<PersonalQuestion> getListByDetailNo(Long detailNo);

    List<PersonalQuestion> getList(Long stdNo, Long detailNo);

    PersonalQuestion getPersonalQuestion(Long questionNo, Long detailNo, Long userNo);

    void updatePersonalQuestion(PersonalQuestion personalQuestion, QuestionUpdateReq questionUpdateReq);

    int deletePersonalQuestion(Long questionNo);

    void updateSavedStatus(PersonalQuestion personalQuestion);

    PersonalQuestion validatePersonalQuestion(Long questionNo, Long detailNo, Long userNo);

    List<PersonalQuestion> getSavedPersonalQuestionList(Long resumeNo);

}
