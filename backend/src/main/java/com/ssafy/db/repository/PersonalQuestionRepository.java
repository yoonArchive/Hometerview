package com.ssafy.db.repository;

import com.ssafy.db.entity.PersonalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalQuestionRepository extends JpaRepository<PersonalQuestion, Long> {
}
