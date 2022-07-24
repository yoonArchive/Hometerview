package com.ssafy.api.service;

import com.ssafy.api.request.RecruitWritePostReq;
import com.ssafy.db.entity.Recruit;

import java.util.List;

public interface RecruitService {

    void writeRecruit(RecruitWritePostReq recruitWritePostReq);

    List<Recruit> getList();

    Recruit getByRecruitNo(Long recruitNo);

    int deleteRecruit(Long recruitNo);

}
