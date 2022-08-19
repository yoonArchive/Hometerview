package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public void updateNotice(String noticeTitle, String noticeContents, String writeDate) {
        this.noticeTitle = noticeTitle;
        this.noticeContents = noticeContents;
        this.writeDate = writeDate;
    }

}
