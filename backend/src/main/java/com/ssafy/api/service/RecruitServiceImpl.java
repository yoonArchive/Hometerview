package com.ssafy.api.service;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import com.ssafy.db.entity.StdType;
import com.ssafy.db.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;

    @Override
    public Recruit writeRecruit(RecruitReq recruitReq) {
        Recruit recruit = Recruit.builder()
                .recruitTitle(recruitReq.getRecruitTitle())
                .stdName(recruitReq.getStdName())
                .stdDetail(recruitReq.getStdDetail())
                .stdImg(recruitReq.getStdImg())
                .stdType(recruitReq.getStdType())
                .comName(recruitReq.getComName())
                .startDate(recruitReq.getStartDate())
                .endDate(recruitReq.getEndDate())
                .stdDay(recruitReq.getStdDay())
                .stdLimit(recruitReq.getStdLimit())
                .recruitStatus(RecruitStatus.RECRUITING)
                .build();
        return recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> getList() {
        return recruitRepository.findAllByOrderByRecruitNoDesc();
    }

    @Override
    public List<Recruit> getRecruitingList() {
        return recruitRepository.findAllByRecruitStatusOrderByRecruitNoDesc(RecruitStatus.RECRUITING);
    }

    @Override
    public List<Recruit> getFilteredList(int type) {
        if (type == 1) {
            return recruitRepository.findAll();
        } else if (type == 2) {
            return recruitRepository.findAllByStdTypeOrderByRecruitNoDesc(StdType.COM);
        } else {
            return recruitRepository.findAllByStdTypeOrderByRecruitNoDesc(StdType.FREE);
        }
    }

    @Override
    public List<Recruit> search(String keyword) {
        return recruitRepository.findByRecruitTitleContainingIgnoreCaseOrComNameContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public Recruit getByRecruitNo(Long recruitNo) {
        return recruitRepository.findByRecruitNo(recruitNo).orElse(null);
    }

    @Override
    @Transactional
    public void updateRecruit(Recruit recruit, RecruitReq recruitReq) {
        String recruitTitle = recruitReq.getRecruitTitle();
        String stdName = recruitReq.getStdName();
        String stdDetail = recruitReq.getStdDetail();
        String stdImg = recruitReq.getStdImg();
        StdType stdType = recruitReq.getStdType();
        String comName = recruitReq.getComName();
        String startDate = recruitReq.getStartDate();
        String endDate = recruitReq.getEndDate();
        String stdDay = recruitReq.getStdDay();
        int stdLimit = recruitReq.getStdLimit();
        recruit.initRecruit(recruitTitle, stdName, stdDetail, stdImg, stdType, comName, startDate, endDate, stdDay, stdLimit);
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
