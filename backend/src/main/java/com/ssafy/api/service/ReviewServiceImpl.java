package com.ssafy.api.service;

import com.ssafy.api.request.ReviewReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.ReviewRepository;
import com.ssafy.db.repository.ReviewRepositorySupport;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewRepositorySupport reviewRepositorySupport;

    private final UserRepository userRepository;

    @Override
    public void writeReview(Long userNo, ReviewReq reviewReq) {
        User user = userRepository.findByUserNo(userNo).get();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Review review = Review.builder()
                .user(user)
                .reviewTitle(reviewReq.getReviewTitle())
                .reviewContents(reviewReq.getReviewContents())
                .reviewType(reviewReq.getReviewType())
                .writeDate(dataFormat.format(now))
                .build();
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getList(Long userNo) {
        return reviewRepositorySupport.findAllReviewByUserNo(userNo);
    }

    @Override
    public Review getReviewDetail(Long reviewNo, Long userNo) {
        return reviewRepositorySupport.findReviewByReviewNoAndUserNo(reviewNo, userNo);
    }

    @Override
    @Transactional
    public void updateReview(Review review, ReviewReq reviewReq) {
        String reviewTitle = reviewReq.getReviewTitle();
        String reviewContents = reviewReq.getReviewContents();
        ReviewType reviewType = reviewReq.getReviewType();
        review.initReview(reviewTitle, reviewContents, reviewType);
    }

    @Override
    public Review getByReviewNo(Long reviewNo) {
        return reviewRepository.findByReviewNo(reviewNo).orElse(null);
    }

    @Override
    @Transactional
    public int deleteReview(Long reviewNo) {
        try {
            reviewRepository.findByReviewNo(reviewNo).get();
        } catch (Exception e) {
            return 0;
        }
        reviewRepository.deleteByReviewNo(reviewNo);
        return 1;
    }

}
