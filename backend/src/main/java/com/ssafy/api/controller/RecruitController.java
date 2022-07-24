package com.ssafy.api.controller;

import com.ssafy.api.request.RecruitWritePostReq;
import com.ssafy.api.response.RecruitRes;
import com.ssafy.api.service.RecruitService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Recruit;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "모집글 API", tags = {"Recruits"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruits")
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping()
    @ApiOperation(value = "모집글 작성", notes = "스터디 모집글을 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "모집글 작성 성공"), @ApiResponse(code = 401, message = "모집글 작성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody @ApiParam(value = "모집글 정보", required = true) @Valid RecruitWritePostReq recruitWritePostReq) throws Exception {
        try {
            recruitService.writeRecruit(recruitWritePostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

    /*
    @GetMapping()
    @ApiOperation(value = "스터디 모집글 목록 조회", notes = "스터디 모집글 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<Posts> getPostsList() throws Exception {
        List<Posts> posts = postService.getList();
        return ResponseEntity.status(200).body(PostsListRes.of(posts, 200, "공지사항 목록 조회를 성공하였습니다."));
    }
     */

    @GetMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 상세조회", notes = "스터디 모집글 상세정보를 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 상세정보 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 상세정보 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> getRecruitDetail(@PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) {
        Recruit recruit = recruitService.getByRecruitNo(recruitNo);
        if (recruit == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "해당하는 모집글이 없습니다."));
        return ResponseEntity.status(200).body(RecruitRes.of(recruit, 200, "모집글 상세조회를 성공하였습니다."));
    }

    @DeleteMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 삭제", notes = "스터디 모집글을 삭제한다.")
    public ResponseEntity<?> deleteRecruit(@PathVariable Long recruitNo) {
        int result = recruitService.deleteRecruit(recruitNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 삭제가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "스터디 모집글 삭제에 실패하였습니다."));
    }

}
