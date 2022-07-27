package com.ssafy.api.service;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final RecruitRepository recruitRepository;

    private final StudyRepository studyRepository;

    private final ApplyRepositorySupport applyRepositorySupport;

    private final StudyJoinRepository studyJoinRepository;

    private final UserRepository userRepository;

    private final StudyJoinRepositorySupport studyJoinRepositorySupport;

    private final ApplyRepository applyRepository;

    @Override
    public void createStudy(Long recruitNo) {
        // 스터디 생성
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).orElse(null);
        Study study = Study.builder()
                .stdName(recruit.getStdName())
                .stdDetail(recruit.getStdDetail())
                .stdImg(recruit.getStdImg())
                .stdType(recruit.getStdType())
                .comName(recruit.getComName())
                .startDate(recruit.getStartDate())
                .endDate(recruit.getEndDate())
                .stdDay(recruit.getStdDay())
                .stdLimit(recruit.getStdLimit())
                .build();
        studyRepository.save(study);
        this.recruitComplete(recruit);

        //스터디 조인
        List<Apply> applyList = applyRepositorySupport.findApplyByRecruitNo(recruitNo);
        for (Apply apply : applyList) {
            StudyJoin studyJoin = StudyJoin.builder()
                    .user(apply.getUser())
                    .study(study)
                    .joinType(apply.getApplyType())
                    .build();
            studyJoinRepository.save(studyJoin);
            applyRepository.deleteByApplyNo(apply.getApplyNo());
        }
    }

    @Override
    public List<Study> getStudyList(Long userNo) {
        User user = userRepository.findByUserNo(userNo).orElse(null);
        List<StudyJoin> studyJoinList = user.getStudyJoins();
        List<Study> studyList = new ArrayList<>();
        for (StudyJoin studyJoin : studyJoinList) {
            studyList.add(studyJoin.getStudy());
        }
        return studyList;
    }

    @Override
    public Study detailStudy(Long stdNo) {
        Study study = studyRepository.findByStdNo(stdNo).orElse(null);
        return study;
    }

    @Override
    @Transactional
    public int leaveStudy(Long userNo, Long stdNo) {
        StudyJoin studyJoin = studyJoinRepositorySupport.findStudyJoinByUserNoAndStdNo(userNo, stdNo).orElse(null);
        if (studyJoin == null) return 0;
        else {
            studyJoinRepository.deleteByJoinNo(studyJoin.getJoinNo());
        }
        return 1;
    }

    @Override
    @Transactional
    public void updateNotice(Study study, String newNotice) {
        study.updateNotice(newNotice);
    }

    @Override
    @Transactional
    public void updateEndDate(Study study, String newEndDate) {
        study.updateEndDate(newEndDate);
    }

    @Override
    @Transactional
    public void updateStdImg(Study study, String newStdImg) {
        study.updateStdImg(newStdImg);
    }

    @Override
    @Transactional
    public void updateStdDay(Study study, String newStdDay) {
        study.updateStdDay(newStdDay);
    }

    @Override
    @Transactional
    public void recruitComplete(Recruit recruit) {
        recruit.recruitComplete();
    }

}