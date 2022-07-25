package com.ssafy.api.controller;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.api.response.RecruitListRes;
import com.ssafy.api.response.RecruitRes;
import com.ssafy.api.service.ApplyService;
import com.ssafy.api.service.RecruitService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Recruit;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "모집글 API", tags = {"Recruits"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruits")
public class RecruitController {

    private final RecruitService recruitService;

    private final ApplyService applyService;

    @PostMapping()
    @ApiOperation(value = "모집글 작성", notes = "스터디 모집글을 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "모집글 작성 성공"), @ApiResponse(code = 401, message = "모집글 작성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody @ApiParam(value = "모집글 정보", required = true) @Valid RecruitReq recruitReq) throws Exception {
        try {
            recruitService.writeRecruit(recruitReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

    @GetMapping()
    @ApiOperation(value = "스터디 모집글 목록 조회", notes = "스터디 모집글 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<RecruitListRes> getRecruitList() throws Exception {
        List<Recruit> recruits = recruitService.getList();
        long[] applyCounts = applyService.getApplyCount(recruits);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruits, applyCounts, 200, "스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/recruiting")
    @ApiOperation(value = "모집 중 스터디 모집글 목록 조회", notes = "모집 현황이 모집 중인 스터디 모집글 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<RecruitListRes> getRecruitingList() throws Exception {
        List<Recruit> recruitings = recruitService.getRecruitingList();
        long[] applyCounts = applyService.getApplyCount(recruitings);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitings, applyCounts, 200, "모집 중인 스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/type") // 전체: 1, 기업: 2, 자율: 3
    @ApiOperation(value = "전체, 기업, 자율 스터디 필터링", notes = "스터디 모집글 목록에서 전체, 기업, 자율 스터디를 필터링한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "필터링 성공"), @ApiResponse(code = 401, message = "필터링 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<RecruitListRes> getFilteredList(@RequestParam int type) throws Exception {
        List<Recruit> recruits = recruitService.getFilteredList(type);
        long[] applyCounts = applyService.getApplyCount(recruits);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruits, applyCounts, 200, "필터링 된 결과입니다."));
    }

    @GetMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 상세조회", notes = "스터디 모집글 상세정보를 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 상세정보 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 상세정보 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> getRecruitDetail(@PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        Recruit recruit = recruitService.getByRecruitNo(recruitNo);
        if (recruit == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "해당하는 스터디 모집글이 없습니다."));
        return ResponseEntity.status(200).body(RecruitRes.of(recruit, 200, "스터디 모집글 상세조회를 성공하였습니다."));
    }

    @PutMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 수정", notes = "모집글 내용을 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "모집글 수정 성공"), @ApiResponse(code = 401, message = "모집글 수정 실패"), @ApiResponse(code = 402, message = "해당 모집글 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateRecruit(@PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo, @RequestBody @ApiParam(value = "모집글 변경 내용", required = true) RecruitReq recruitReq) {
        Recruit recruit = recruitService.getByRecruitNo(recruitNo);
        if (recruit == null) return ResponseEntity.status(401).body(BaseResponseBody.of(402, "해당하는 스터디 모집글이 없습니다."));
        try {
            recruitService.updateRecruit(recruit, recruitReq);
            Recruit updatedRecruit = recruitService.getByRecruitNo(recruitNo);
            return ResponseEntity.status(200).body(RecruitRes.of(updatedRecruit, 200, "스터디 모집글이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 수정에 실패하였습니다."));
        }
    }

    @DeleteMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 삭제", notes = "스터디 모집글을 삭제한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "모집글 삭제 성공"), @ApiResponse(code = 401, message = "모집글 삭제 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteRecruit(@PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        int result = recruitService.deleteRecruit(recruitNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 삭제가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "스터디 모집글 삭제에 실패하였습니다."));
    }

}
