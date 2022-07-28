package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.Study;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("StudyListResponse")
public class StudyListRes extends BaseResponseBody {

    @ApiModelProperty(name = "내 스터디 목록")
    List<Study> studies;

    public static StudyListRes of(List<Study> studies, Integer statusCode, String message) {
        StudyListRes res = new StudyListRes();
        res.setStudies(studies);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
