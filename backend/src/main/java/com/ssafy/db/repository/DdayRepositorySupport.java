package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.Dday;
import com.ssafy.db.entity.QDday;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DdayRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QDday qDday = QDday.dday;

    public List<Dday> findAllDdayByUserNo(Long userNo) {
        return jpaQueryFactory.select(qDday).from(qDday)
                .where(qDday.user.userNo.eq(userNo))
                .orderBy(qDday.ddayDate.asc()).fetch();
    }

    public Dday findDdayByDdayNoAndUserNo(Long ddayNo, Long userNo) {
        return jpaQueryFactory.select(qDday).from(qDday)
                .where(qDday.ddayNo.eq(ddayNo))
                .where(qDday.user.userNo.eq(userNo)).fetchOne();
    }

}
