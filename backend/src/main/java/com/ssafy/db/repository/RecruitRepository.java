package com.ssafy.db.repository;

import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    Optional<Recruit> findByRecruitNo(Long RecruitNo);

    List<Recruit> findAllByRecruitStatus(RecruitStatus recruitStatus);

    @Transactional
    Optional<Recruit> deleteByRecruitNo(Long recruitNo);

}
