package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private long noticeNo;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "notice_contents")
    private String noticeContents;

    @Column(name = "write_date")
    private String writeDate;

}
