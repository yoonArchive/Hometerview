package com.ssafy.api.controller;

import com.ssafy.api.request.PostWritePostReq;
import com.ssafy.api.service.PostService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Posts;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*
    @GetMapping()
    @ApiOperation(value = "스터디 모집글 목록 조회", notes = "스터디 모집글 목록을 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 목록 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 목록 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<Posts> getPostsList() throws Exception {
        List<Posts> posts = postService.getList();
        return ResponseEntity.status(200).body(PostsListRes.of(posts, 200, "공지사항 목록 조회를 성공하였습니다."));
    }
     */

    @GetMapping("/{postNo}")
    @ApiOperation(value = "모집글 상세조회", notes = "스터디 모집글 상세정보를 조회한다.")
    @ApiResponses({@ApiResponse(code = 200, message = "스터디 모집글 상세정보 조회 성공"), @ApiResponse(code = 401, message = "스터디 모집글 상세정보 조회 실패"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> getPostDetail(@PathVariable @ApiParam(value = "모집글 번호", required = true) long postNo) {
        Posts posts = postService.getByPostNo(postNo);
        if (posts == null) return ResponseEntity.status(401).body(BaseResponseBody.of(402, "해당하는 모집글이 없습니다."));
        return ResponseEntity.status(200).body(PostRes.of(posts, 200, "모집글 상세조회를 성공하였습니다."));
    }

}
