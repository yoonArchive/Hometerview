package com.ssafy.db.repository;

import com.ssafy.db.entity.ResumeDetail;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ResumeDetailRepository extends JpaRepository<ResumeDetail, Long> {

    Optional<ResumeDetail> findByDetailNo(Long detailNo);

    List<ResumeDetail> findAllByResumeNo(Long resumeNo);

    Optional<ResumeDetail> findByResumeNoAndItemNo(Long resumeNo, Long itemNo);

    @Transactional
    Optional<ResumeDetail> deleteByDetailNo(Long detailNo);

}
