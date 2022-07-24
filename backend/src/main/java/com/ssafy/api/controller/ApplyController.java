package com.ssafy.api.controller;

import com.ssafy.api.service.ApplyService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@Api(value = "스터디 모집 API", tags = {"Apply"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/{recruitNo}")
    @ApiOperation(value = "모집 신청", notes = "스터디 모집글에 가입 신청한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "신청 완료"), @ApiResponse(code = 401, message = "신청 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> applyRecruit(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            applyService.applyRecruit(userNo, recruitNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집 신청에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집 신청이 완료되었습니다."));
    }

    @DeleteMapping("/{recruitNo}")
    @ApiOperation(value = "모집 신청 취소", notes = "스터디 모집 신청을 취소한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "취소 완료"), @ApiResponse(code = 401, message = "취소 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteApply(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        int result = applyService.deleteApply(userNo, recruitNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "모집 신청 취소가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "모집 신청 취소에 실패하였습니다."));
    }

}
