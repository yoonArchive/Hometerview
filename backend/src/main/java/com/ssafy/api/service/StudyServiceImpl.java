package com.ssafy.api.service;

import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyJoin;
import com.ssafy.db.repository.ApplyRepositorySupport;
import com.ssafy.db.repository.RecruitRepository;
import com.ssafy.db.repository.StudyJoinRepository;
import com.ssafy.db.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService{

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    ApplyRepositorySupport applyRepositorySupport;

    @Autowired
    StudyJoinRepository studyJoinRepository;

    @Override
    public void createStudy(Long recruitNo){
        // 스터디 생성
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).orElse(null);
        Study study = new Study();
        study.setStdName(recruit.getStdName());
        study.setStdDetail(recruit.getStdDetail());
        study.setStdImg(recruit.getStdImg());
        study.setStdType(recruit.getStdType());
        study.setComName(recruit.getComName());
        study.setStartDate(recruit.getStartDate());
        study.setEndDate(recruit.getEndDate());
        study.setStdDay(recruit.getStdDay());
        study.setStdLimit(recruit.getStdLimit());
        studyRepository.save(study);

        //스터디 조인
        List<Apply> applyList = applyRepositorySupport.findApplyByRecruitNo(recruitNo);
        for(Apply apply : applyList){
            StudyJoin studyJoin = new StudyJoin();
            studyJoin.setUser(apply.getUser());
            studyJoin.setStudy(study);
            studyJoinRepository.save(studyJoin);
        }

    }



}
