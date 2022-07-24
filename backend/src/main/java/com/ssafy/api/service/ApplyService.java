package com.ssafy.api.service;

public interface ApplyService {

    void applyRecruit(Long userNo, Long recruitNo);

    int deleteApply(Long userNo, Long recruitNo);

}
