package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Recruit;
import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecruitResponse")
public class RecruitRes extends BaseResponseBody {

    @ApiModelProperty(name = "모집글 제목", example = "모집글 제목입니다.")
    String recruitTitle;

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

    public static RecruitRes of(Recruit recruit, Integer statusCode, String message) {
        RecruitRes res = new RecruitRes();
        res.setRecruitTitle(recruit.getRecruitTitle());
        res.setStdName(recruit.getStdName());
        res.setStdDetail(res.getStdDetail());
        res.setStdImg(res.getStdImg());
        res.setStdType(res.getStdType());
        res.setComName(res.getComName());
        res.setStartDate(res.getStartDate());
        res.setEndDate(res.getEndDate());
        res.setStdDay(res.getStdDay());
        res.setStdLimit(res.getStdLimit());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }


}
