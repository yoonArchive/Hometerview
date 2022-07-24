package com.ssafy.db.repository;

import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByResumeNo(Long resumeNo);
    @Transactional
    Optional<Resume> deleteByResumeNo(Long resumeNo);
}
