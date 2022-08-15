package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_no")
    private Long feedbackNo;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "std_no")
//    private Study study;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "record_no")
//    private Recording recording;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writer_no")
    private Long writerNo;

    @Column(name = "write_date")
    private String writeDate;

}
