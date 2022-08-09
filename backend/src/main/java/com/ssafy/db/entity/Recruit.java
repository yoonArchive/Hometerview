package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruit")
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_no")
    private Long recruitNo;

    @Column(name = "recruit_title")
    private String recruitTitle;

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

    @Column(name = "std_no")
    private Long stdNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "recruit_status")
    private RecruitStatus recruitStatus;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "recruit", orphanRemoval = true)
    private List<Apply> applies = new ArrayList<>();

    public void initRecruit(String recruitTitle, String stdName, String stdDetail, StdType stdType, String comName, String startDate, String endDate, String stdDay, int stdLimit) {
        this.recruitTitle = recruitTitle;
        this.stdName = stdName;
        this.stdDetail = stdDetail;
        this.stdType = stdType;
        this.comName = comName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stdDay = stdDay;
        this.stdLimit = stdLimit;
    }

    public void recruitComplete() {
        this.recruitStatus = RecruitStatus.COMPLETED;
    }

    public void updateStdImg(String fileUrl) {
        this.stdImg = fileUrl;
    }

    public void addStdNo(Long stdNo) {
        this.stdNo = stdNo;
    }
}
