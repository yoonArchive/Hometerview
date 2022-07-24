package com.ssafy.db.repository;

import com.ssafy.db.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    Optional<Recruit> findByRecruitNo(Long RecruitNo);

    @Transactional
    Optional<Recruit> deleteByRecruitNo(Long recruitNo);

}
