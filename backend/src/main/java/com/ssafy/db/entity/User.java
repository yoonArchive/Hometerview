package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 유저 모델 정의.
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", unique = true)
    private String userId;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_img")
    private String userImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Resume> resumes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Apply> applies = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<StudyJoin> studyJoins = new ArrayList<>();

}
