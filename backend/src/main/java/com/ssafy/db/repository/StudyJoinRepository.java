package com.ssafy.db.repository;

import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.StudyJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudyJoinRepository extends JpaRepository<StudyJoin, Long> {

    @Transactional
    Optional<StudyJoin> deleteByJoinNo(Long joinNo);
}
