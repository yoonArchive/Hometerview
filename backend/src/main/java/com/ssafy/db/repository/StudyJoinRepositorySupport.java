package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QStudyJoin;
import com.ssafy.db.entity.StudyJoin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudyJoinRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QStudyJoin qStudyJoin = QStudyJoin.studyJoin;

    public Optional<StudyJoin> findStudyJoinByUserNoAndStdNo(Long userNo, Long stdNo) {
        StudyJoin studyJoin = jpaQueryFactory.select(qStudyJoin).from(qStudyJoin)
                .where(qStudyJoin.user.userNo.eq(userNo))
                .where(qStudyJoin.study.stdNo.eq(stdNo)).fetchOne();
        if (studyJoin == null) return Optional.empty();
        return Optional.ofNullable(studyJoin);
    }

    public List<StudyJoin> findByStdNo(Long stdNo) {
        List<StudyJoin> studyJoins = jpaQueryFactory.selectFrom(qStudyJoin)
                .where(qStudyJoin.study.stdNo.eq(stdNo))
                .fetch();
        return studyJoins;
    }
}
