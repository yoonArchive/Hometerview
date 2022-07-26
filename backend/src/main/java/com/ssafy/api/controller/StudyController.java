package com.ssafy.api.controller;

import com.ssafy.api.service.StudyService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "스터디 API", tags = {"Study"})
@RestController
@RequestMapping("/api/v1/study")
public class StudyController {

    @Autowired
    StudyService studyService;

    //스터디 생성
    @PostMapping()
    @ApiOperation(value = "스터디 생성", notes = "스터디를 생성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 생성 성공"), @ApiResponse(code = 401, message = "스터디 생성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> createStudy(@RequestParam @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception{
        try {
            studyService.createStudy(recruitNo);
        } catch (Exception e){
            return ResponseEntity.status(200).body(BaseResponseBody.of(401, "스터디 생성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 생성에 성공하였습니다."));
    }
}
