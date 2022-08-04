package com.ssafy.api.controller;

import com.ssafy.api.dto.Mail;
import com.ssafy.api.request.*;
import com.ssafy.api.response.*;
import com.ssafy.api.service.DdayService;
import com.ssafy.api.service.MailService;
import com.ssafy.api.service.ReviewService;
import com.ssafy.db.entity.Dday;
import com.ssafy.db.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

import javax.validation.Valid;
import java.util.List;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final MailService mailService;

    private final ReviewService reviewService;

    private final DdayService ddayService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    /*
    @ApiOperation
    한 개의 Operation을 선언한다.
    - value: API에 대한 요약을 작성한다.
    - notes : API에 대한 자세한 설명을 작성한다.
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody @ApiParam(value = "회원가입 정보", required = true) @Valid UserRegisterPostReq userRegisterPostReq) throws Exception {
		/*
		@ApiParam : Api에서 사용할 파라미터를 표시
		 */
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        try {
            userService.createUser(userRegisterPostReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입에 성공하였습니다.")); // 응답 코드와 함께 응답 메시지 return
    }

    @GetMapping("/me")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "(token) 로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) throws Exception {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        return ResponseEntity.status(200).body(UserRes.of(user, 200, "회원 정보 조회 성공"));
    }

    @GetMapping("/checkId")
    @ApiOperation(value = "아이디 중복 검사", notes = "회원가입 시 아이디 중복을 체크한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "아이디 중복", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkId(@RequestParam @ApiParam(value = "회원 아이디", required = true) String id) throws Exception {
        User user = userService.getByUserId(id);
        if (user == null) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 아이디입니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "사용 중인 아이디입니다."));
    }

    @GetMapping("/checkEmail")
    @ApiOperation(value = "이메일 중복 검사", notes = "회원가입 시 이메일 중복을 체크한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "이메일 중복", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkEmail(@RequestParam @ApiParam(value = "회원 이메일", required = true) String userEmail) throws Exception {
        User user = userService.getByUserEmail(userEmail);
        if (user == null) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 이메일입니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "사용 중인 이메일입니다."));
    }

    @GetMapping("/pw")
    @ApiOperation(value = "비밀번호 인증", notes = "(token) 비밀번호 인증을 위해 로그인한 회원의 비밀번호와 일치하는 비밀번호를 입력한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 인증 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "비밀번호 인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> certifyPw(@ApiIgnore Authentication authentication, @RequestParam String userPw) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        if (passwordEncoder.matches(userPw, userDetails.getPassword()))
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "비밀번호 인증 성공"));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
    }

    @PutMapping("/pw")
    @ApiOperation(value = "비밀번호 변경", notes = "(token) 회원의 비밀번호를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "비밀번호 변경 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updatePw(Authentication authentication, @RequestBody @ApiParam(value = "비밀번호 변경 정보", required = true) UpdatePwPutReq updatePwPutReq) {
        String userPw = updatePwPutReq.getUserPw();
        String newPw = updatePwPutReq.getNewPw();
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        if (!passwordEncoder.matches(userPw, user.getUserPw()))
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
        else {
            userService.updatePassword(user, newPw);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "비밀번호가 변경되었습니다."));
        }
    }

    @GetMapping("/findId")
    @ApiOperation(value = "아이디 찾기", notes = "회원의 이름과 이메일에 해당하는 회원 아이디를 찾는다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "아이디 찾기 성공", response = UserFindIdGetRes.class),
            @ApiResponse(code = 401, message = "아이디 찾기 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> findId(@RequestParam @ApiParam(value = "회원 이름", required = true) String userName, @RequestParam @ApiParam(value = "회원 이메일", required = true) String userEmail) throws Exception {
        User user = userService.getByUserNameAndUserEmail(userName, userEmail);
        if (user == null)
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "고객님의 정보와 일치하는 아이디가 없습니다."));
        else return ResponseEntity.status(200).body(UserFindIdGetRes.of(200, "아이디 찾기 성공", user.getUserId()));
    }

    @PostMapping("/findPw")
    @ApiOperation(value = "임시 비밀번호 전송", notes = "회원 정보를 입력하고 일치하면 임시 비밀번호를 메일로 전송한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공", response = UserFindPwGetRes.class),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserFindPwGetRes> findPw(@RequestParam @ApiParam(value = "회원 이름", required = true) String userName, @RequestParam @ApiParam(value = "회원 이메일", required = true) String userEmail, @RequestParam @ApiParam(value = "회원 아이디", required = true) String userId) throws Exception {
        User user = userService.getByUserNameAndUserEmailAndUserId(userName, userEmail, userId);
        if (user == null) return ResponseEntity.status(401).body(UserFindPwGetRes.of(401, "입력한 정보를 다시 확인해주세요.", null));
        else {
            // 랜덤 임시 비밀번호 생성
            String tmpPw = mailService.getTmpPassword();
            // 비밀번호 값 변경
            userService.updatePassword(user, tmpPw);
            // 메일 생성 & 전송
            Mail mail = mailService.createTempPwMail(tmpPw, user.getUserEmail());
            mailService.sendMail(mail);
            return ResponseEntity.status(200).body(UserFindPwGetRes.of(200, "이메일 발송 성공", passwordEncoder.encode(user.getUserPw())));
        }
    }

    @PostMapping("/authEmail")
    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 인증번호를 전송한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> authEmail(@RequestParam @ApiParam(value = "회원 이메일", required = true) String userEmail) throws Exception {
        Mail mail = mailService.createAuthMail(userEmail);
        mailService.sendMail(mail);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "인증번호 발송 성공"));
    }

    @GetMapping("/checkAuthKey")
    @ApiOperation(value = "인증번호 확인", notes = "인증번호를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkAuthKey(@RequestParam @ApiParam(value = "회원 이메일", required = true) String userEmail, @RequestParam @ApiParam(value = "인증번호", required = true) String authKey) throws Exception {
        String email = mailService.checkAuthKey(authKey);
        if (email == null || !email.equals(userEmail))
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "인증번호가 올바르지 않습니다."));
        else {
            mailService.deleteAuthKey(authKey);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "인증 성공"));
        }
    }

    @PutMapping()
    @ApiOperation(value = "회원 정보 수정", notes = "(token) 회원의 프로필 이미지, 이름 혹은 이메일을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 정보 수정 성공", response = UserRes.class),
            @ApiResponse(code = 401, message = "회원 정보 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "유저 프로필 사진 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateUser(@ApiIgnore Authentication authentication, @ModelAttribute @Valid UpdateUserPutReq updateUserPutReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        int result = userService.updateUser(user, updateUserPutReq);
        if (result == 0) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원정보 수정에 실패하였습니다."));
        else if (result == 1) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "프로필 사진 수정에 실패하였습니다."));
        User updatedUser = userService.getByUserId(userId);
        return ResponseEntity.status(200).body(UserRes.of(updatedUser, 200, "회원정보가 수정되었습니다."));
    }

    @DeleteMapping()
    @ApiOperation(value = "회원 탈퇴", notes = "(token) 회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 탈퇴 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "회원 탈퇴 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(@ApiIgnore Authentication authentication) throws Exception {
        userService.deleteUser(((UserDetails) authentication.getDetails()).getUsername());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원탈퇴가 완료되었습니다."));
    }

    @PostMapping("/review")
    @ApiOperation(value = "회고 작성", notes = "(token) 면접 회고를 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회고 작성 성공", response = ReviewRes.class),
            @ApiResponse(code = 401, message = "회고 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> createReview(@ApiIgnore Authentication authentication, @RequestBody @ApiParam(value = "회고 내용", required = true) @Valid ReviewReq reviewReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Review review;
        try {
            review = reviewService.writeReview(userNo, reviewReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회고 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(ReviewRes.of(review, 200, "회고가 등록되었습니다."));
    }

    @GetMapping("/review")
    @ApiOperation(value = "회고 목록 조회", notes = "(token) 회고 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회고 목록 조회 성공", response = ReviewListRes.class),
            @ApiResponse(code = 401, message = "회고 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ReviewListRes> getReviewList(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Review> reviews = reviewService.getList(userNo);
        return ResponseEntity.status(200).body(ReviewListRes.of(reviews, 200, "회고 목록 조회에 성공하였습니다."));
    }

    @GetMapping("/review/{reviewNo}")
    @ApiOperation(value = "회고 상세 조회", notes = "(token) 회고 상세 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회고 상세 정보 조회 성공", response = ReviewRes.class),
            @ApiResponse(code = 401, message = "회고 상세 정보 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당하는 회고 상세 정보 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getReviewDetail(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "회고 번호", required = true) Long reviewNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Review review = reviewService.getReviewDetail(reviewNo, userNo);
        if (review == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 회고가 없습니다."));
        return ResponseEntity.status(200).body(ReviewRes.of(review, 200, "회고 상세 정보 조회에 성공하였습니다."));
    }

    @PutMapping("/review/{reviewNo}")
    @ApiOperation(value = "회고 수정", notes = "(token) 회고 내용을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회고 수정 성공", response = ReviewRes.class),
            @ApiResponse(code = 401, message = "회고 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당하는 회고 상세 정보 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateReview(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "회고 번호", required = true) Long reviewNo, @RequestBody @ApiParam(value = "회고 변경 내용", required = true) ReviewReq reviewReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Review review = reviewService.getReviewDetail(reviewNo, userNo);
        if (review == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 회고가 없습니다."));
        Review updatedReview;
        try {
            reviewService.updateReview(review, reviewReq);
            updatedReview = reviewService.getByReviewNo(reviewNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회고 수정에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(ReviewRes.of(updatedReview, 200, "회고 수정이 완료되었습니다."));
    }

    @DeleteMapping("/review/{reviewNo}")
    @ApiOperation(value = "회고 삭제", notes = "(token) 회고를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회고 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "회고 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당하는 회고 상세 정보 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteReview(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "모집글 번호", required = true) Long reviewNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Review review = reviewService.getReviewDetail(reviewNo, userNo);
        if (review == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 회고가 없습니다."));
        try {
            reviewService.deleteReview(reviewNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회고 삭제에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회고 삭제가 완료되었습니다."));
    }

    @PostMapping("/dday")
    @ApiOperation(value = "일정 등록", notes = "(token) 일정을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일정 등록 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "일정 등록 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> registerDday(@ApiIgnore Authentication authentication, @RequestBody @ApiParam(value = "일정 정보", required = true) @Valid DdayReq ddayReq) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        try {
            ddayService.writeDday(userNo, ddayReq);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일정 등록에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "일정이 등록되었습니다."));
    }

    @GetMapping("/dday")
    @ApiOperation(value = "D-day 목록 조회", notes = "(token) D-day 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "D-day 목록 조회 성공", response = DdayListRes.class),
            @ApiResponse(code = 401, message = "D-day 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<DdayListRes> getDdayList(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        List<Dday> ddays = ddayService.getList(userNo);
        long[] results = ddayService.getResults(ddays);
        return ResponseEntity.status(200).body(DdayListRes.of(ddays, results, 200, "D-day 목록 조회에 성공하였습니다."));
    }

    @PutMapping("/dday/{ddayNo}")
    @ApiOperation(value = "일정 수정", notes = "(token) 일정을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일정 수정 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "일정 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateDday(@ApiIgnore Authentication authentication,
                                                                 @PathVariable @ApiParam(value = "D-day 번호", required = true) Long ddayNo,
                                                                 @RequestBody @ApiParam(value = "일정 수정 내용", required = true) @Valid DdayReq ddayReq) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Dday dday = ddayService.getDday(ddayNo, userNo);
        if (dday == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 D-day가 없습니다."));
        Dday updatedDday;
        try {
            ddayService.updateDday(dday, ddayReq);
            updatedDday = ddayService.getByDdayNo(ddayNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일정 수정에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DdayRes.of(updatedDday, 200, "일정이 수정되었습니다."));
    }

    @DeleteMapping("/dday/{ddayNo}")
    @ApiOperation(value = "일정 삭제", notes = "(token) 일정을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일정 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "일정 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 일정 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteDday(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "D-day 번호", required = true) Long ddayNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUserNo();
        Dday dday = ddayService.getDday(ddayNo, userNo);
        if (dday == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 D-day가 없습니다."));
        try {
            ddayService.deleteDday(ddayNo);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일정 삭제에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "일정이 삭제되었습니다."));
    }

}