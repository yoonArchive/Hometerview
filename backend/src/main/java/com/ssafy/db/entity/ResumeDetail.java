package com.ssafy.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "resume_detail")
public class ResumeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_no")
    private Long detailNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_no")
    private Resume resume;

    @Column(name = "item_no")
    private Long itemNo;

    @Column(name = "item")
    private String item;

    @Column(name = "answer")
    private String answer;

    @OneToMany(mappedBy = "resumeDetail")
    private List<PersonalQuestion> personalQuestions = new ArrayList<>();

}
