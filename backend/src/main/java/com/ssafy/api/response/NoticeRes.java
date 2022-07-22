package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NoticeResponse")
public class NoticeRes extends BaseResponseBody {

    @ApiModelProperty(name = "공지사항 번호")
    long noticeNo;

    @ApiModelProperty(name = "공지사항 제목")
    String noticeTitle;

    @ApiModelProperty(name = "공지사항 내용")
    String noticeContents;

    @ApiModelProperty(name = "작성일자")
    String writeDate;

    public static NoticeRes of(Notice notice, Integer statusCode, String message) {
        NoticeRes res = new NoticeRes();
        res.setNoticeNo(notice.getNoticeNo());
        res.setNoticeTitle(notice.getNoticeTitle());
        res.setNoticeContents(notice.getNoticeContents());
        res.setWriteDate(notice.getWriteDate());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
