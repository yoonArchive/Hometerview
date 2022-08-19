package com.ssafy.db.repository;

import com.ssafy.db.entity.Dday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DdayRepository extends JpaRepository<Dday, Long> {
    Optional<Dday> findByDdayNo(Long ddayNo);

    Optional<Dday> deleteByDdayNo(Long ddayNo);

}
