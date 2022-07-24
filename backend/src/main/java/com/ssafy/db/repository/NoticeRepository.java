package com.ssafy.db.repository;

import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Transactional
    Optional<Notice> deleteByNoticeNo(Long noticeNo);

    Optional<Notice> findByNoticeNo(Long noticeNo);
    
}
