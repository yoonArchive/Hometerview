package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_no")
    private Long stdNo;

    @Column(name = "std_name")
    private String stdName;

    @Column(name = "std_detail")
    private String stdDetail;

    @Column(name = "std_img")
    private String stdImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "std_type")
    private StdType stdType;

    @Column(name = "com_name")
    private String comName;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "std_day")
    private String stdDay;

    @Column(name = "std_limit")
    private int stdLimit;

    @Column(name = "std_notice")
    private String stdNotice;

    @JsonIgnore
    @OneToMany(mappedBy = "study")
    private List<StudyJoin> studyJoins = new ArrayList<>();

    @OneToMany(mappedBy = "study")
    private List<Recording> recordings = new ArrayList<>();

    @OneToMany(mappedBy = "study")
    private List<CommonQuestion> commonQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "study")
    private List<RegistResume> registResumes = new ArrayList<>();

}
