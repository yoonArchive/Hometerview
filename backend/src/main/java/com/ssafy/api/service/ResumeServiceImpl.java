package com.ssafy.api.service;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ResumeDetailRepository;
import com.ssafy.db.repository.ResumeRepository;
import com.ssafy.db.repository.UserRepository;
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

    // 자기소개서
    @Override
    public void createResume(Long userNo) {
        Resume resume = new Resume();
        User user = userRepository.findByUserNo(userNo).get();
        resume.setUser(user);
        resumeRepository.save(resume);
    }

    @Override
    public List<Resume> listResume() {
        return resumeRepository.findAll();
    }

    @Override
    public int deleteResume(Long resumeNo, List<ResumeDetail> detailList) {
        try {
            resumeRepository.findByResumeNo(resumeNo).get();
            for(ResumeDetail resumeDetail : detailList) {
                resumeDetailRepository.deleteByDetailNo(resumeDetail.getDetailNo());
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }


    // 자기소개서 상세
    @Override
    public ResumeDetail writeResumeDetail(Long resumeNo, ResumeWritePostReq resumeWritePostReq) {
        ResumeDetail resumeDetail = new ResumeDetail();
        Resume resume = resumeRepository.findByResumeNo(resumeNo).orElse(null);
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
        return resumeDetailRepository.findByResumeNoAndItemNo(resumeNo, itemNo).orElse(null);
    }

    @Override
    public List<ResumeDetail> listResumeDetail(Long resumeNo){
        return resumeDetailRepository.findAllByResumeNo(resumeNo);
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
