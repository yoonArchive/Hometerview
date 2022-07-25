package com.ssafy.api.service;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ResumeService {

    // 자기소개서
    void createResume(Long userNo, String resumeTitle);
    List<Resume> listResume(Long userNo);
    Resume getByResumeNo(Long resumeNo);
    void updateResume(Resume resume, String resumeTitle);
    int deleteResume(Long resumeNo);

    // 자기소개서 상세
    ResumeDetail writeResumeDetail(ResumeWritePostReq resumeWritePostReq);
    ResumeDetail getByDetailNo(Long resumeNo);
    ResumeDetail getByResumeNoAndItemNo(Long resumeNo, Long itemNo);
    List<ResumeDetail> listResumeDetail(Long resumeNo);
    void updateResumeDetail(ResumeDetail resumeDetail, UpdateResumePutReq updateResumePutReq);
    int deleteResumeDetail(Long detailNo);
}
