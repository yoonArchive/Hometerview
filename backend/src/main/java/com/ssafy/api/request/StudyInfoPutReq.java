package com.ssafy.api.request;

import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("StudyPutRequest")
public class StudyInfoPutReq {

    @NotEmpty
    @ApiModelProperty(name = "스터디 이름", example = "스터디 이름입니다.")
    String stdName;

    @NotEmpty
    @ApiModelProperty(name = "스터디 설명", example = "스터디 설명입니다.")
    String stdDetail;

    @ValidEnum(enumClass = StdType.class)
    @ApiModelProperty(name = "스터디 카테고리", example = "COM")
    StdType stdType;

    @ApiModelProperty(name = "기업명", example = "기업명입니다.")
    String comName;

    @NotEmpty
    @ApiModelProperty(name = "시작일", example = "2022-08-01")
    String startDate;

    @NotEmpty
    @ApiModelProperty(name = "종료일", example = "2022-08-10")
    String endDate;

    @NotEmpty
    @ApiModelProperty(name = "진행일시", example = "진행일시입니다.")
    String stdDay;

    @NotNull
    @ApiModelProperty(name = "정원", example = "5")
    int stdLimit;

    @ApiModelProperty(name = "스터디 공지", example = "공지사항입니다.")
    String stdNotice;

}
