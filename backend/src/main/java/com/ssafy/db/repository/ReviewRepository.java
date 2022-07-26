package com.ssafy.db.repository;

import com.ssafy.db.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByReviewNo(Long reviewNo);

    @Transactional
    Optional<Review> deleteByReviewNo(Long reviewNo);

}
