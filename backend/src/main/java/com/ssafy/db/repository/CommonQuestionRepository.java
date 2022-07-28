package com.ssafy.db.repository;

import com.ssafy.db.entity.CommonQuestion;
import com.ssafy.db.entity.PersonalQuestion;
import com.ssafy.db.entity.QuestionType;
import com.ssafy.db.entity.StdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, Long> {

    Optional<CommonQuestion> findByQuestionNo(Long questionNo);

    @Transactional
    Optional<CommonQuestion> deleteByQuestionNo(Long questionNo);
}
