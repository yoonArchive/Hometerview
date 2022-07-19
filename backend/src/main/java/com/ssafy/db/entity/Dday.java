package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "d_day")
public class Dday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dday_no")
    private Long ddayNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "dday_title")
    private String ddayTitle;

}
