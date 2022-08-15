package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NoticeWritePostRequest")
public class RecordingPostReq {

    @ApiModelProperty(name = "회원아이디", example = "회원아이디 입니다.")
    String userId;

    @ApiModelProperty(name = "비디오 주소", example = "비디오 주소입니다.")
    String videoUrl;

}
