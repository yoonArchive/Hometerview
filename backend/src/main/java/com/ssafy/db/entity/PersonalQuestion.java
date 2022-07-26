package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personal_question")
public class PersonalQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_no")
    private Long questionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_no")
    private ResumeDetail resumeDetail;

    @Column(name = "std_no")
    private Long stdNo;

    @Column(name = "contents")
    private String contents;

    @Column(name = "writer_no")
    private Long writerNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "saved")
    private Saved saved;

}
