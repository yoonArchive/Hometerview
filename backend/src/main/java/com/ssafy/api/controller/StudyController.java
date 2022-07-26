package com.ssafy.api.controller;

import com.ssafy.api.response.StudyListRes;
import com.ssafy.api.response.StudyRes;
import com.ssafy.api.service.StudyService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Study;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    //내 스터디 조회
    @GetMapping()
    @ApiOperation(value = "내 스터디 조회", notes = "내 스터디 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 목록 조회 성공"), @ApiResponse(code = 401, message = "스터디 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<StudyListRes> studyList(@ApiIgnore Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Study> studyList = studyService.getStudyList(userNo);
        return ResponseEntity.status(200).body(StudyListRes.of(studyList,200, "스터디 목록 조회를 성공하였습니다."));
    }

    //스터디 상세 조회
    @GetMapping("/{stdNo}")
    @ApiOperation(value = "스터디 상세 조회", notes = "스터디 상세정보를 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 상세 조회 성공"), @ApiResponse(code = 401, message = "스터디 상세 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> studyDetail(@PathVariable Long stdNo) throws Exception{
        Study study;
        try {
            study = studyService.detailStudy(stdNo);
        } catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 상세 조회를 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(StudyRes.of(study,200, "스터디 상세 조회를 성공하였습니다."));
    }

    // 스터디 탈퇴
    @DeleteMapping("/{stdNo}")
    @ApiOperation(value = "스터디 탈퇴", notes = "스터디에서 탈퇴한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 탈퇴 성공"), @ApiResponse(code = 401, message = "스터디 탈퇴 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> leaveStudy(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        int result = studyService.leaveStudy(userNo, stdNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 탈퇴가 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 탈퇴에 실패하였습니다."));
    }

}