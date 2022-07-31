package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ApplyType;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.RecruitStatus;
import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecruitResponse")
public class RecruitRes extends BaseResponseBody {

    @ApiModelProperty(name = "모집글 제목")
    String recruitTitle;

    @ApiModelProperty(name = "스터디 이름")
    String stdName;

    @ApiModelProperty(name = "스터디 설명")
    String stdDetail;

    @ApiModelProperty(name = "썸네일 이미지")
    String stdImg;

    @ApiModelProperty(name = "스터디 카테고리")
    StdType stdType;

    @ApiModelProperty(name = "기업명")
    String comName;

    @ApiModelProperty(name = "시작일")
    String startDate;

    @ApiModelProperty(name = "종료일")
    String endDate;

    @ApiModelProperty(name = "진행일시")
    String stdDay;

    @ApiModelProperty(name = "정원")
    int stdLimit;

    @ApiModelProperty(name = "모집 현황")
    String recruitStatus;

    @ApiModelProperty(name = "신청자 수")
    long count;

    @ApiModelProperty(name = "신청 타입")
    ApplyType applyType;

    public static RecruitRes of(Recruit recruit, long count, ApplyType applyType, Integer statusCode, String message) {
        RecruitRes res = new RecruitRes();
        res.setRecruitTitle(recruit.getRecruitTitle());
        res.setStdName(recruit.getStdName());
        res.setStdDetail(recruit.getStdDetail());
        res.setStdImg(recruit.getStdImg());
        res.setStdType(recruit.getStdType());
        res.setComName(recruit.getComName());
        res.setStartDate(recruit.getStartDate());
        res.setEndDate(recruit.getEndDate());
        res.setStdDay(recruit.getStdDay());
        res.setStdLimit(recruit.getStdLimit());
        res.setRecruitStatus(recruit.getRecruitStatus().getStatus());
        res.setCount(count);
        res.setApplyType(applyType);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }


}
