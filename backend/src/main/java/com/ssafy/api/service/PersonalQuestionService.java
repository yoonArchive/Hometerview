package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;

public interface PersonalQuestionService {

    void registerQuestion(Long userNo, Long detailNo, QuestionReq questionReq);

}
