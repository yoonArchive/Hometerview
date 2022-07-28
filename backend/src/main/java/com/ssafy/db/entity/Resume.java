package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_no")
    private Long resumeNo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @JoinColumn(name = "resume_title")
    private String resumeTitle;

    public void createResume(User user, String resumeTitle) {
        this.user = user;
        this.resumeTitle = resumeTitle;
    }

    public void updateTitle(String resumeTitle) {
        this.resumeTitle = resumeTitle;
    }

}
