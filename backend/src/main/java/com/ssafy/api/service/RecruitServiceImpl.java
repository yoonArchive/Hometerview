package com.ssafy.api.service;

import com.ssafy.api.request.RecruitWritePostReq;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitRepository recruitRepository;

    @Override
    public void writeRecruit(RecruitWritePostReq recruitWritePostReq) {
        Recruit recruit = new Recruit();
        recruit.setRecruitTitle(recruitWritePostReq.getRecruitTitle());
        recruit.setStdName(recruitWritePostReq.getStdName());
        recruit.setStdDetail(recruitWritePostReq.getStdDetail());
        recruit.setStdImg(recruitWritePostReq.getStdImg());
        recruit.setStdType(recruitWritePostReq.getStdType());
        recruit.setComName(recruitWritePostReq.getComName());
        recruit.setStartDate(recruitWritePostReq.getStartDate());
        recruit.setEndDate(recruitWritePostReq.getEndDate());
        recruit.setStdDay(recruitWritePostReq.getStdDay());
        recruit.setStdLimit(recruitWritePostReq.getStdLimit());
        recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> getList() {
        return recruitRepository.findAll();
    }

    @Override
    public Recruit getByRecruitNo(Long recruitNo) {
        return recruitRepository.findByRecruitNo(recruitNo).orElse(null);
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

}
