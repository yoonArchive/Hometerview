package com.ssafy.api.service;

import com.ssafy.api.request.StudyInfoPutReq;
import com.ssafy.api.request.StudyPutReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudyServiceImpl implements StudyService {

    private final RecruitRepository recruitRepository;

    private final StudyRepository studyRepository;

    private final ApplyRepositorySupport applyRepositorySupport;

    private final StudyJoinRepository studyJoinRepository;

    private final UserRepository userRepository;

    private final StudyJoinRepositorySupport studyJoinRepositorySupport;

    private final ApplyRepository applyRepository;

    private final ResumeDetailRepositorySupport resumeDetailRepositorySupport;

    String uploadPath = "C:\\intervenience";

    String uploadFolder = "stdImg";

    @Override
    public Study createStudy(Long recruitNo) {
        // 스터디 생성
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo).orElse(null);
        Study study = Study.builder()
                .stdName(recruit.getStdName())
                .stdDetail(recruit.getStdDetail())
                .stdImg(recruit.getStdImg())
                .stdType(recruit.getStdType())
                .comName(recruit.getComName())
                .startDate(recruit.getStartDate())
                .endDate(recruit.getEndDate())
                .stdDay(recruit.getStdDay())
                .stdLimit(recruit.getStdLimit())
                .build();
        studyRepository.save(study);
        recruit.addStdNo(study.getStdNo());
        this.recruitComplete(recruit);
        //스터디 조인
        List<Apply> applyList = applyRepositorySupport.findApplyByRecruitNo(recruitNo);
        for (Apply apply : applyList) {
            StudyJoin studyJoin = StudyJoin.builder()
                    .user(apply.getUser())
                    .study(study)
                    .joinType(apply.getApplyType())
                    .build();
            studyJoinRepository.save(studyJoin);
            applyRepository.deleteByApplyNo(apply.getApplyNo());
        }
        return study;
    }

    @Override
    public List<Study> getStudyList(Long userNo) {
        User user = userRepository.findByUserNo(userNo).orElse(null);
        List<StudyJoin> studyJoinList = user.getStudyJoins();
        List<Study> studyList = new ArrayList<>();
        for (StudyJoin studyJoin : studyJoinList) {
            studyList.add(studyJoin.getStudy());
        }
        return studyList;
    }

    @Override
    public Study detailStudy(Long stdNo) {
        Study study = studyRepository.findByStdNo(stdNo).orElse(null);
        return study;
    }

    @Override
    @Transactional
    public int leaveStudy(Long userNo, Long stdNo) {
        StudyJoin studyJoin = studyJoinRepositorySupport.findStudyJoinByUserNoAndStdNo(userNo, stdNo);
        if (studyJoin == null) return 0;
        else {
            studyJoinRepository.deleteByJoinNo(studyJoin.getJoinNo());
        }
        return 1;
    }
    @Override
    @Transactional
    public int updateStudy(Study study, StudyPutReq studyPutReq){
        String newStdName = studyPutReq.getStudyInfoPutReq().getStdName();
        String newStdDetail = studyPutReq.getStudyInfoPutReq().getStdDetail();
        StdType newStdType = studyPutReq.getStudyInfoPutReq().getStdType();
        String newComName = studyPutReq.getStudyInfoPutReq().getComName();
        String newStartDate = studyPutReq.getStudyInfoPutReq().getStartDate();
        String newEndDate = studyPutReq.getStudyInfoPutReq().getEndDate();
        String newStdDay = studyPutReq.getStudyInfoPutReq().getStdDay();
        int newStdLimit = studyPutReq.getStudyInfoPutReq().getStdLimit();
        String newStdNotice = studyPutReq.getStudyInfoPutReq().getStdNotice();
        try {
            study.updateStudy(newStdName, newStdDetail, newStdType, newComName, newStartDate, newEndDate, newStdDay, newStdLimit, newStdNotice);
        } catch (Exception e) {
            return 0;
        }
        int result = updateStdImg(study, studyPutReq.getMultipartFile());
        if (result == 0) return 1;
        return 2;
    }

    @Override
    @Transactional
    public void updateNotice(Study study, String newNotice) {
        study.updateNotice(newNotice);
    }

    @Override
    @Transactional
    public void updateEndDate(Study study, String newEndDate) {
        study.updateEndDate(newEndDate);
    }

    @Override
    @Transactional
    public int updateStdImg(Study study,  MultipartFile multipartFile) {
        try {
            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String preFileUrl = study.getStdImg();
            File file;
            if (preFileUrl != null) {
                file = new File(preFileUrl);
                if (file.exists()) {
                    file.delete();
                }
            }
            if (multipartFile.isEmpty()) { // 기본 이미지
                String fileUrl = "";
                study.updateStdImg(fileUrl);
                return 1;
            } else { // 업로드한 이미지
                String fileName = multipartFile.getOriginalFilename();
                String extension = FilenameUtils.getExtension(fileName).toLowerCase();
                UUID uuid = UUID.randomUUID();
                String destFileName = uuid + "." + extension;
                File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + destFileName);
                destFile.getParentFile().mkdirs();
                multipartFile.transferTo(destFile);
                // String fileUrl = "https://i7b105.p.ssafy.io:8080/static/" + uploadFolder + "/" + destFileName;
                String fileUrl = uploadPath + "/" + uploadFolder + "/" + destFileName;
                study.updateStdImg(fileUrl);
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional
    public void updateStdDay(Study study, String newStdDay) {
        study.updateStdDay(newStdDay);
    }

    @Override
    @Transactional
    public void recruitComplete(Recruit recruit) {
        recruit.recruitComplete();
    }

    @Override
    public StudyJoin findStudyJoin(Long stdNo, Long userNo) {
        return studyJoinRepositorySupport.findStudyJoinByUserNoAndStdNo(userNo, stdNo);
    }

    @Override
    @Transactional
    public void updateRegistedResume(StudyJoin studyJoin, Long resumeNo) {
        studyJoin.updateResumeNo(resumeNo);
    }

    @Override
    public long[] getDetailCounts(Long stdNo) {
        List<StudyJoin> studyJoins = studyJoinRepositorySupport.findByStdNo(stdNo);
        long[] detailCounts = new long[studyJoins.size()];
        int idx = 0;
        for (StudyJoin studyJoin : studyJoins) {
            detailCounts[idx++] = studyJoin.getResumeNo() == null ? 0 : resumeDetailRepositorySupport.CountByResumeNo(studyJoin.getResumeNo());
        }
        return detailCounts;
    }

    @Override
    public ApplyType getJoinType(Long userNo, Long stdNo) {
        return studyJoinRepositorySupport.findStudyJoinByUserNoAndStdNo(userNo, stdNo).getJoinType();
    }

    @Override
    @Transactional
    public int deleteStudy(Long stdNo) {
        try {
            Study study = studyRepository.findByStdNo(stdNo).get();
        } catch (Exception e) {
            return 0;
        }
        // 남아있는 스터디원 삭제 처리
        List<StudyJoin> studyJoins = studyJoinRepositorySupport.findByStdNo(stdNo);
        for (StudyJoin studyJoin : studyJoins) {
            studyJoinRepository.deleteByJoinNo(studyJoin.getJoinNo());
        }
        // 스터디 스페이스 삭제
        studyRepository.deleteByStdNo(stdNo);
        return 1;
    }

}