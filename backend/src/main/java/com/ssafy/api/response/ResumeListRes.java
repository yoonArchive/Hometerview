package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.Resume;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ResumeListResponse")
public class ResumeListRes extends BaseResponseBody {

    @ApiModelProperty(name = "작성일자")
    List<Resume> resumes;

    public static ResumeListRes of(List<Resume> resumes, Integer statusCode, String message) {
        ResumeListRes res = new ResumeListRes();
        res.setResumes(resumes);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}

