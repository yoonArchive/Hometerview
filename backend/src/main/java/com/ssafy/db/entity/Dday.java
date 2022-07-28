package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "d_day")
public class Dday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dday_no")
    private Long ddayNo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "dday_title")
    private String ddayTitle;

    @Column(name = "dday_date")
    private String ddayDate;

    public void initDday(String ddayTitle, String ddayDate) {
        this.ddayTitle = ddayTitle;
        this.ddayDate = ddayDate;
    }

}
