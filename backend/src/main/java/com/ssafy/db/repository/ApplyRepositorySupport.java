package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.QApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ApplyRepositorySupport {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QApply qApply = QApply.apply;

    public Optional<Apply> findApplyByUserNoAndRecruitNo(Long userNo, Long recruitNo) {
        Apply apply = jpaQueryFactory.select(qApply).from(qApply)
                .where(qApply.user.userNo.eq(userNo))
                .where(qApply.recruit.recruitNo.eq(recruitNo)).fetchOne();
        if (apply == null) return Optional.empty();
        return Optional.ofNullable(apply);
    }
}
