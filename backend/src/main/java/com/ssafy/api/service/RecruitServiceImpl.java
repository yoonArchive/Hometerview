package com.ssafy.api.service;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.db.entity.Apply;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import com.ssafy.db.entity.StdType;
import com.ssafy.db.repository.ApplyRepositorySupport;
import com.ssafy.db.repository.RecruitRepository;
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
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;

    private final ApplyRepositorySupport applyRepositorySupport;

    String uploadPath = "C:\\intervenience";

    String uploadFolder = "stdImg";

    /*String uploadFolder = "upload";

    String uploadPath = "/home" + File.separator + "ubuntu" + File.separator + "S05P13A204"
            + File.separator + "backend"
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static";*/

    @Override
    public Recruit writeRecruit(RecruitReq recruitReq) {
        Recruit recruit = Recruit.builder()
                .recruitTitle(recruitReq.getRecruitInfoReq().getRecruitTitle())
                .stdName(recruitReq.getRecruitInfoReq().getStdName())
                .stdDetail(recruitReq.getRecruitInfoReq().getStdDetail())
                .stdType(recruitReq.getRecruitInfoReq().getStdType())
                .comName(recruitReq.getRecruitInfoReq().getComName())
                .startDate(recruitReq.getRecruitInfoReq().getStartDate())
                .endDate(recruitReq.getRecruitInfoReq().getEndDate())
                .stdDay(recruitReq.getRecruitInfoReq().getStdDay())
                .stdLimit(recruitReq.getRecruitInfoReq().getStdLimit())
                .recruitStatus(RecruitStatus.RECRUITING)
                .build();
        int result = updateStdImg(recruit, recruitReq.getMultipartFile());
        if (result == 0) return null;
        return recruitRepository.save(recruit);
    }

    @Override
    public List<Recruit> getList() {
        return recruitRepository.findAllByOrderByRecruitNoDesc();
    }

    @Override
    public List<Recruit> getRecruitingList(int type) {
        if (type == 1) {
            return recruitRepository.findAllByRecruitStatusOrderByRecruitNoDesc(RecruitStatus.RECRUITING);
        } else if (type == 2) {
            return recruitRepository.findAllByRecruitStatusAndStdTypeOrderByRecruitNoDesc(RecruitStatus.RECRUITING, StdType.COM);
        } else {
            return recruitRepository.findAllByRecruitStatusAndStdTypeOrderByRecruitNoDesc(RecruitStatus.RECRUITING, StdType.FREE);
        }
    }

    @Override
    public List<Recruit> getApplyingList(Long userNo) {
        List<Apply> applies = applyRepositorySupport.findAllByUserNo(userNo);
        List<Recruit> recruits = new ArrayList<>();
        for (Apply apply : applies) {
            recruits.add(recruitRepository.findByRecruitNo(apply.getRecruit().getRecruitNo()).get());
        }
        return recruits;
    }

    @Override
    public List<Recruit> getFilteredList(int type) {
        if (type == 1) {
            return recruitRepository.findAllByOrderByRecruitNoDesc();
        } else if (type == 2) {
            return recruitRepository.findAllByStdTypeOrderByRecruitNoDesc(StdType.COM);
        } else {
            return recruitRepository.findAllByStdTypeOrderByRecruitNoDesc(StdType.FREE);
        }
    }

    @Override
    public List<Recruit> search(String keyword) {
        return recruitRepository.findByRecruitTitleContainingIgnoreCaseOrComNameContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public Recruit getByRecruitNo(Long recruitNo) {
        return recruitRepository.findByRecruitNo(recruitNo).orElse(null);
    }

    @Override
    @Transactional
    public int updateRecruit(Recruit recruit, RecruitReq recruitReq) {
        String recruitTitle = recruitReq.getRecruitInfoReq().getRecruitTitle();
        String stdName = recruitReq.getRecruitInfoReq().getStdName();
        String stdDetail = recruitReq.getRecruitInfoReq().getStdDetail();
        StdType stdType = recruitReq.getRecruitInfoReq().getStdType();
        String comName = recruitReq.getRecruitInfoReq().getComName();
        String startDate = recruitReq.getRecruitInfoReq().getStartDate();
        String endDate = recruitReq.getRecruitInfoReq().getEndDate();
        String stdDay = recruitReq.getRecruitInfoReq().getStdDay();
        int stdLimit = recruitReq.getRecruitInfoReq().getStdLimit();
        try {
            recruit.initRecruit(recruitTitle, stdName, stdDetail, stdType, comName, startDate, endDate, stdDay, stdLimit);
        } catch (Exception e) {
            return 0;
        }
        int result = updateStdImg(recruit, recruitReq.getMultipartFile());
        if (result == 0) return 1;
        return 2;
    }

    @Override
    @Transactional
    public int deleteRecruit(Long recruitNo) {
        try {
            recruitRepository.findByRecruitNo(recruitNo).get();
        } catch (Exception e) {
            return 0;
        }
        recruitRepository.deleteByRecruitNo(recruitNo);
        return 1;
    }

    @Override
    public int updateStdImg(Recruit recruit, MultipartFile multipartFile) {
        try {
            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String preFileUrl = recruit.getStdImg();
            File file;
            if (preFileUrl != null) {
                file = new File(preFileUrl);
                if (file.exists()) {
                    file.delete();
                }
            }
            if (multipartFile.isEmpty()) { // 기본 이미지
                String fileUrl = "";
                recruit.updateStdImg(fileUrl);
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
                recruit.updateStdImg(fileUrl);
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }

}
