package com.ssafy.api.service;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.db.entity.Recruit;

import java.util.List;

public interface RecruitService {

    void writeRecruit(RecruitReq recruitReq);

    List<Recruit> getList();

    Recruit getByRecruitNo(Long recruitNo);

    void updateRecruit(Recruit recruit, RecruitReq recruitReq);

    int deleteRecruit(Long recruitNo);

}
