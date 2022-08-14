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

    @Column(name = "answer", length = 1000)
    private String answer;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "resumeDetail")
    private List<PersonalQuestion> personalQuestions = new ArrayList<>();

    public void createResumeDetail(Resume resume, Long itemNo, String item, String answer) {
        this.resume = resume;
        this.itemNo = itemNo;
        this.item = item;
        this.answer = answer;
    }

    public void updateResumeDetail(String item, String answer) {
        this.item = item;
        this.answer = answer;
    }

}
