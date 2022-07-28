package com.ssafy.db.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommonQuestionRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QCommonQuestion qCommonQuestion = QCommonQuestion.commonQuestion;

    public List<CommonQuestion> findAllCommonQuestionByStdNo(Long stdNo) {
        return jpaQueryFactory.select(qCommonQuestion).from(qCommonQuestion)
                .where(qCommonQuestion.study.stdNo.eq(stdNo)).fetch();
    }

    public List<CommonQuestion> findAllCommonQuestionByStdNoAndQuestionType(Long stdNo, QuestionType questionType) {
        return jpaQueryFactory.select(qCommonQuestion).from(qCommonQuestion)
                .where(qCommonQuestion.study.stdNo.eq(stdNo))
                .where(qCommonQuestion.questionType.eq(questionType)).fetch();
    }
}
