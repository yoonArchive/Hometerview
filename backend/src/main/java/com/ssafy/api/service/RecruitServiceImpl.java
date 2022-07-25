package com.ssafy.api.service;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import com.ssafy.db.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitRepository recruitRepository;

    @Override
    public void writeRecruit(RecruitReq recruitReq) {
        Recruit recruit = new Recruit();
        initRecruit(recruitReq, recruit);
        recruit.setRecruitStatus(RecruitStatus.RECRUITING);
        recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> getList() {
        return recruitRepository.findAll();
    }

    @Override
    public List<Recruit> getRecruitingList() {
        return recruitRepository.findAllByRecruitStatus(RecruitStatus.RECRUITING);
    }

    @Override
    public Recruit getByRecruitNo(Long recruitNo) {
        return recruitRepository.findByRecruitNo(recruitNo).orElse(null);
    }

    @Override
    @Transactional
    public void updateRecruit(Recruit recruit, RecruitReq recruitReq) {
        initRecruit(recruitReq, recruit);
    }

    @Override
    @Transactional
    public int deleteRecruit(Long recruitNo) {
        try {
            recruitRepository.findByRecruitNo(recruitNo).get();
        } catch (Exception e) {
            return 0;
        }
        recruitRepository.deleteByRecruitNo(recruitNo);
        return 1;
    }

    private void initRecruit(RecruitReq recruitReq, Recruit recruit) {
        recruit.setRecruitTitle(recruitReq.getRecruitTitle());
        recruit.setStdName(recruitReq.getStdName());
        recruit.setStdDetail(recruitReq.getStdDetail());
        recruit.setStdImg(recruitReq.getStdImg());
        recruit.setStdType(recruitReq.getStdType());
        recruit.setComName(recruitReq.getComName());
        recruit.setStartDate(recruitReq.getStartDate());
        recruit.setEndDate(recruitReq.getEndDate());
        recruit.setStdDay(recruitReq.getStdDay());
        recruit.setStdLimit(recruitReq.getStdLimit());
    }

}
