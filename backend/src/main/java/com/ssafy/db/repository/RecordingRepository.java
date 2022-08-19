package com.ssafy.db.repository;

import com.ssafy.db.entity.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RecordingRepository extends JpaRepository<Recording, Long> {

    @Transactional
    Optional<Recording> deleteByRecordNo(Long recordNo);

    Optional<Recording> findByRecordNo(Long recordNo);

}
