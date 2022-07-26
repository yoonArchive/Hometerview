package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;
import com.ssafy.db.entity.PersonalQuestion;

import java.util.List;

public interface PersonalQuestionService {

    void registerQuestion(Long userNo, Long detailNo, QuestionReq questionReq);

    List<PersonalQuestion> getList(Long detailNo);

}
