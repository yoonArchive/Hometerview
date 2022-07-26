package com.ssafy.db.repository;

import com.ssafy.db.entity.PersonalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonalQuestionRepository extends JpaRepository<PersonalQuestion, Long> {

    Optional<PersonalQuestion> findByQuestionNo(Long questionNo);

}
