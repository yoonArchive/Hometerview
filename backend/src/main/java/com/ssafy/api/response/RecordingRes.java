package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Recording;
import com.ssafy.db.entity.ResumeDetail;
import com.ssafy.db.entity.Study;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecordingResponse")
public class RecordingRes extends BaseResponseBody {

    @ApiModelProperty(name = "녹화번호")
    Long recordNo;

    @ApiModelProperty(name = "스터디 정보")
    Study study;

    @ApiModelProperty(name = "저장일시")
    String saveDate;

    @ApiModelProperty(name = "녹화URL")
    String videoUrl;

    public static RecordingRes of(Recording recording, Integer statusCode, String message) {
        RecordingRes res = new RecordingRes();
        res.setRecordNo(recording.getRecordNo());
        res.setStudy(recording.getStudy());
        res.setSaveDate(recording.getSaveDate());
        res.setVideoUrl(recording.getVideoUrl());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
