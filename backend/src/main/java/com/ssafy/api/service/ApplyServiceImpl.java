package com.ssafy.api.service;

import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ApplyRepository;
import com.ssafy.db.repository.ApplyRepositorySupport;
import com.ssafy.db.repository.RecruitRepository;
import com.ssafy.db.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    ApplyRepository applyRepository;

    @Autowired
    ApplyRepositorySupport applyRepositorySupport;

    @Override
    public void applyRecruit(Long userNo, Long recruitNo) {
        User user = userRepository.findByUserNo(userNo).get();
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).get();
        Apply apply = new Apply();
        apply.setUser(user);
        apply.setRecruit(recruit);
        applyRepository.save(apply);
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

}
