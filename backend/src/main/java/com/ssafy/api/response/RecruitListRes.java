package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Recruit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("RecruitListResponse")
public class RecruitListRes extends BaseResponseBody {

    @ApiModelProperty(name = "모집글 목록")
    List<Recruit> recruits;

    @ApiModelProperty(name = "신청자 수")
    long[] applyCounts;

    public static RecruitListRes of(List<Recruit> recruits, long[] applyCounts, Integer statusCode, String message) {
        RecruitListRes res = new RecruitListRes();
        res.setRecruits(recruits);
        res.setApplyCounts(applyCounts);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
