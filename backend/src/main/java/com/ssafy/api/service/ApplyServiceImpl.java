package com.ssafy.api.service;

import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ApplyRepository;
import com.ssafy.db.repository.RecruitRepository;
import com.ssafy.db.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    ApplyRepository applyRepository;

    @Override
    public void applyRecruit(Long userNo, Long recruitNo) {
        User user = userRepository.findByUserNo(userNo).get();
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).get();
        Apply apply = Apply.createApply(user, recruit);
        applyRepository.save(apply);
    }
}
