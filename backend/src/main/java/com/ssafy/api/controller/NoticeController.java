package com.ssafy.api.controller;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.response.NoticeListRes;
import com.ssafy.api.response.NoticeRes;
import com.ssafy.api.service.NoticeService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "공지사항 API", tags = {"Notice"})
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @PostMapping()
    @ApiOperation(value = "공지사항 작성", notes = "공지사항 제목과 내용을 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 작성 성공"), @ApiResponse(code = 401, message = "공지사항 작성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<NoticeRes> writeNotice(@RequestBody @ApiParam(value = "공지사항 내용", required = true) NoticeWritePostReq noticeWritePostReq) throws Exception {
        try {
            Notice notice = noticeService.writeNotice(noticeWritePostReq);
            return ResponseEntity.status(200).body(NoticeRes.of(notice, 200, "공지사항 작성에 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(NoticeRes.of(null, 401, "공지사항 작성에 실패하셨습니다."));
        }
    }

    @GetMapping()
    @ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 목록 조회 성공"), @ApiResponse(code = 401, message = "공지사항 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<NoticeListRes> listNotice() throws Exception {
        List<Notice> notices = noticeService.listNotice();
        return ResponseEntity.status(200).body(NoticeListRes.of(notices, 200, "공지사항 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 상세조회", notes = "공지사항 상세정보을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 상세정보을 조회 성공"), @ApiResponse(code = 401, message = "공지사항 상세정보을 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> detailNotice(@PathVariable @ApiParam(value = "공지사항 번호", required = true) Long noticeNo) {
        Notice notice = noticeService.getByNoticeNo(noticeNo);
        if (notice == null) return ResponseEntity.status(401).body(BaseResponseBody.of(402, "해당하는 공지사항이 없습니다."));
        return ResponseEntity.status(200).body(NoticeRes.of(notice, 200, "공지사항 상세조회를 성공하였습니다."));
    }

    @PutMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 수정", notes = "공지사항의 제목 또는 내용을 수정한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 수정 성공"), @ApiResponse(code = 401, message = "공지사항 수정 실패"), @ApiResponse(code = 402, message = "해당 공지 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updateNotice(@PathVariable @ApiParam(value = "공지사항 번호", required = true) Long noticeNo, @RequestBody @ApiParam(value = "공지사항 내용", required = true) UpdateNoticePutReq updateNoticePutReq) {
        Notice notice = noticeService.getByNoticeNo(noticeNo);
        if (notice == null) return ResponseEntity.status(401).body(BaseResponseBody.of(402, "해당하는 공지사항이 없습니다."));
        try {
            noticeService.updateNotice(notice, updateNoticePutReq);
            Notice updatedNotice = noticeService.getByNoticeNo(noticeNo);
            return ResponseEntity.status(200).body(NoticeRes.of(updatedNotice, 200, "공지사항이 수정되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 수정에 실패하였습니다."));
        }
    }

    @DeleteMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제")
    @ApiResponses({@ApiResponse(code = 200, message = "공지사항 삭제 성공"), @ApiResponse(code = 401, message = "공지사항 삭제 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> deleteNotice(@PathVariable @ApiParam(value = "공지사항 번호", required = true) Long noticeNo) throws Exception {
        int result = noticeService.deleteNotice(noticeNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공지사항 삭제가 완료되었습니다."));
        else return ResponseEntity.status(200).body(BaseResponseBody.of(401, "공지사항 삭제에 실패하였습니다."));
    }

}
