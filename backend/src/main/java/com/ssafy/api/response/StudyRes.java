package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.StdType;
import com.ssafy.db.entity.Study;
import com.ssafy.db.entity.StudyJoin;
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

    @ApiModelProperty(name = "스터디 이름", example = "스터디 이름입니다.")
    String stdName;

    @ApiModelProperty(name = "스터디 설명", example = "스터디 설명입니다.")
    String stdDetail;

    @ApiModelProperty(name = "썸네일 이미지", example = "썸네일 이미지입니다.")
    String stdImg;

    @ApiModelProperty(name = "스터디 카테고리", example = "COM")
    StdType stdType;

    @ApiModelProperty(name = "기업명", example = "기업명입니다.")
    String comName;

    @ApiModelProperty(name = "시작일", example = "2022-08-01")
    String startDate;

    @ApiModelProperty(name = "종료일", example = "2022-08-10")
    String endDate;

    @ApiModelProperty(name = "진행일시", example = "진행일시입니다.")
    String stdDay;

    @ApiModelProperty(name = "정원", example = "5")
    int stdLimit;

    @ApiModelProperty(name = "스터디 공지", example = "공지사항입니다.")
    String stdNotice;

    @ApiModelProperty(name = "스터디 참여 멤버", example = "공지사항입니다.")
    List<StudyJoin> studyJoins;

    public static StudyRes of(Study study, Integer statusCode, String message) {
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
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
