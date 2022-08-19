package com.ssafy.api.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.api.request.ConferencePostReq;
import com.ssafy.api.service.SessionService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

@Api(value = "화상회의 API", tags = {"Session"})
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping(value = "/enter")
    @ApiOperation(value = "화상회의 방 입장", notes = "(token) 화상회의 방 입장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "입장 완료", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "입장 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<?> enterConference(@ApiIgnore Authentication authentication,
                                             @RequestBody @ApiParam(value = "화상회의 정보", required = true) ConferencePostReq conferencePostReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        int result = sessionService.enterSession(conferencePostReq, userNo);
        if (result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "화상회의 방에 입장하셨습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "화상회의 방 입장 중 문제가 발생하였습니다."));
    }

    @PostMapping(value = "/remove-user")
    public ResponseEntity<?> removeUser(@RequestBody String sessionNameToken, HttpSession httpSession) throws Exception {
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "화상회의 방에 입장하셨습니다."));
    }

}
