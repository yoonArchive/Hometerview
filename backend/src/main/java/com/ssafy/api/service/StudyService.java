package com.ssafy.api.service;

import com.ssafy.api.request.StudyPostReq;
import com.ssafy.db.entity.Study;

import java.util.List;

public interface StudyService {

    void createStudy(Long recruitNo);
    List<Study> getStudyList(Long userNo);
    Study detailStudy(Long stdNo);
    int leaveStudy(Long UserNo, Long stdNo);
}
