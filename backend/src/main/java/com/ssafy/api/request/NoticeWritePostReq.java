package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel("NoticeWritePostRequest")
public class NoticeWritePostReq {

    @ApiModelProperty(name = "공지사항 제목", example = "공지사항 제목입니다.")
    String noticeTitle;

    @ApiModelProperty(name = "공지사항 내용", example = "공지사항 내용입니다.")
    String noticeContents;

}
