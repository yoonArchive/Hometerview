package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "recording")
public class Recording {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_no")
    private Long recordNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "std_no")
    private Study study;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "save_date")
    private String saveDate;

    @Column(name = "video_url")
    private String videoUrl;

    @OneToMany(mappedBy = "recording")
    private List<Feedback> feedbacks = new ArrayList<>();

}
