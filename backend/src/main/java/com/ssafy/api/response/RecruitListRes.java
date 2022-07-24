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

    public static RecruitListRes of(List<Recruit> recruits, Integer statusCode, String message) {
        RecruitListRes res = new RecruitListRes();
        res.setRecruits(recruits);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
