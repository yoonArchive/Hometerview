package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Dday;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("DdayResponse")
public class DdayRes extends BaseResponseBody {

    @ApiModelProperty(name = "D-day 제목")
    String ddayTitle;

    @ApiModelProperty(name = "D-day 날짜")
    String ddayDate;

    public static DdayRes of(Dday dday, Integer statusCode, String message) {
        DdayRes res = new DdayRes();
        res.setDdayTitle(dday.getDdayTitle());
        res.setDdayDate(dday.getDdayDate());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
