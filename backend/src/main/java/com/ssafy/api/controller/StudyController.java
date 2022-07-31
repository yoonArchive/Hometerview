package com.ssafy.api.controller;

import com.ssafy.api.request.*;
import com.ssafy.api.response.*;
import com.ssafy.api.service.CommonQuestionService;
import com.ssafy.api.service.ResumeService;
import com.ssafy.api.service.StudyService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.CommonQuestionRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(value = "스터디 API", tags = {"Study"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/study")
public class StudyController {

    private final StudyService studyService;

    private final ResumeService resumeService;

    private final CommonQuestionService commonQuestionService;

    @PostMapping()
    @ApiOperation(value = "스터디 생성", notes = "스터디를 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 생성 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 생성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> createStudy(@RequestParam @ApiParam(value = "모집글 번호", required = true) Long recruitNo) throws Exception {
        try {
            studyService.createStudy(recruitNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 생성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 생성에 성공하였습니다."));
    }

    @GetMapping()
    @ApiOperation(value = "스터디 목록 조회", notes = "스터디 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 목록 조회 성공", response = StudyListRes.class),
            @ApiResponse(code = 401, message = "스터디 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<StudyListRes> studyList(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Study> studyList = studyService.getStudyList(userNo);
        return ResponseEntity.status(200).body(StudyListRes.of(studyList, 200, "스터디 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/{stdNo}")
    @ApiOperation(value = "스터디 상세 조회", notes = "스터디 상세정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 상세 조회 성공", response = StudyRes.class),
            @ApiResponse(code = 401, message = "스터디 상세 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> studyDetail(@PathVariable Long stdNo) throws Exception {
        Study study;
        try {
            study = studyService.detailStudy(stdNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 상세 조회를 실패하였습니다."));
        }
        long[] detailCounts = studyService.getDetailCounts(stdNo);
        return ResponseEntity.status(200).body(StudyRes.of(study, detailCounts, 200, "스터디 상세 조회를 성공하였습니다."));
    }

    @DeleteMapping("/{stdNo}")
    @ApiOperation(value = "스터디 탈퇴", notes = "스터디에서 탈퇴한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 탈퇴 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 탈퇴 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> leaveStudy(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        int result = studyService.leaveStudy(userNo, stdNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 탈퇴가 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 탈퇴에 실패하였습니다."));
    }

    @DeleteMapping()
    @ApiOperation(value = "스터디 추방", notes = "스터디원을 추방한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디원 추방 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디원 추방 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> exileStudy(@RequestParam @ApiParam(value = "유저 번호", required = true) Long userNo, @RequestParam @ApiParam(value = "스터디 번호", required = true) Long stdNo) throws Exception {
        int result = studyService.leaveStudy(userNo, stdNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디원 추방이 완료되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디원 추방에 실패하였습니다."));
    }

    @PutMapping("notice/{stdNo}")
    @ApiOperation(value = "스터디 공지사항 작성", notes = "스터디 공지사항을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 공지사항 작성 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 공지사항 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 스터디 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> writeNotice(@PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo, @RequestBody @ApiParam(value = "공지사항 내용", required = true) @Valid UpdateStdNoticePutReq updateStdNoticePutReq) throws Exception {
        Study study = studyService.detailStudy(stdNo);
        if (study == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "스터디가 존재하지 않습니다."));
        try {
            studyService.updateNotice(study, updateStdNoticePutReq.getNewStdNotice());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 공지사항 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 공지사항 작성에 성공하였습니다."));
    }

    @PutMapping("enddate/{stdNo}")
    @ApiOperation(value = "스터디 종료일 변경", notes = "스터디 종료일을 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 종료일 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 종료일 변경 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 스터디 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updateEndDate(@PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo, @RequestBody @ApiParam(value = "스터디 종료일", required = true) @Valid UpdateStdEndDatePutReq updateStdEndDatePutReq) throws Exception {
        Study study = studyService.detailStudy(stdNo);
        if (study == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "스터디가 존재하지 않습니다."));
        try {
            studyService.updateEndDate(study, updateStdEndDatePutReq.getNewEndDate());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 종료일 변경에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 종료일 변경에 성공하였습니다."));
    }

    @PutMapping("stdimg/{stdNo}")
    @ApiOperation(value = "스터디 이미지 변경", notes = "스터디 이미지를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 이미지 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 이미지 변경 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 스터디 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updateStdImg(@PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo, @RequestBody @ApiParam(value = "스터디 이미지", required = true) @Valid UpdateStdImgPutReq updateStdImgPutReq) throws Exception {
        Study study = studyService.detailStudy(stdNo);
        if (study == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "스터디가 존재하지 않습니다."));
        try {
            studyService.updateStdImg(study, updateStdImgPutReq.getNewStdImg());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 이미지 변경에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 이미지 변경에 성공하였습니다."));
    }

    @PutMapping("stdday/{stdNo}")
    @ApiOperation(value = "스터디 진행일시 변경", notes = "스터디 진행일시를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "스터디 진행일시 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "스터디 진행일시 변경 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 스터디 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updateStdDay(@PathVariable @ApiParam(value = "스터디 번호", required = true) Long stdNo, @RequestBody @ApiParam(value = "스터디 진행일시", required = true) @Valid UpdateStdDayPutReq updateStdDayPutReq) throws Exception {
        Study study = studyService.detailStudy(stdNo);
        if (study == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "스터디가 존재하지 않습니다."));
        try {
            studyService.updateStdDay(study, updateStdDayPutReq.getNewStdDay());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 진행일시 변경에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 진행일시 변경에 성공하였습니다."));
    }

    @PostMapping({"/{stdNo}/common"})
    @ApiOperation(value = "공통 질문 등록", notes = "스터디에 공통 질문을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공통 질문 등록 성공", response = CommonQuestionListRes.class),
            @ApiResponse(code = 401, message = "공통 질문 등록 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> registerCommonQuestion(@ApiIgnore Authentication authentication,
                                                                             @PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                             @RequestBody @ApiParam(value = "질문내용", required = true) @Valid CommonQuestionReq commonQuestionReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            commonQuestionService.registerCommonQuestion(userNo, stdNo, commonQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공통 질문 등록에 실패하였습니다."));
        }
        List<CommonQuestion> commonQuestions = commonQuestionService.getList(stdNo);
        return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestions, 200, "공통 질문 등록이 완료되었습니다."));
    }

    @GetMapping({"/{stdNo}/common"})
    @ApiOperation(value = "공통 질문 목록 조회", notes = "스터디에 등록된 공통 질문을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공통 질문 조회 성공", response = CommonQuestionListRes.class),
            @ApiResponse(code = 401, message = "공통 질문 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<CommonQuestionListRes> getCommonQuestionList(@PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo) {
        List<CommonQuestion> commonQuestions = commonQuestionService.getList(stdNo);
        return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestions, 200, "스터디 스페이스에 등록된 공통 질문 목록입니다."));
    }

    @PutMapping({"{stdNo}/common/{questionNo}"})
    @ApiOperation(value = "공통 질문 수정", notes = "스터디에 등록된 공통 질문을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공통 질문 수정 성공", response = CommonQuestionListRes.class),
            @ApiResponse(code = 401, message = "공통 질문 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 질문 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updatePersonalQuestion(@PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                             @PathVariable("questionNo") @ApiParam(value = "공통 질문 번호", required = true) Long questionNo,
                                                                             @RequestBody @ApiParam(value = "수정내용", required = true) @Valid CommonQuestionReq commonQuestionReq) {
        CommonQuestion commonQuestion = commonQuestionService.getCommonQuestion(questionNo);
        if (commonQuestion == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 공통 질문이 없습니다."));
        try {
            commonQuestionService.updateCommonQuestion(commonQuestion, commonQuestionReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공통 질문 수정에 실패하였습니다."));
        }
        List<CommonQuestion> commonQuestions = commonQuestionService.getList(stdNo);
        return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestions, 200, "공통 질문 수정이 완료되었습니다."));
    }

    @DeleteMapping({"{stdNo}/common/{questionNo}"})
    @ApiOperation(value = "공통 질문 삭제", notes = "스터디에 등록된 공통 질문을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공통 질문 삭제 성공", response = CommonQuestionListRes.class),
            @ApiResponse(code = 401, message = "공통 질문 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "공통 질문 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> deleteCommonQuestion(@ApiIgnore Authentication authentication,
                                                                           @PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                           @PathVariable("questionNo") @ApiParam(value = "공통 질문 번호", required = true) Long questionNo) {
        CommonQuestion commonQuestion = commonQuestionService.getCommonQuestion(questionNo);
        if (commonQuestion == null)
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 공통 질문이 없습니다."));
        int result = commonQuestionService.deleteCommonQuestion(questionNo);
        if (result == 1) {
            List<CommonQuestion> commonQuestions = commonQuestionService.getList(stdNo);
            return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestions, 200, "공통 질문이 삭제되었습니다."));
        } else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공통 질문 삭제에 실패하였습니다."));
    }

    @GetMapping("{stdNo}/common/type")
    @ApiOperation(value = "전체, 직무, 인성, 자율 질문 필터링", notes = "스터디 공통 질문을 전체, 직무, 인성, 자율로 필터링한다. 전체: 1, 인성: 2, 직무: 3, 자율: 4")
    @ApiResponses({
            @ApiResponse(code = 200, message = "필터링 성공", response = CommonQuestionListRes.class),
            @ApiResponse(code = 401, message = "필터링 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<CommonQuestionListRes> getFilteredList(@PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                 @RequestParam @ApiParam(value = "필터 타입", required = true) int type) throws Exception {
        List<CommonQuestion> commonQuestions = commonQuestionService.getFilteredList(stdNo, type);
        return ResponseEntity.status(200).body(CommonQuestionListRes.of(commonQuestions, 200, "필터링 된 결과입니다."));
    }

    @PutMapping({"/resume/{stdNo}/{resumeNo}"})
    @ApiOperation(value = "스터디 스페이스에 자기소개서 등록", notes = "스터디 스페이스에 자기소개서를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "자기소개서 등록 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "자기소개서 등록 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 자기소개서 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> updateRegistedResume(@ApiIgnore Authentication authentication,
                                                                           @PathVariable("stdNo") @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                           @PathVariable("resumeNo") @ApiParam(value = "자기소개서 번호", required = true) Long resumeNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        StudyJoin studyJoin = studyService.findStudyJoin(stdNo, userNo);
        Resume resume = resumeService.getResume(resumeNo, userNo);
        if (resume == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "등록된 자기소개서가 아닙니다."));
        try {
            studyService.updateRegistedResume(studyJoin, resumeNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "자기소개서 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "자기소개서가 등록되었습니다."));
    }

}