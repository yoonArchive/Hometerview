package com.ssafy.api.service;

import com.ssafy.api.request.StudyPostReq;
import com.ssafy.db.entity.*;

import java.util.List;

public interface StudyService {

    void createStudy(Long recruitNo);

    List<Study> getStudyList(Long userNo);

    Study detailStudy(Long stdNo);

    int leaveStudy(Long UserNo, Long stdNo);

    void updateNotice(Study study, String newNotice);

    void updateEndDate(Study study, String newEndDate);

    void updateStdImg(Study study, String newStdImg);

    void updateStdDay(Study study, String newStdDay);

    void recruitComplete(Recruit recruit);

    StudyJoin findStudyJoin(Long stdNo, Long userNo);

    void updateRegistedResume(StudyJoin studyJoin, Long resumeNo);

    long[] getDetailCounts(Long stdNo);

    ApplyType getJoinType(Long userNo, Long stdNo);

}
