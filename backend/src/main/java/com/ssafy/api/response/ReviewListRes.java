package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Review;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ReviewListResponse")
public class ReviewListRes extends BaseResponseBody {

    @ApiModelProperty(name = "작성일자")
    List<Review> reviews;

    public static ReviewListRes of(List<Review> reviews, Integer statusCode, String message) {
        ReviewListRes res = new ReviewListRes();
        res.setReviews(reviews);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
