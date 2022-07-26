package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Review;
import com.ssafy.db.entity.ReviewType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("ReviewResponse")
public class ReviewRes extends BaseResponseBody {

    @ApiModelProperty(name = "회고 번호")
    long reviewNo;

    @ApiModelProperty(name = "회고 제목")
    String reviewTitle;

    @ApiModelProperty(name = "회고 내용")
    String reviewContents;

    @ApiModelProperty(name = "회고 타입")
    ReviewType reviewType;

    @ApiModelProperty(name = "작성일자")
    String writeDate;

    public static ReviewRes of(Review review, Integer statusCode, String message) {
        ReviewRes res = new ReviewRes();
        res.setReviewNo(review.getReviewNo());
        res.setReviewTitle(review.getReviewTitle());
        res.setReviewContents(review.getReviewContents());
        res.setReviewType(review.getReviewType());
        res.setWriteDate(review.getWriteDate());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
