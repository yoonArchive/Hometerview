package com.ssafy.db.repository;

import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Transactional
    Optional<Apply> deleteByApplyNo(Long applyNo);

}
