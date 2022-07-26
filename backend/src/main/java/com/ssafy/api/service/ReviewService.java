package com.ssafy.api.service;

import com.ssafy.api.request.ReviewReq;
import com.ssafy.db.entity.Review;

import java.util.List;

public interface ReviewService {

    void writeReview(Long userNo, ReviewReq reviewReq);

    List<Review> getList(Long userNo);

    Review getByReviewNo(Long reviewNo);

}
