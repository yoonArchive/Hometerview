package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("UpdateStudyPutRequest")
public class StudyPutReq {

    @ApiModelProperty(name = "스터디 정보 객체")
    StudyInfoPutReq studyInfoPutReq;

    @ApiModelProperty(name = "multipartFile")
    MultipartFile multipartFile;

}
