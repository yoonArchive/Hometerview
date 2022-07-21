package com.ssafy.api.controller;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.response.NoticeRes;
import com.ssafy.api.service.NoticeService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "공지사항 API", tags = {"Notice"})
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    // 작성
    @PostMapping()
    @ApiOperation(value = "공지사항 작성", notes = "공지사항 제목과 내용을 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "실패"), @ApiResponse(code = 500, message = "서버 오류")})

    public ResponseEntity<NoticeRes> writeNotice(@RequestBody @ApiParam(value = "공지사항 내용", required = true) NoticeWritePostReq noticeWritePostReq) throws Exception {
        try{
            Notice notice = noticeService.writeNotice(noticeWritePostReq);
            return ResponseEntity.status(200).body(NoticeRes.of(notice, 200, "공지사항 작성에 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(NoticeRes.of(null, 401, "공지사항 작성에 실패하셨습니다."));
        }
    }

    // 목록조회


    // 상세조회


    // 수정


    // 삭제

}
