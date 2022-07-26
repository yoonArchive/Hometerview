package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    private Long reviewNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_contents")
    private String reviewContents;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_type")
    private ReviewType reviewType;

    @Column(name = "write_date")
    private String writeDate;

    public void initReview(String reviewTitle, String reviewContents, ReviewType reviewType) {
        this.reviewTitle = reviewTitle;
        this.reviewContents = reviewContents;
        this.reviewType = reviewType;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
