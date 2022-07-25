package com.ssafy.api.service;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResumeDetailRepository resumeDetailRepository;

    private final UserRepository userRepository;

    private final ResumeDetailRepositorySupport resumeDetailRepositorySupport;

    private final ResumeRepositorySupport resumeRepositorySupport;

    @Override
    public void createResume(Long userNo, String resumeTitle) {
        Resume resume = new Resume();
        User user = userRepository.findByUserNo(userNo).get();
        resume.setUser(user);
        resume.setResumeTitle(resumeTitle);
        resumeRepository.save(resume);
    }

    @Override
    public List<Resume> listResume(Long userNo) {
        return resumeRepositorySupport.findAllResumeByUserNo(userNo);
    }

    @Override
    public Resume getByResumeNo(Long resumeNo){
        return resumeRepository.findByResumeNo(resumeNo).orElse(null);
    }

    @Override
    @Transactional
    public void updateResume(Resume resume, String resumeTitle){
        resume.setResumeTitle(resumeTitle);
    }

    @Override
    @Transactional
    public int deleteResume(Long resumeNo) {
        try {
            resumeRepository.findByResumeNo(resumeNo).get();
            List<ResumeDetail> detailList = this.listResumeDetail(resumeNo);
            for(ResumeDetail resumeDetail : detailList) {
                resumeDetailRepository.deleteByDetailNo(resumeDetail.getDetailNo());
            }
            resumeRepository.deleteByResumeNo(resumeNo);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public ResumeDetail writeResumeDetail(ResumeWritePostReq resumeWritePostReq) {
        ResumeDetail resumeDetail = new ResumeDetail();
        Resume resume = resumeRepository.findByResumeNo(resumeWritePostReq.getResumeNo()).orElse(null);
        resumeDetail.setResume(resume);
        resumeDetail.setItemNo(resumeWritePostReq.getItemNo());
        resumeDetail.setItem(resumeWritePostReq.getItem());
        resumeDetail.setAnswer(resumeWritePostReq.getAnswer());
        resumeDetailRepository.save(resumeDetail);
        return resumeDetail;
    }

    @Override
    public ResumeDetail getByDetailNo(Long detailNo){
        return resumeDetailRepository.findByDetailNo(detailNo).orElse(null);
    }

    @Override
    public ResumeDetail getByResumeNoAndItemNo(Long resumeNo, Long itemNo) {
        return resumeDetailRepositorySupport.findResumeDetailByResumeNoAndItemNo(resumeNo, itemNo).orElse(null);
    }

    @Override
    public List<ResumeDetail> listResumeDetail(Long resumeNo){
        return resumeDetailRepositorySupport.findAllResumeDetailByResumeNo(resumeNo);
    }

    @Override
    @Transactional
    public void updateResumeDetail(ResumeDetail resumeDetail, UpdateResumePutReq updateResumePutReq) {
        resumeDetail.setItem(updateResumePutReq.getItem());
        resumeDetail.setAnswer(updateResumePutReq.getAnswer());
    }

    @Override
    @Transactional
    public int deleteResumeDetail(Long detailNo) {
        try {
            resumeDetailRepository.findByDetailNo(detailNo).get();
        } catch (Exception e) {
            return 0;
        }
        resumeDetailRepository.deleteByDetailNo(detailNo);
        return 1;
    }

}
