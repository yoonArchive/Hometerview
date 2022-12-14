package com.ssafy.db.repository;

import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    Optional<Study> findByStdNo(Long StdNo);

    @Transactional
    Optional<Study> deleteByStdNo(Long stdNo);

}
