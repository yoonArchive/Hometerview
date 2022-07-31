package com.ssafy.api.service;

import com.ssafy.api.request.CommonQuestionReq;
import com.ssafy.api.request.QuestionUpdateReq;
import com.ssafy.db.entity.CommonQuestion;
import com.ssafy.db.entity.PersonalQuestion;
import com.ssafy.db.entity.Recruit;

import java.util.List;

public interface CommonQuestionService {


    CommonQuestion registerCommonQuestion(Long userNo, Long stdNo, CommonQuestionReq commonQuestionReq);

    List<CommonQuestion> getList(Long stdNo);

    CommonQuestion getCommonQuestion(Long questionNo);

    void updateCommonQuestion(CommonQuestion commonQuestion, QuestionUpdateReq questionUpdateReq);

    int deleteCommonQuestion(Long questionNo);

    List<CommonQuestion> getFilteredList(Long stdNo, int type);
}
