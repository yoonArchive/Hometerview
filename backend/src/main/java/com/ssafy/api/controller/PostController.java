package com.ssafy.api.controller;

import com.ssafy.api.request.PostWritePostReq;
import com.ssafy.api.service.PostService;
import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "모집글 API", tags = {"Posts"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    @ApiOperation(value = "모집글 작성", notes = "스터디 모집글을 작성한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "모집글 작성 성공"), @ApiResponse(code = 401, message = "모집글 작성 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody @ApiParam(value = "모집글 정보", required = true) @Valid PostWritePostReq postWritePostReq) throws Exception {
        try {
            postService.writePost(postWritePostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "스터디 모집글 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "스터디 모집글 작성에 성공하였습니다."));
    }

}
