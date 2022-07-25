package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QResumeDetail;
import com.ssafy.db.entity.ResumeDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResumeDetailRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QResumeDetail qResumeDetail = QResumeDetail.resumeDetail;

    public Optional<ResumeDetail> findResumeDetailByResumeNoAndItemNo(Long resumeNo, Long itemNo) {
        ResumeDetail resumeDetail = jpaQueryFactory.select(qResumeDetail).from(qResumeDetail)
                .where(qResumeDetail.resume.resumeNo.eq(resumeNo))
                .where(qResumeDetail.itemNo.eq(itemNo)).fetchOne();
        if (resumeDetail == null) return Optional.empty();
        return Optional.ofNullable(resumeDetail);
    }

    public List<ResumeDetail> findAllResumeDetailByResumeNo(Long resumeNo) {
        List<ResumeDetail> resumeDetails = jpaQueryFactory.select(qResumeDetail).from(qResumeDetail)
                .where(qResumeDetail.resume.resumeNo.eq(resumeNo)).fetch();
        if (resumeDetails == null) return null;
        return resumeDetails;
    }

}
