package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.UserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    /*
    @ApiOperation
    한 개의 Operation을 선언한다.
    - value: API에 대한 요약을 작성한다.
    - notes : API에 대한 자세한 설명을 작성한다.
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {
		/*
		@ApiParam : Api에서 사용할 파라미터를 표시
		 */
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        try {
            userService.createUser(registerInfo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입에 실패하셨습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입에 성공하셨습니다.")); // 응답 코드와 함께 응답 메시지 return
    }

    @GetMapping("/me")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);

        return ResponseEntity.status(200).body(UserRes.of(user));
    }

    @GetMapping("/checkid")
    @ApiOperation(value = "아이디 중복 검사", notes = "회원가입 시 아이디 중복을 체크한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 401, message = "아이디 중복"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> checkId(@RequestParam @ApiParam(value = "회원 아이디", required = true) String id) {
        User user = userService.getByUserId(id);
        if (user == null) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 아이디입니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "사용 중인 아이디입니다."));
    }

    @GetMapping("/checkemail")
    @ApiOperation(value = "이메일 중복 검사", notes = "회원가입 시 이메일 중복을 체크한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능"),
            @ApiResponse(code = 401, message = "이메일 중복"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> checkEmail(@RequestParam @ApiParam(value = "회원 이메일", required = true) String email) {
        User user = userService.getByUserEmail(email);
        if (user == null) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 이메일입니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "사용 중인 이메일입니다."));
    }

}
