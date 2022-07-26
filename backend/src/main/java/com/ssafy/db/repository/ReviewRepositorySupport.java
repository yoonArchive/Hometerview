package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QReview;
import com.ssafy.db.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QReview qReview = QReview.review;

    public List<Review> findAllReviewByUserNo(Long userNo) {
        return jpaQueryFactory.select(qReview).from(qReview)
                .where(qReview.user.userNo.eq(userNo)).fetch();
    }

    public Review findReviewByReviewNoAndUserNo(Long reviewNo, Long userNo){
        return jpaQueryFactory.select(qReview).from(qReview)
                .where(qReview.reviewNo.eq(reviewNo))
                .where(qReview.user.userNo.eq(userNo)).fetchOne();
    }

}
