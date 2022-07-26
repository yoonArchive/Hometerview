package com.ssafy.db.repository;

import com.ssafy.db.entity.PersonalQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalQuestionRepository extends JpaRepository<PersonalQuestion, Long> {

    List<PersonalQuestion> getAllByQuestionNo(Long questionNo);

}
