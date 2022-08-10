package com.ssafy.api.service;

import com.ssafy.api.request.RecruitInfoReq;
import com.ssafy.api.request.RecruitReq;
import com.ssafy.db.entity.Recruit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecruitService {

    Recruit writeRecruit(RecruitReq recruitReq);

    List<Recruit> getList();

    List<Recruit> getRecruitingList(int type);

    List<Recruit> getApplyingList(Long userNo);

    List<Recruit> getFilteredList(int type);

    List<Recruit> search(String keyword);

    Recruit getByRecruitNo(Long recruitNo);

    int updateRecruit(Recruit recruit, RecruitReq recruitReq);

    int deleteRecruit(Long recruitNo);

    int updateStdImg(Recruit recruit, MultipartFile multipartFile);

}
