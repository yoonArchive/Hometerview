package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ResumeDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("ResumeResponse")
public class ResumeRes extends BaseResponseBody {

    @ApiModelProperty(name = "상세번호")
    Long detailNo;

    @ApiModelProperty(name = "항목번호")
    Long itemNo;

    @ApiModelProperty(name = "항목내용")
    String item;

    @ApiModelProperty(name = "답변")
    String answer;

    public static ResumeRes of(ResumeDetail resumeDetail, Integer statusCode, String message) {
        ResumeRes res = new ResumeRes();
        res.setDetailNo(resumeDetail.getDetailNo());
        res.setItemNo(resumeDetail.getItemNo());
        res.setItem(resumeDetail.getItem());
        res.setAnswer(resumeDetail.getAnswer());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
