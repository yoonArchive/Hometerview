package com.ssafy.api.controller;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.request.RecordingPostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.api.response.NoticeListRes;
import com.ssafy.api.response.NoticeRes;
import com.ssafy.api.response.RecordingListRes;
import com.ssafy.api.response.RecordingRes;
import com.ssafy.api.service.NoticeService;
import com.ssafy.api.service.RecordingService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.entity.Recording;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "녹화 API", tags = {"Recording"})
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recording")
public class RecordingController {

    private final RecordingService recordingService;

    @PostMapping()
    @ApiOperation(value = "녹화 등록", notes = "녹화를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "녹화 등록 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "녹화 등록 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeRecording(@RequestParam @ApiParam(value = "스터디 번호", required = true) Long stdNo,
                                                                     @RequestBody @ApiParam(value = "녹화 정보", required = true) RecordingPostReq recordingPostReq) throws Exception {
        try {
            recordingService.writeRecord(stdNo, recordingPostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "녹화 등록에 실패하셨습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "녹화가 등록되었습니다."));
    }

    @GetMapping()
    @ApiOperation(value = "녹화 목록 조회", notes = "녹화 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "녹화 목록 조회 성공", response = RecordingListRes.class),
            @ApiResponse(code = 401, message = "녹화 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<RecordingListRes > listRecording(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        List<Recording> recordings = recordingService.getRecordList(userId);
        return ResponseEntity.status(200).body(RecordingListRes.of(recordings, 200, "녹화 목록 조회를 성공하였습니다."));
    }

    @GetMapping("/{recordNo}")
    @ApiOperation(value = "녹화 상세조회", notes = "녹화 상세정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "녹화 상세정보 조회 성공", response = RecordingRes.class),
            @ApiResponse(code = 401, message = "녹화 상세정보 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> detailRecording(@PathVariable @ApiParam(value = "녹화 번호", required = true) Long recordNo) throws Exception {
        Recording recording = recordingService.getRecordByRecordNo(recordNo);
        if (recording == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 녹화가 없습니다."));
        return ResponseEntity.status(200).body(RecordingRes.of(recording, 200, "녹화 상세조회를 성공하였습니다."));
    }

    @DeleteMapping("/{recordNo}")
    @ApiOperation(value = "녹화 삭제", notes = "녹화 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "녹화 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "녹화 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteRecording(@PathVariable @ApiParam(value = "녹화 번호", required = true) Long recordNo) throws Exception {
        int result = recordingService.deleteRecording(recordNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "녹화가 삭제되었습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "녹화삭제에 실패하였습니다."));
    }

}
