package com.ssafy.db.entity;

import lombok.*;
import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    @OneToMany(mappedBy = "study")
    private List<StudyJoin> studyJoins = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "study")
    private List<Recording> recordings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "study")
    private List<CommonQuestion> commonQuestions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "study")
    private List<RegistResume> registResumes = new ArrayList<>();

    public void updateStudy(String newStdName, String newStdDetail, StdType newStdType,  String newComName,
                            String newStartDate, String newEndDate,  String newStdDay, int newStdLimit, String newStdNotice) {
        this.stdName = newStdName;
        this.stdDetail = newStdDetail;
        this.stdType = newStdType;
        this.comName = newComName;
        this.startDate = newStartDate;
        this.endDate = newEndDate;
        this.stdDay = newStdDay;
        this.stdLimit = newStdLimit;
        this.stdNotice = newStdNotice;

    }

    public void updateNotice(String newStdNotice) { this.stdNotice = newStdNotice; }

    public void updateEndDate(String newEndDate) {
        this.endDate = newEndDate;
    }

    public void updateStdImg(String fileUrl) {
        this.stdImg = fileUrl;
    }

    public void updateStdDay(String newStdDay) {
        this.stdDay = newStdDay;
    }

}
