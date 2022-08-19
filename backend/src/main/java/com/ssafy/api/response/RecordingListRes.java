package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.Recording;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("RecordingListResponse")
public class RecordingListRes extends BaseResponseBody {

    @ApiModelProperty(name = "녹화 리스트")
    List<Recording> recordings;

    public static RecordingListRes of(List<Recording> recordings, Integer statusCode, String message) {
        RecordingListRes res = new RecordingListRes();
        res.setRecordings(recordings);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}

