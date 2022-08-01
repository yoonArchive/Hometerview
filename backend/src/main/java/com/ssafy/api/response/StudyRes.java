package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ApiModel("StudyResponse")
public class StudyRes extends BaseResponseBody {

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

    @ApiModelProperty(name = "스터디 공지")
    String stdNotice;

    @ApiModelProperty(name = "스터디 참여 멤버")
    List<StudyJoin> studyJoins;

    @ApiModelProperty(name = "항목갯수")
    long[] detailCounts;

    @ApiModelProperty(name = "가입 타입")
    ApplyType joinType;

    public static StudyRes of(Study study, long[] detailCounts, ApplyType joinType, Integer statusCode, String message) {
        StudyRes res = new StudyRes();
        res.setStdName(study.getStdName());
        res.setStdDetail(study.getStdDetail());
        res.setStdImg(study.getStdImg());
        res.setStdType(study.getStdType());
        res.setComName(study.getComName());
        res.setStartDate(study.getStartDate());
        res.setEndDate(study.getEndDate());
        res.setStdDay(study.getStdDay());
        res.setStdLimit(study.getStdLimit());
        res.setStdNotice(study.getStdNotice());
        res.setStudyJoins(study.getStudyJoins());
        res.setDetailCounts(detailCounts);
        res.setJoinType(joinType);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
