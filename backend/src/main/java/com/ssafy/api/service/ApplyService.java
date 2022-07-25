package com.ssafy.api.service;

import com.ssafy.db.entity.Recruit;

import java.util.List;

public interface ApplyService {

    int applyRecruit(Long userNo, Long recruitNo);

    int deleteApply(Long userNo, Long recruitNo);

    long[] getApplyCount(List<Recruit> recruits);

}
