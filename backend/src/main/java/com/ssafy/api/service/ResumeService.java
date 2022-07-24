package com.ssafy.api.service;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {

    // 자기소개서
    void createResume(Long userNo);
    List<Resume> listResume();
    int deleteResume(Long resumeNo, List<ResumeDetail> detailList);

    // 자기소개서 상세
    ResumeDetail writeResumeDetail(Long resumeNo, ResumeWritePostReq resumeWritePostReq);
    ResumeDetail getByDetailNo(Long resumeNo);
    ResumeDetail getByResumeNoAndItemNo(Long resumeNo, Long itemNo);
    List<ResumeDetail> listResumeDetail(Long resumeNo);
    void updateResumeDetail(ResumeDetail detailNo, UpdateResumePutReq updateResumePutReq);
    int deleteResumeDetail(Long detailNo);
}
