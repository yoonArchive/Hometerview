package com.ssafy.db.repository;

import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import com.ssafy.db.entity.StdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    Optional<Recruit> findByRecruitNo(Long RecruitNo);

    List<Recruit> findAllByOrderByRecruitNoDesc();

    List<Recruit> findAllByRecruitStatusOrderByRecruitNoDesc(RecruitStatus recruitStatus);

    List<Recruit> findAllByStdTypeOrderByRecruitNoDesc(StdType stdType);

    List<Recruit> findByRecruitTitleContainingIgnoreCase(String keyword);

    @Transactional
    Optional<Recruit> deleteByRecruitNo(Long recruitNo);

}
