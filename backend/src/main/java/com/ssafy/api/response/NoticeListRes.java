package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("NoticeListResponse")
public class NoticeListRes extends BaseResponseBody {

    @ApiModelProperty(name = "작성일자")
    List<Notice> notices;

    public static NoticeListRes of(List<Notice> notices, Integer statusCode, String message) {
        NoticeListRes res = new NoticeListRes();
        res.setNotices(notices);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}

