package com.ssafy.api.controller;

import com.ssafy.api.request.RecruitReq;
import com.ssafy.api.response.ApplyListRes;
import com.ssafy.api.response.RecruitListRes;
import com.ssafy.api.response.RecruitRes;
import com.ssafy.api.service.ApplyService;
import com.ssafy.api.service.RecruitService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ApplyType;
import com.ssafy.db.entity.Recruit;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(value = "모집글 API", tags = {"Recruits"})
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recruits")
public class RecruitController {

    private final RecruitService recruitService;

    private final ApplyService applyService;

    @PostMapping()
    @ApiOperation(value = "모집글 작성", notes = "(token) 스터디 모집글을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "모집글 작성 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "모집글 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> register(@ApiIgnore Authentication authentication,
                                                               @ModelAttribute @Valid RecruitReq recruitReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Recruit recruit;
        try {
            recruit = recruitService.writeRecruit(recruitReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 작성에 실패하였습니다."));
        }
        Long recruitNo = recruit.getRecruitNo();
        applyService.applyRecruit(userNo, recruitNo, ApplyType.LEADER);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

    @GetMapping()
    @ApiOperation(value = "스터디 모집글 목록 조회", notes = "스터디 모집글 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공", response = RecruitListRes.class),
            @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<RecruitListRes> getRecruitList() throws Exception {
        List<Recruit> recruits = recruitService.getList();
        long[] applyCounts = applyService.getApplyCounts(recruits);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruits, applyCounts, 200, "스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/recruiting")
    @ApiOperation(value = "모집 중 스터디 모집글 목록 조회", notes = "모집 현황이 모집 중인 스터디 모집글 목록을 조회한다. (전체: 1, 기업: 2, 자율:3) ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공", response = RecruitListRes.class),
            @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<RecruitListRes> getRecruitingList(@RequestParam int type) throws Exception {
        List<Recruit> recruitings = recruitService.getRecruitingList(type);
        long[] applyCounts = applyService.getApplyCounts(recruitings);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruitings, applyCounts, 200, "모집 중인 스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/applying")
    @ApiOperation(value = "신청 중 스터디 모집글 목록 조회", notes = "(token) 신청 현황이 신청 중인 스터디 모집글 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공", response = ApplyListRes.class),
            @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ApplyListRes> getApplingList(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Recruit> applyings = recruitService.getApplyingList(userNo);
        return ResponseEntity.status(200).body(ApplyListRes.of(applyings, 200, "신청 중인 스터디 모집글 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/type") // 전체: 1, 기업: 2, 자율: 3
    @ApiOperation(value = "전체, 기업, 자율 스터디 필터링", notes = "스터디 모집글 목록에서 전체, 기업, 자율 스터디를 필터링한다. (전체: 1, 기업: 2, 자율:3) ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "필터링 성공", response = RecruitListRes.class),
            @ApiResponse(code = 401, message = "필터링 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<RecruitListRes> getFilteredList(@RequestParam int type) throws Exception {
        List<Recruit> recruits = recruitService.getFilteredList(type);
        long[] applyCounts = applyService.getApplyCounts(recruits);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruits, applyCounts, 200, "필터링 된 결과입니다."));
    }

    @GetMapping("/search")
    @ApiOperation(value = "모집글 검색", notes = "모집글 제목 또는 기업명으로 모집글을 검색한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "검색 성공", response = RecruitListRes.class),
            @ApiResponse(code = 401, message = "검색 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<RecruitListRes> searchRecruit(@RequestParam String keyword) throws Exception {
        List<Recruit> recruits = recruitService.search(keyword);
        long[] applyCounts = applyService.getApplyCounts(recruits);
        return ResponseEntity.status(200).body(RecruitListRes.of(recruits, applyCounts, 200, "검색 결과입니다."));
    }

    @GetMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 상세조회", notes = "스터디 모집글 상세정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 모집글 상세정보 조회 성공", response = RecruitRes.class),
            @ApiResponse(code = 401, message = "스터디 모집글 상세정보 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getRecruitDetail(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Recruit recruit = recruitService.getByRecruitNo(recruitNo);
        if (recruit == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "해당하는 스터디 모집글이 없습니다."));
        long count = applyService.getApplyCount(recruit);
        ApplyType applyType = applyService.getApplyType(recruit, userNo);
        return ResponseEntity.status(200).body(RecruitRes.of(recruit, count, applyType, 200, "스터디 모집글 상세조회를 성공하였습니다."));
    }

    @PutMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 수정", notes = "모집글 내용을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "모집글 수정 성공", response = RecruitRes.class),
            @ApiResponse(code = 401, message = "모집글 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "썸네일 이미지 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 403, message = "해당 모집글 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateRecruit(@ApiIgnore Authentication authentication,
                                                                    @PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo,
                                                                    @ModelAttribute @Valid RecruitReq recruitReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Recruit recruit = recruitService.getByRecruitNo(recruitNo);
        if (recruit == null)
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "해당하는 스터디 모집글이 없습니다."));
        int result = recruitService.updateRecruit(recruit, recruitReq);
        if (result == 0) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 수정에 실패하였습니다."));
        else if (result == 1) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "썸네일 이미지 수정에 실패하였습니다."));
        Recruit updatedRecruit = recruitService.getByRecruitNo(recruitNo);
        long count = applyService.getApplyCount(recruit);
        ApplyType applyType = applyService.getApplyType(recruit, userNo);
        return ResponseEntity.status(200).body(RecruitRes.of(updatedRecruit, count, applyType, 200, "스터디 모집글이 수정되었습니다."));
    }

    @DeleteMapping("/{recruitNo}")
    @ApiOperation(value = "모집글 삭제", notes = "스터디 모집글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "모집글 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "모집글 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteRecruit(@PathVariable @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        int result = recruitService.deleteRecruit(recruitNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 삭제가 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 삭제에 실패하였습니다."));
    }

}
