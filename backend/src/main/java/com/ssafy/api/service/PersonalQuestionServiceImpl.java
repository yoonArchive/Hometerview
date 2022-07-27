package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;
import com.ssafy.api.request.QuestionUpdateReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.PersonalQuestionRepository;
import com.ssafy.db.repository.PersonalQuestionRepositorySupport;
import com.ssafy.db.repository.ResumeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalQuestionServiceImpl implements PersonalQuestionService {

    private final PersonalQuestionRepository personalQuestionRepository;

    private final PersonalQuestionRepositorySupport personalQuestionRepositorySupport;

    private final ResumeDetailRepository resumeDetailRepository;

    @Override
    public void registerQuestion(Long userNo, Long detailNo, QuestionReq questionReq) {
        ResumeDetail resumeDetail = resumeDetailRepository.findByDetailNo(detailNo).get();
        PersonalQuestion personalQuestion = PersonalQuestion.builder()
                .resumeDetail(resumeDetail)
                .stdNo(questionReq.getStdNo())
                .contents(questionReq.getContents())
                .writerNo(userNo)
                .saved(Saved.FALSE)
                .build();
        personalQuestionRepository.save(personalQuestion);
    }

    @Override
    public List<PersonalQuestion> getList(Long detailNo) {
        return personalQuestionRepositorySupport.findAllPersonalQuestionByDetailNo(detailNo);
    }

    @Override
    public PersonalQuestion getPersonalQuestion(Long questionNo, Long detailNo, Long writerNo) {
        return personalQuestionRepositorySupport.findPersonalQuestionByQuestionNoAndDetailNoAndUserNo(questionNo, detailNo, writerNo);
    }

    @Override
    @Transactional
    public void updatePersonalQuestion(PersonalQuestion personalQuestion, QuestionUpdateReq questionUpdateReq) {
        String contents = questionUpdateReq.getContents();
        personalQuestion.updatePersonalQuestion(contents);
    }

    @Override
    @Transactional
    public int deletePersonalQuestion(Long questionNo) {
        try {
            personalQuestionRepository.findByQuestionNo(questionNo).get();
        } catch (Exception e) {
            return 0;
        }
        personalQuestionRepository.deleteByQuestionNo(questionNo);
        return 1;
    }

    @Override
    @Transactional
    public void updateSavedStatus(PersonalQuestion personalQuestion) {
        Saved saved = personalQuestion.getSaved();
        if (saved == Saved.TRUE)
            personalQuestion.updateSavedStatus(Saved.FALSE);
        else personalQuestion.updateSavedStatus(Saved.TRUE);
    }

    @Override
    public PersonalQuestion validatePersonalQuestion(Long questionNo, Long detailNo, Long userNo) {
        return personalQuestionRepositorySupport.findPersonalQuestion(questionNo, detailNo, userNo);
    }

}
