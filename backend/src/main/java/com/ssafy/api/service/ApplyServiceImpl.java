package com.ssafy.api.service;

import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.ApplyType;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ApplyRepository;
import com.ssafy.db.repository.ApplyRepositorySupport;
import com.ssafy.db.repository.RecruitRepository;
import com.ssafy.db.repository.UserRepository;

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

    @Override
    public int applyRecruit(Long userNo, Long recruitNo, ApplyType applyType) {
        try {
            applyRepositorySupport.findApplyByUserNoAndRecruitNo(userNo, recruitNo).get();
        } catch (Exception e) {
            User user = userRepository.findByUserNo(userNo).get();
            Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).get();
            Apply apply = new Apply();
            apply.createApply(user, recruit, applyType);
            applyRepository.save(apply);
            return 1;
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
        return applyRepositorySupport.CountByRecruitNo(recruit.getRecruitNo()) + 1;
    }

    @Override
    public ApplyType getApplyType(Long recruitNo, Long userNo) {
        return applyRepositorySupport.findByRecruitNoAndUserNo(recruitNo, userNo);
    }

}
