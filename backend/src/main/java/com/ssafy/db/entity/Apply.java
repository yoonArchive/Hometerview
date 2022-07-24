package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public static Apply createApply(User user, Recruit recruit) {
        Apply apply = new Apply();
        apply.setUser(user);
        apply.setRecruit(recruit);
        return apply;
    }

}
