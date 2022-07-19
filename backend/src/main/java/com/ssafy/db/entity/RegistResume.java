package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "regist_resume")
public class RegistResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regist_no")
    private Long registNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "std_no")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_no")
    private Resume resume;

    @OneToMany(mappedBy = "registResume")
    private List<PersonalQuestion> personalQuestions = new ArrayList<>();

}
