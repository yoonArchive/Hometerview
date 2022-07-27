package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.PersonalQuestion;
import com.ssafy.db.entity.QPersonalQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonalQuestionRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QPersonalQuestion qPersonalQuestion = QPersonalQuestion.personalQuestion;

    public List<PersonalQuestion> findAllPersonalQuestionByDetailNo(Long detailNo) {
        return jpaQueryFactory.select(qPersonalQuestion).from(qPersonalQuestion)
                .where(qPersonalQuestion.resumeDetail.detailNo.eq(detailNo)).fetch();
    }

    public PersonalQuestion findPersonalQuestionByQuestionNoAndDetailNoAndUserNo(Long questionNo, Long detailNo, Long writerNo) {
        return jpaQueryFactory.select(qPersonalQuestion).from(qPersonalQuestion)
                .where(qPersonalQuestion.questionNo.eq(questionNo))
                .where(qPersonalQuestion.resumeDetail.detailNo.eq(detailNo))
                .where(qPersonalQuestion.writerNo.eq(writerNo)).fetchOne();
    }

}
