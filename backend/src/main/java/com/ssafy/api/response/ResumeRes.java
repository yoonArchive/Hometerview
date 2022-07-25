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

    @NotNull
    @ApiModelProperty(name = "항목번호", example = "1")
    Long itemNo;

    @NotEmpty
    @ApiModelProperty(name = "항목내용", example = "항목내용입니다.")
    String item;

    @NotEmpty
    @ApiModelProperty(name = "답변", example = "답변입니다.")
    String answer;

    public static ResumeRes of(ResumeDetail resumeDetail, Integer statusCode, String message){
        ResumeRes res = new ResumeRes();
        res.setItemNo(resumeDetail.getItemNo());
        res.setItem(resumeDetail.getItem());
        res.setAnswer(resumeDetail.getAnswer());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
