package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Dday;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("DdayResponse")
public class DdayListRes extends BaseResponseBody {

    @ApiModelProperty(name = "일정 목록")
    List<Dday> ddays;

    @ApiModelProperty(name = "디데이")
    long[] results;

    public static DdayListRes of(List<Dday> ddays, long[] results, Integer statusCode, String message) {
        DdayListRes res = new DdayListRes();
        res.setDdays(ddays);
        res.setResults(results);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
