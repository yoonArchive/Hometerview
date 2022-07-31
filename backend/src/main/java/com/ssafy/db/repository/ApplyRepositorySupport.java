package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.ApplyType;
import com.ssafy.db.entity.QApply;
import com.ssafy.db.entity.ResumeDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplyRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QApply qApply = QApply.apply;

    public Optional<Apply> findApplyByUserNoAndRecruitNo(Long userNo, Long recruitNo) {
        Apply apply = jpaQueryFactory.select(qApply).from(qApply)
                .where(qApply.user.userNo.eq(userNo))
                .where(qApply.recruit.recruitNo.eq(recruitNo)).fetchOne();
        if (apply == null) return Optional.empty();
        return Optional.ofNullable(apply);
    }

    public long CountByRecruitNo(Long recruitNo) {
        return jpaQueryFactory.selectFrom(qApply)
                .where(qApply.recruit.recruitNo.eq(recruitNo)).fetchCount();
    }

    public List<Apply> findApplyByRecruitNo(Long recruitNo) {
        List<Apply> applies = jpaQueryFactory.selectFrom(qApply)
                .where(qApply.recruit.recruitNo.eq(recruitNo)).fetch();
        if (applies == null) return null;
        return applies;
    }

    public ApplyType findByRecruitNoAndUserNo(Long recruitNo, Long userNo) {
        ApplyType applyType = jpaQueryFactory.select(qApply.applyType)
                .from(qApply)
                .where(qApply.recruit.recruitNo.eq(recruitNo))
                .where(qApply.user.userNo.eq(userNo))
                .fetchOne();
        if (applyType == null) return null;
        return applyType;
    }

}
