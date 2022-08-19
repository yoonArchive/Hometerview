package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("UpdateStdImgPutRequest")
public class UpdateStdImgPutReq {

    @ApiModelProperty(name = "multipartFile")
    MultipartFile multipartFile;

}
