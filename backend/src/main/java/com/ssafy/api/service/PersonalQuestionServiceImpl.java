package com.ssafy.api.service;

import com.ssafy.api.request.QuestionReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.PersonalQuestionRepository;
import com.ssafy.db.repository.ResumeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalQuestionServiceImpl implements PersonalQuestionService {

    private final PersonalQuestionRepository personalQuestionRepository;

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


}
