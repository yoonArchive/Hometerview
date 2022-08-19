package com.ssafy.api.controller;

import com.ssafy.api.request.PersonalQuestionReq;
import com.ssafy.api.request.ResumeWritePostReq;
import com.ssafy.api.request.UpdateResumePutReq;
import com.ssafy.api.response.PersonalQuestionListRes;
import com.ssafy.api.response.ResumeListRes;
import com.ssafy.api.response.ResumeRes;
import com.ssafy.api.service.PersonalQuestionService;
import com.ssafy.api.service.ResumeService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.PersonalQuestion;
import com.ssafy.db.entity.Resume;
import com.ssafy.db.entity.ResumeDetail;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(value = "자기소개서 API", tags = {"Resume"})
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resume")
public class ResumeController {

    private final ResumeService resumeService;

    private final PersonalQuestionService personalQuestionService;

    @PostMapping()
    @ApiOperation(value = "자기소개서 생성", notes = "(token) 자기소개서를 생성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 생성 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "자기소개서 생성 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> createResume(@ApiIgnore Authentication authentication, @RequestParam @ApiParam(value = "자기소개서 제목", required = true) String resumeTitle) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            resumeService.createResume(userNo, resumeTitle);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 생성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 생성되었습니다."));
    }

    @GetMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "(token) 자기소개서 목록 조회 성공", response = ResumeListRes.class), @ApiResponse(code = 401, message = "자기소개서 목록 조회 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    @ApiOperation(value = "자기소개서 목록 조회", notes = "자기소개서를 목록을 조회한다.")
    public ResponseEntity<ResumeListRes> listResume(@ApiIgnore Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Resume> resumes = resumeService.listResume(userNo);
        long[] detailCounts = resumeService.getDetailCount(resumes);
        return ResponseEntity.status(200).body(ResumeListRes.of(resumes, detailCounts, 200, "자기소개서 목록조회에 성공하였습니다."));
    }

    @PutMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "(token) 자기소개서 제목 수정 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "자기소개서 제목 수정 실패", response = BaseResponseBody.class), @ApiResponse(code = 402, message = "해당 자기소개서 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    @ApiOperation(value = "자기소개서 제목 수정", notes = "자기소개서 제목을 수정한다.")
    public ResponseEntity<? extends BaseResponseBody> updateResume(@ApiIgnore Authentication authentication, @RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo, @RequestParam @ApiParam(value = "자기소개서 제목", required = true) String resumeTitle) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Resume resume = resumeService.getResume(resumeNo, userNo);
        if (resume == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        try {
            resumeService.updateResume(resume, resumeTitle);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 제목 수정에 실패했습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 제목 수정에 성공했습니다."));
    }

    @DeleteMapping()
    @ApiResponses({@ApiResponse(code = 200, message = "(token) 자기소개서 삭제 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "자기소개서 삭제 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    @ApiOperation(value = "자기소개서 삭제", notes = "자기소개서를 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteResume(@ApiIgnore Authentication authentication, @RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Resume resume = resumeService.getResume(resumeNo, userNo);
        if (resume == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        int result = resumeService.deleteResume(resumeNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 삭제되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 삭제에 실패하였습니다."));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "자기소개서 상세 작성", notes = "자기소개서를 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 작성 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "자기소개서 작성 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> writeResumeDetail(@RequestBody @ApiParam(value = "자기소개서 상세 내용", required = true) @Valid ResumeWritePostReq resumeWritePostReq) throws Exception {
        try {
            resumeService.writeResumeDetail(resumeWritePostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 등록되었습니다."));
    }

    @GetMapping("/detail")
    @ApiOperation(value = "자기소개서 상세 조회", notes = "자기소개서를 상세 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 조회 성공", response = ResumeRes.class), @ApiResponse(code = 401, message = "자기소개서 상세 조회 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> detailResumeDetail(@RequestParam @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo, @RequestParam @ApiParam(value = "항목번호", required = true) Long itemNo) throws Exception {
        ResumeDetail resumeDetail = resumeService.getByResumeNoAndItemNo(resumeNo, itemNo);
        if (resumeDetail == null)
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세조회에 실패하였습니다."));
        return ResponseEntity.status(200).body(ResumeRes.of(resumeDetail, 200, "자기소개서 상세조회에 성공하였습니다."));
    }

    @PutMapping("/{detailNo}")
    @ApiOperation(value = "자기소개서 상세 수정", notes = "자기소개서 상세 내용을 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 수정 성공", response = ResumeRes.class), @ApiResponse(code = 401, message = "자기소개서 상세 수정 실패", response = BaseResponseBody.class), @ApiResponse(code = 402, message = "자기소개서 상세 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updateResume(@PathVariable @ApiParam(value = "상세번호", required = true) Long detailNo, @RequestBody @ApiParam(value = "상세내용", required = true) UpdateResumePutReq updateResumePutReq) throws Exception {
        ResumeDetail resumeDetail = resumeService.getByDetailNo(detailNo);
        if (resumeDetail == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 자기소개서 상세가 없습니다."));
        try {
            resumeService.updateResumeDetail(resumeDetail, updateResumePutReq);
            ResumeDetail updatedResumeDetail = resumeService.getByDetailNo(detailNo);
            return ResponseEntity.status(200).body(ResumeRes.of(updatedResumeDetail, 200, "자기소개서 상세내용이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세내용 수정에 실패하였습니다."));
        }
    }

    @DeleteMapping("/{detailNo}")
    @ApiOperation(value = "자기소개서 상세 삭제", notes = "자기소개서 상세 내용을 삭제한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "자기소개서 상세 삭제 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "자기소개서 상세 삭제 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> deleteResumeDetail(@PathVariable @ApiParam(value = "상세번호", required = true) Long detailNo) throws Exception {
        int result = resumeService.deleteResumeDetail(detailNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서 상세 삭제가 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 상세가 삭제되었습니다."));
    }

    @PostMapping({"/study/{stdNo}/detail/{detailNo}/question"})
    @ApiOperation(value = "개인 질문 등록", notes = "(token) 자기소개서 상세에 개인 질문을 등록한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "개인 질문 등록 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "개인 질문 등록 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> registerQuestion(@ApiIgnore Authentication authentication,
                                                                       @PathVariable("stdNo") @ApiParam(value = "스터디번호", required = true) Long stdNo,
                                                                       @PathVariable("detailNo") @ApiParam(value = "상세번호", required = true) Long detailNo,
                                                                       @RequestBody @ApiParam(value = "질문내용", required = true) @Valid PersonalQuestionReq personalQuestionReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            personalQuestionService.registerQuestion(userNo, stdNo, detailNo, personalQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "개인 질문 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "개인 질문이 등록되었습니다."));
    }

    @GetMapping({"/study/{stdNo}/detail/{detailNo}/question"})
    @ApiOperation(value = "개인 질문 목록 조회", notes = "자기소개서 상세에 등록된 개인 질문을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "개인 질문 조회 성공", response = PersonalQuestionListRes.class), @ApiResponse(code = 401, message = "개인 질문 조회 실패", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<PersonalQuestionListRes> getPersonalQuestionList(@PathVariable("stdNo") @ApiParam(value = "스터디번호", required = true) Long stdNo,
                                                                           @PathVariable("detailNo") @ApiParam(value = "상세번호", required = true) Long detailNo) {
        List<PersonalQuestion> personalQuestions = personalQuestionService.getList(stdNo, detailNo);
        int count = personalQuestions.size();
        return ResponseEntity.status(200).body(PersonalQuestionListRes.of(personalQuestions, count, 200, "해당 자기소개서 항목에 등록된 개인 질문입니다."));
    }

    @PutMapping({"/study/{stdNo}/detail/{detailNo}/question/{questionNo}"})
    @ApiOperation(value = "개인 질문 수정", notes = "(token) 자기소개서 상세에 등록된 개인 질문을 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "개인 질문 수정 성공", response = PersonalQuestionListRes.class), @ApiResponse(code = 401, message = "개인 질문 수정 실패", response = BaseResponseBody.class), @ApiResponse(code = 402, message = "해당 질문 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updatePersonalQuestion(@ApiIgnore Authentication authentication,
                                                                             @PathVariable("stdNo") @ApiParam(value = "스터디번호", required = true) Long stdNo,
                                                                             @PathVariable("detailNo") @ApiParam(value = "상세번호", required = true) Long detailNo,
                                                                             @PathVariable("questionNo") @ApiParam(value = "질문번호", required = true) Long questionNo,
                                                                             @RequestBody @ApiParam(value = "수정내용", required = true) @Valid PersonalQuestionReq personalQuestionReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        PersonalQuestion personalQuestion = personalQuestionService.getPersonalQuestion(questionNo, detailNo, userNo);
        if (personalQuestion == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 개인 질문이 없습니다."));
        try {
            personalQuestionService.updatePersonalQuestion(personalQuestion, personalQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "개인 질문 수정에 실패하였습니다."));
        }
        List<PersonalQuestion> personalQuestions = personalQuestionService.getList(stdNo, detailNo);
        int count = personalQuestions.size();
        return ResponseEntity.status(200).body(PersonalQuestionListRes.of(personalQuestions, count, 200, "개인 질문 수정이 완료되었습니다."));
    }

    @DeleteMapping({"/study/{stdNo}/detail/{detailNo}/question/{questionNo}"})
    @ApiOperation(value = "개인 질문 삭제", notes = "(token) 자기소개서 상세에 등록된 개인 질문을 삭제한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "개인 질문 삭제 성공", response = BaseResponseBody.class), @ApiResponse(code = 401, message = "개인 질문 삭제 실패", response = BaseResponseBody.class), @ApiResponse(code = 402, message = "해당 질문 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> deletePersonalQuestion(@ApiIgnore Authentication authentication,
                                                                             @PathVariable("stdNo") @ApiParam(value = "스터디번호", required = true) Long stdNo,
                                                                             @PathVariable("detailNo") @ApiParam(value = "상세번호", required = true) Long detailNo,
                                                                             @PathVariable("questionNo") @ApiParam(value = "질문번호", required = true) Long questionNo) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        PersonalQuestion personalQuestion = personalQuestionService.getPersonalQuestion(questionNo, detailNo, userNo);
        if (personalQuestion == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 개인 질문이 없습니다."));
        int result = personalQuestionService.deletePersonalQuestion(questionNo);
        if (result == 0) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "개인 질문 삭제에 실패하였습니다."));
        else {
            List<PersonalQuestion> personalQuestions = personalQuestionService.getList(stdNo, detailNo);
            int count = personalQuestions.size();
            return ResponseEntity.status(200).body(PersonalQuestionListRes.of(personalQuestions, count, 200, "개인 질문이 삭제되었습니다."));
        }
    }

    @PutMapping({"/detail/{detailNo}/question/{questionNo}/saved"})
    @ApiOperation(value = "개인 질문 즐겨찾기 등록 여부 변경", notes = "(token) 자기소개서 상세에 등록된 개인 질문의 즐겨찾기 등록 여부를 변경한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "변경 성공", response = PersonalQuestionListRes.class), @ApiResponse(code = 401, message = "변경 실패", response = BaseResponseBody.class), @ApiResponse(code = 402, message = "해당 질문 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> savePersonalQuestion(@ApiIgnore Authentication authentication,
                                                                           @PathVariable("detailNo") @ApiParam(value = "상세번호", required = true) Long detailNo,
                                                                           @PathVariable("questionNo") @ApiParam(value = "질문번호", required = true) Long questionNo) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        PersonalQuestion personalQuestion = personalQuestionService.validatePersonalQuestion(questionNo, detailNo, userNo);
        if (personalQuestion == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "즐겨찾기 상태를 변경할 수 없는 개인 질문입니다."));
        try {
            personalQuestionService.updateSavedStatus(personalQuestion);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "즐겨찾기 상태 변경에 실패하였습니다."));
        }
        List<PersonalQuestion> personalQuestions = personalQuestionService.getListByDetailNo(detailNo);
        int count = personalQuestions.size();
        return ResponseEntity.status(200).body(PersonalQuestionListRes.of(personalQuestions, count, 200, "즐겨찾기 상태 변경이 완료되었습니다."));
    }

    @GetMapping("/saved/{resumeNo}")
    @ApiOperation(value = "자기소개서 별 즐겨찾기에 추가된 개인질문 조회", notes = "(token) 자기소개서 별 즐겨찾기에 추가된 개인질문을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "즐겨찾기 조회 성공", response = PersonalQuestionListRes.class), @ApiResponse(code = 402, message = "해당 자기소개서 없음", response = BaseResponseBody.class), @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> getSavedPersonalQuestions(@ApiIgnore Authentication authentication,
                                                                                @PathVariable("resumeNo") @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Resume resume = resumeService.getResume(resumeNo, userNo);
        if (resume == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당 자기소개서가 없습니다."));
        List<PersonalQuestion> personalQuestions = personalQuestionService.getSavedPersonalQuestionList(resumeNo);
        int count = personalQuestions.size();
        return ResponseEntity.status(200).body(PersonalQuestionListRes.of(personalQuestions, count, 200, "즐겨찾기 추가된 개인질문 리스트입니다."));
    }

}
