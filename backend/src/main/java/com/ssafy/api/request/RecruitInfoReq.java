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
@ApiModel("RecruitRequest")
public class RecruitInfoReq {

    @NotEmpty
    @ApiModelProperty(name = "recruitTitle", example = "모집글 제목입니다.")
    String recruitTitle;

    @NotEmpty
    @ApiModelProperty(name = "stdName", example = "스터디 이름입니다.")
    String stdName;

    @NotEmpty
    @ApiModelProperty(name = "stdDetail", example = "스터디 설명입니다.")
    String stdDetail;

    @ValidEnum(enumClass = StdType.class)
    @ApiModelProperty(name = "stdType", example = "COM")
    StdType stdType;

    @ApiModelProperty(name = "comName", example = "기업명입니다.")
    String comName;

    @NotEmpty
    @ApiModelProperty(name = "startDate", example = "2022-08-01")
    String startDate;

    @NotEmpty
    @ApiModelProperty(name = "endDate", example = "2022-08-10")
    String endDate;

    @NotEmpty
    @ApiModelProperty(name = "stdDay", example = "진행일시입니다.")
    String stdDay;

    @ApiModelProperty(name = "stdLimit", example = "5")
    int stdLimit;

}
