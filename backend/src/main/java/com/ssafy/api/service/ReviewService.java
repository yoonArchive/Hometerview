package com.ssafy.api.service;

import com.ssafy.api.request.ReviewReq;
import com.ssafy.db.entity.Review;

import java.util.List;

public interface ReviewService {

    Review writeReview(Long userNo, ReviewReq reviewReq);

    List<Review> getList(Long userNo);

    Review getReviewDetail(Long reviewNo, Long userNo);

    void updateReview(Review review, ReviewReq reviewReq);

    Review getByReviewNo(Long reviewNo);

    void deleteReview(Long reviewNo);

}
