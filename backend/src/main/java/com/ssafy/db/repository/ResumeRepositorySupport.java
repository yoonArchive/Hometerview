package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QResume;
import com.ssafy.db.entity.QResumeDetail;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResumeRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QResume qResume = QResume.resume;

    public List<Resume> findAllResumeByUserNo(Long userNo) {
        return jpaQueryFactory.select(qResume).from(qResume)
                .where(qResume.user.userNo.eq(userNo)).fetch();
    }

    public Resume findResumeByResumeNoAndUserNo(Long resumeNo, Long userNo) {
        return jpaQueryFactory.select(qResume).from(qResume)
                .where(qResume.resumeNo.eq(resumeNo))
                .where(qResume.user.userNo.eq(userNo)).fetchOne();
    }
}
