package com.ssafy.api.service;

import com.ssafy.api.request.StudyInfoPutReq;
import com.ssafy.api.request.StudyPutReq;
import com.ssafy.db.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudyService {

    Study createStudy(Long recruitNo);

    List<Study> getStudyList(Long userNo);

    Study detailStudy(Long stdNo);

    int leaveStudy(Long UserNo, Long stdNo);

    int updateStudy(Study study, StudyPutReq studyPutReq);

    void updateNotice(Study study, String newNotice);

    void updateEndDate(Study study, String newEndDate);

    int updateStdImg(Study study, MultipartFile multipartFile);

    void updateStdDay(Study study, String newStdDay);

    void recruitComplete(Recruit recruit);

    StudyJoin findStudyJoin(Long stdNo, Long userNo);

    void updateRegistedResume(StudyJoin studyJoin, Long resumeNo);

    long[] getDetailCounts(Long stdNo);

    ApplyType getJoinType(Long userNo, Long stdNo);

    int deleteStudy(Long stdNo);

}
