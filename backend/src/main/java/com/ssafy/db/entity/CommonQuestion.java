package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "common_question")
public class CommonQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_no")
    private Long questionNo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "std_no")
    private Study study;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writer_no")
    private Long writerNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private QuestionType questionType;

    public void updateCommonQuestion(QuestionType questionType, String contents) {
        this.questionType = questionType;
        this.contents = contents;
    }

}
