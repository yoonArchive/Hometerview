package com.ssafy.api.request;

import com.ssafy.common.util.ValidEnum;
import com.ssafy.db.entity.ReviewType;
import com.ssafy.db.entity.StdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("ReviewRequest")
public class ReviewReq {

    @NotEmpty
    @ApiModelProperty(name = "회고 제목", example = "회고 제목입니다.")
    String reviewTitle;

    @NotEmpty
    @ApiModelProperty(name = "회고 내용", example = "회고 내용입니다.")
    String reviewContents;

    @ValidEnum(enumClass = ReviewType.class)
    @ApiModelProperty(name = "회고 타입", example = "REAL")
    ReviewType reviewType;

    @NotEmpty
    @ApiModelProperty(name = "회고 일자", example = "2022-08-01")
    String reviewDate;

}
