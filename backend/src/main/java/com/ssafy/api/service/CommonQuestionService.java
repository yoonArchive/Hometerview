package com.ssafy.api.service;

import com.ssafy.api.request.CommonQuestionReq;
import com.ssafy.db.entity.CommonQuestion;

import java.util.List;

public interface CommonQuestionService {


    void registerCommonQuestion(Long userNo, Long stdNo, CommonQuestionReq commonQuestionReq);

    List<CommonQuestion> getList(Long stdNo);

    CommonQuestion getCommonQuestion(Long questionNo);

    void updateCommonQuestion(CommonQuestion commonQuestion, CommonQuestionReq commonQuestionReq);

    int deleteCommonQuestion(Long questionNo);

    List<CommonQuestion> getFilteredList(Long stdNo, int type);
}
