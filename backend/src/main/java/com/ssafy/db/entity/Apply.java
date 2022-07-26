package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apply")
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_no")
    private Long applyNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_no")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    public void createApply(User user, Recruit recruit) {
        this.user = user;
        this.recruit = recruit;
    }

}
