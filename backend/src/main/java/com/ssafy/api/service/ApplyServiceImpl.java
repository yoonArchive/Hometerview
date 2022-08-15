package com.ssafy.api.service;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {

    private final UserRepository userRepository;

    private final RecruitRepository recruitRepository;

    private final ApplyRepository applyRepository;

    private final ApplyRepositorySupport applyRepositorySupport;

    private final StudyJoinRepositorySupport studyJoinRepositorySupport;

    @Override
    public int applyRecruit(Long userNo, Long recruitNo, ApplyType applyType) {
        try {
            applyRepositorySupport.findApplyByUserNoAndRecruitNo(userNo, recruitNo).get();
        } catch (Exception e) {
            User user = userRepository.findByUserNo(userNo).get();
            Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).get();
            long applyCount = applyRepositorySupport.CountByRecruitNo(recruitNo);
            if(applyCount >= recruit.getStdLimit()) return 1;
            Apply apply = new Apply();
            apply.createApply(user, recruit, applyType);
            applyRepository.save(apply);
            return 2;
        }
        return 0;
    }

    @Override
    @Transactional
    public int deleteApply(Long userNo, Long recruitNo) {
        Apply apply;
        try {
            apply = applyRepositorySupport.findApplyByUserNoAndRecruitNo(userNo, recruitNo).get();
        } catch (Exception e) {
            return 0;
        }
        Long applyNo = apply.getApplyNo();
        applyRepository.deleteByApplyNo(applyNo);
        return 1;
    }

    @Override
    public long[] getApplyCounts(List<Recruit> recruits) {
        long[] applyCount = new long[recruits.size()];
        int idx = 0;
        for (Recruit recruit : recruits) {
            applyCount[idx++] = applyRepositorySupport.CountByRecruitNo(recruit.getRecruitNo());
        }
        return applyCount;
    }

    @Override
    public long getApplyCount(Recruit recruit) {
        return applyRepositorySupport.CountByRecruitNo(recruit.getRecruitNo());
    }

    @Override
    public ApplyType getApplyType(Recruit recruit, Long userNo) {
        if(recruit.getRecruitStatus() == RecruitStatus.RECRUITING) {
            return applyRepositorySupport.findByRecruitNoAndUserNo(recruit.getRecruitNo(), userNo);
        }
        else return studyJoinRepositorySupport.findStudyJoinByUserNoAndStdNo(userNo, recruit.getStdNo()).get().getJoinType();
    }

}
