package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "personal_question")
public class PersonalQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_no")
    private Long questionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regist_no")
    private RegistResume registResume;

    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "std_no")
    private Long stdNo;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writer_no")
    private Long writerNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "saved")
    private Saved saved;

}
