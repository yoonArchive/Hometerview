package com.ssafy.api.controller;

import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.api.response.NoticeRes;
import com.ssafy.api.response.ResumeListRes;
import com.ssafy.api.response.ResumeRes;
import com.ssafy.api.service.ResumeService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(value = "자기소개서 API", tags = {"Resume"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping()
    @ApiOperation(value = "자기소개서 생성", notes = "자기소개서를 생성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 생성 성공"), @ApiResponse(code = 401, message = "자기소개서 생성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> createResume(@ApiIgnore Authentication authentication,
                                                                   @RequestParam @ApiParam(value = "자기소개서 제목", required = true) String resumeTitle) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            resumeService.createResume(userNo, resumeTitle);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 생성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 생성에 성공하였습니다."));
    }

    @GetMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 목록 조회 성공"), @ApiResponse(code = 401, message = "자기소개서 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    @ApiOperation(value = "자기소개서 목록 조회", notes = "자기소개서를 목록을 조회한다.")
    public ResponseEntity<ResumeListRes> listResume(@ApiIgnore Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Resume> resumes = resumeService.listResume(userNo);
        return ResponseEntity.status(200).body(ResumeListRes.of(resumes,200, "자기소개서 생성에 성공하였습니다."));
    }

    @PutMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 제목 수정 성공"), @ApiResponse(code = 401, message = "자기소개서 제목 수정 실패"), @ApiResponse(code = 402, message = "해당 자기소개서 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    @ApiOperation(value = "자기소개서 제목 수정", notes = "자기소개서 제목을 수정한다.")
    public ResponseEntity<?> updateResume(@RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo,
                                          @RequestParam @ApiParam(value = "자기소개서 제목", required = true) String resumeTitle){
        Resume resume = resumeService.getByResumeNo(resumeNo);
        if(resume == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        try{
            resumeService.updateResume(resume, resumeTitle);
        }catch (Exception e) {
           return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소게서 제목 수정에 실패했습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소게서 제목 수정에 성공했습니다."));
    }

    @DeleteMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 삭제 성공"), @ApiResponse(code = 401, message = "자기소개서 삭제 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    @ApiOperation(value = "자기소개서 삭제", notes = "자기소개서를 삭제한다.")
    public ResponseEntity<?> deleteResume(@RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo){
        int result = resumeService.deleteResume(resumeNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 삭제에 성공하였습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 생성에 실패하였습니다."));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "자기소개서 상세 작성", notes = "자기소개서를 작성한다..")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 작성 성공"), @ApiResponse(code = 401, message = "자기소개서 작성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> writeResumeDetail(@RequestBody @ApiParam(value = "자기소개서 상세 내용", required = true) @Valid ResumeWritePostReq resumeWritePostReq) throws Exception {
        try {
            resumeService.writeResumeDetail(resumeWritePostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 작성에 성공하였습니다."));
    }

    @GetMapping("/detail")
    @ApiOperation(value = "자기소개서 상세 조회", notes = "자기소개서를 상세 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 조회 성공"), @ApiResponse(code = 401, message = "자기소개서 상세 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> detailResumeDetail(@RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo, @RequestParam @ApiParam(value = "항목번호", required = true) Long itemNo) throws Exception {
        ResumeDetail resumeDetail = resumeService.getByResumeNoAndItemNo(resumeNo, itemNo);
        if (resumeDetail == null)
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세조회에 실패하였습니다."));
        return ResponseEntity.status(200).body(ResumeRes.of(resumeDetail, 200, "자기소개서 상세조회에 성공하였습니다."));
    }

    @PutMapping("/{detailno}")
    @ApiOperation(value = "자기소개서 상세 수정", notes = "자기소개서 상세 내용을 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 수정 성공"), @ApiResponse(code = 401, message = "자기소개서 상세 수정 실패"), @ApiResponse(code = 402, message = "자기소개서 상세 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateResume(@PathVariable @ApiParam(value = "상세번호", required = true) Long detailno, @RequestBody @ApiParam(value = "상세내용", required = true) UpdateResumePutReq updateResumePutReq) throws Exception {
        ResumeDetail resumeDetail = resumeService.getByDetailNo(detailno);
        if (resumeDetail == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 자기소개서 상세가 없습니다."));
        try {
            resumeService.updateResumeDetail(resumeDetail, updateResumePutReq);
            ResumeDetail updatedResumeDetail = resumeService.getByDetailNo(detailno);
            return ResponseEntity.status(200).body(ResumeRes.of(updatedResumeDetail, 200, "자기소개서 상세내용이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세내용 수정에 실패하였습니다."));
        }
    }

    @DeleteMapping("/{detailno}")
    @ApiOperation(value = "자기소개서 상세 삭제", notes = "자기소개서 상세 내용을 삭제한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 삭제 성공"), @ApiResponse(code = 401, message = "자기소개서 상세 삭제 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteResumeDetail(@PathVariable @ApiParam(value = "상세번호", required = true) Long detailno) throws Exception {
        int result = resumeService.deleteResumeDetail(detailno);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 상세 삭제가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "자기소개서 상세 삭제를 실패하였습니다."));
    }

}
