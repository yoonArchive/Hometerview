package com.ssafy.api.service;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ResumeService {

    void createResume(Long userNo, String resumeTitle);

    List<Resume> listResume(Long userNo);

    Resume getResume(Long resumeNo, Long userNo);

    void updateResume(Resume resume, String resumeTitle);

    int deleteResume(Long resumeNo);

    ResumeDetail writeResumeDetail(ResumeWritePostReq resumeWritePostReq);

    ResumeDetail getByDetailNo(Long resumeNo);

    ResumeDetail getByResumeNoAndItemNo(Long resumeNo, Long itemNo);

    List<ResumeDetail> listResumeDetail(Long resumeNo);

    void updateResumeDetail(ResumeDetail resumeDetail, UpdateResumePutReq updateResumePutReq);

    int deleteResumeDetail(Long detailNo);

    long[] getDetailCount(List<Resume> resumes);

}
