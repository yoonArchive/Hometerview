package com.ssafy.api.service;

import com.ssafy.api.request.CommonQuestionReq;
import com.ssafy.api.request.QuestionUpdateReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.CommonQuestionRepository;
import com.ssafy.db.repository.CommonQuestionRepositorySupport;
import com.ssafy.db.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonQuestionServiceImpl implements CommonQuestionService {

    private final StudyRepository studyRepository;

    private final CommonQuestionRepository commonQuestionRepository;

    private final CommonQuestionRepositorySupport commonQuestionRepositorySupport;

    @Override
    public void registerCommonQuestion(Long userNo, Long stdNo, CommonQuestionReq commonQuestionReq) {
        Study study = studyRepository.findByStdNo(stdNo).get();
        CommonQuestion commonQuestion = CommonQuestion.builder()
                .study(study)
                .writerNo(userNo)
                .contents(commonQuestionReq.getContents())
                .questionType(commonQuestionReq.getQuestionType())
                .build();
        commonQuestionRepository.save(commonQuestion);
    }

    @Override
    public CommonQuestion getCommonQuestion(Long questionNo) {
        return commonQuestionRepository.findByQuestionNo(questionNo).orElse(null);
    }

    @Override
    public List<CommonQuestion> getList(Long stdNo) {
        return commonQuestionRepositorySupport.findAllCommonQuestionByStdNo(stdNo);
    }

    @Override
    @Transactional
    public void updateCommonQuestion(CommonQuestion commonQuestion, QuestionUpdateReq questionUpdateReq) {
        QuestionType questionType = questionUpdateReq.getQuestionType();
        String content = questionUpdateReq.getContents();
        commonQuestion.updateCommonQuestion(questionType, content);
    }

    @Override
    public int deleteCommonQuestion(Long questionNo) {
        try {
            commonQuestionRepository.findByQuestionNo(questionNo).get();
        } catch (Exception e) {
            return 0;
        }
        commonQuestionRepository.deleteByQuestionNo(questionNo);
        return 1;
    }

    @Override
    public List<CommonQuestion> getFilteredList(Long stdNo, int type) {
        if (type == 1) {
            return commonQuestionRepository.findAll();
        } else if (type == 2) {
            return commonQuestionRepositorySupport.findAllCommonQuestionByStdNoAndQuestionType(stdNo, QuestionType.PERSONALITY);
        } else if (type == 3) {
            return commonQuestionRepositorySupport.findAllCommonQuestionByStdNoAndQuestionType(stdNo, QuestionType.JOB);
        } else {
            return commonQuestionRepositorySupport.findAllCommonQuestionByStdNoAndQuestionType(stdNo, QuestionType.FREE);
        }
    }

}
