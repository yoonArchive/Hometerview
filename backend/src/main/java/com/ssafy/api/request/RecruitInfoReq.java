package com.ssafy.api.request;

import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("RecruitInfoRequest")
public class RecruitInfoReq {

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.recruitTitle", example = "모집글 제목입니다.")
    String recruitTitle;

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.stdName", example = "스터디 이름입니다.")
    String stdName;

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.stdDetail", example = "스터디 설명입니다.")
    String stdDetail;

    @ValidEnum(enumClass = StdType.class)
    @ApiModelProperty(name = "recruitInfoReq.stdType", example = "COM")
    StdType stdType;

    @ApiModelProperty(name = "recruitInfoReq.comName", example = "기업명입니다.")
    String comName;

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.startDate", example = "2022-08-01")
    String startDate;

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.endDate", example = "2022-08-10")
    String endDate;

    @NotEmpty
    @ApiModelProperty(name = "recruitInfoReq.stdDay", example = "진행일시입니다.")
    String stdDay;

    @ApiModelProperty(name = "recruitInfoReq.stdLimit", example = "5")
    int stdLimit;

}
