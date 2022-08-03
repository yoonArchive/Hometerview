<template>
  <div class="section">
    <div class="container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="section pb-5 pt-5 pt-sm-2 text-center">
            <h6 class="mb-0 pb-3"><span>로그인 </span><span>회원가입</span></h6>
            <input
              class="checkbox"
              type="checkbox"
              id="reg-log"
              name="reg-log"
            />
            <label for="reg-log"></label>
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">로그인</h4>
                      <form @submit.prevent="login(credentials)">
                        <div class="form-group">
                          <input
                            type="text"
                            class="form-style"
                            placeholder="아이디"
                            id="id"
                            v-model="credentials.userId"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-user"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input
                            type="password"
                            class="form-style"
                            placeholder="비밀번호"
                            id="password"
                            v-model="credentials.userPw"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <button class="btn mt-4">로그인</button>
                      </form>
                      <router-link to="/home/findid">
                        <div class="find_id mb-0 mt-4 text-center">
                          아이디 찾기
                        </div>
                      </router-link>
                      <router-link to="/home/finduserpassword">
                        <div class="find_pwd mb-0 mt-4 text-center">
                          비밀번호 찾기
                        </div>
                      </router-link>
                      <!-- <p class="mb-0 mt-4 text-center">
                        <a href="#0" class="link">아이디 찾기</a>
                      </p>
                      <p class="mb-0 mt-4 text-center">
                        <a href="#0" class="link">비밀번호 찾기</a>
                      </p> -->
                    </div>
                  </div>
                </div>
                <div class="card-back">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <h4 class="mb-4 pb-3">회원가입</h4>
                      <form @submit.prevent="signupComfirmation(credentials)">
                        <div class="form-group">
                          <input
                            type="text"
                            class="form-style-sign-up"
                            placeholder="아이디"
                            id="userId"
                            v-model="credentials.userId"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-user"></i>
                          <form
                            @submit.prevent="
                              idDuplicateCheck(credentials.userId)
                            "
                          >
                            <button class="btncheck">중복확인</button>
                          </form>
                        </div>
                        <div class="form-group mt-2">
                          <input
                            type="password"
                            class="form-style"
                            placeholder="비밀번호"
                            id="userPw"
                            v-model="credentials.userPw"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <div class="pw-msg">
                          비밀번호는 9~16자 영문+숫자+특수문자 조합이어야
                          합니다.
                        </div>
                        <div class="form-group mt-2">
                          <input
                            type="password"
                            class="form-style"
                            placeholder="비밀번호 확인"
                            id="userPw2"
                            v-model="credentials.userPw2"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input
                            type="text"
                            class="form-style"
                            placeholder="이름"
                            id="userName"
                            v-model="credentials.userName"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-user-circle"></i>
                        </div>
                        <div class="form-group mt-2">
                          <input
                            type="email"
                            class="form-style-check-auth"
                            placeholder="Email"
                            id="userEmail"
                            v-model="credentials.userEmail"
                            autocomplete="off"
                            required
                          />
                          <i class="input-icon uil uil-at"></i>
                          <!-- <form
                            @submit.prevent="
                              emailDuplicateCheck(credentials.userEmail)
                            "
                          >
                            <button class="btncheck">중복확인</button>
                          </form> -->
                          <form
                            @submit.prevent="
                              sendAuthKeyToEmail(credentials.userEmail)
                            "
                          >
                            <button class="btncheck">인증번호 받기</button>
                          </form>
                        </div>
                        <form
                          @submit.prevent="
                            checkAuthKey([authKey, credentials.userEmail])
                          "
                        >
                          <div class="form-group mt-2">
                            <input
                              type="text"
                              class="form-style-check-auth"
                              placeholder="인증번호 확인"
                              id="checkAuthKey"
                              v-model="authKey"
                              autocomplete="off"
                              required
                            />
                            <i class="input-icon uil uil-shield"></i>
                          </div>
                          <button class="btncheck">인증번호 확인</button>
                        </form>
                        <button class="btn mt-4">회원가입</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";

// import AccountErrorList from './components/AccountErrorList.vue'

export default {
  name: "LoginView",
  components: {
    // AccountErrorList
  },

  data() {
    return {
      credentials: {
        userId: "",
        userPw: ""
      },
      profile: "",
      validationPattern: {
        pwdCheckPattern: /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+-])(?=.*[0-9]).{9,16}$/,
        idCheckPattern: /^[a-zA-z0-9].{1,16}$/,
        eamilCheckPattern: /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
      },
      credentials: {
        userEmail: "",
        userName: "",
        userId: "",
        userPw: "",
        userPw2: ""
      }
    };
  },
  computed: {
    ...mapGetters([
      "authError",
      "isDuplicatedEmail",
      "isDuplicatedId",
      "isAuthorized",
      "comfirmEmail"
    ]),
    ...mapState(["token", "kakaoToken"])
  },
  methods: {
    ...mapActions([
      "login",
      "kakaoLoginBtn",
      "signup",
      // "emailDuplicateCheck",
      "idDuplicateCheck",
      "sendAuthKeyToEmail",
      "checkAuthKey",
      "changeFalseAuthState",
      "changeAuthState"
    ]),
    async signupComfirmation() {
      if (
        this.credentials.userEmail === "" ||
        this.credentials.userName === "" ||
        this.credentials.userId === "" ||
        this.credentials.userPw === "" ||
        this.credentials.userPw2 === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      } else if (
        !this.validationPattern.idCheckPattern.test(this.credentials.userId)
      ) {
        console.log();
        console.log(
          !this.validationPattern.idCheckPattern.test(this.credentials.userId)
        );
        alert("아이디는 영문자 또는 숫자만 가능합니다.");
        return;
      } else if (
        !this.validationPattern.pwdCheckPattern.test(this.credentials.userPw)
      ) {
        alert(
          "비밀번호는 영문자+숫자+특수문자 조합으로 9~16자리를 사용해야합니다."
        );
        return;
      } else if (
        !this.validationPattern.eamilCheckPattern.test(
          this.credentials.userEmail
        )
      ) {
        alert("이메일 형태가 아닙니다. 다시 확인해주세요");
        return;
      } else if (this.credentials.userPw !== this.credentials.userPw2) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      } else if (this.isDuplicatedEmail) {
        alert("이메일 중복검사를 해주세요");
        return;
      } else if (this.isDuplicatedId) {
        alert("아이디 중복검사를 해주세요");
        return;
      } else if (this.isAuthorized === false) {
        alert("인증번호 확인을 해주세요");
        return;
      } else {
        await this.signup(this.credentials);
        await this.changeFalseAuthState();
      }
    }
  },
  setup() {
    return {};
  },
  mounted() {},
  watch: {
    // 이메일창이랑 confirm함수가 다르면 changeAuthState=> false로 변환
    // checkSameEmail(){
    //   console.log(this.comfirmEmail)
    //   console.log(this.credentials.userEmail)
    //   if (this.credentials.userEmail===this.comfirmEmail){
    //     this.changeAuthState(true)
    //   }else{
    //     this.changeAuthState(false)
    //   }
    // }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900");

body {
  font-family: "Poppins", sans-serif;
  font-weight: 300;
  font-size: 15px;
  line-height: 1.7;
  color: #c4c3ca;
  /*background-color: #1f2029;*/
  overflow-x: hidden;
}
a {
  cursor: pointer;
  transition: all 200ms linear;
}
a:hover {
  text-decoration: none;
}
.link {
  color: #c4c3ca;
}
.link:hover {
  color: #ffeba7;
}
p {
  font-weight: 500;
  font-size: 14px;
  line-height: 1.7;
}
h4 {
  font-weight: 600;
}
h6 span {
  padding: 0 20px;
  text-transform: uppercase;
  font-weight: 700;
}
.section {
  position: relative;
  width: 100%;
  display: block;
}
.full-height {
  min-height: 100vh;
}
[type="checkbox"]:checked,
[type="checkbox"]:not(:checked) {
  position: absolute;
  left: -9999px;
}
.checkbox:checked + label,
.checkbox:not(:checked) + label {
  position: relative;
  display: block;
  text-align: center;
  width: 60px;
  height: 16px;
  border-radius: 8px;
  padding: 0;
  margin: 10px auto;
  cursor: pointer;
  background-color: #3b2677;
}
.checkbox:checked + label:before,
.checkbox:not(:checked) + label:before {
  position: absolute;
  display: block;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  color: #ffffff;
  background-color: #3b2677;
  font-family: "unicons";
  content: "\eb4f";
  z-index: 20;
  top: -10px;
  left: -10px;
  line-height: 36px;
  text-align: center;
  font-size: 24px;
  transition: all 0.5s ease;
}
.checkbox:checked + label:before {
  transform: translateX(44px) rotate(-270deg);
}

.card-3d-wrap {
  position: relative;
  width: 470px;
  max-width: 100%;
  height: 600px;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  perspective: 800px;
  margin-top: 50px;
}
.card-3d-wrapper {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  transition: all 600ms ease-out;
}
.card-front,
.card-back {
  width: 100%;
  height: 100%;
  background-color: #ececec;
  /* background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg"); */
  background-position: bottom center;
  background-repeat: no-repeat;
  background-size: 300%;
  position: absolute;
  border-radius: 40px;
  left: 0;
  top: 0;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -o-backface-visibility: hidden;
  backface-visibility: hidden;
}
.card-back {
  transform: rotateY(180deg);
}
.checkbox:checked ~ .card-3d-wrap .card-3d-wrapper {
  transform: rotateY(180deg);
}
.center-wrap {
  position: absolute;
  width: 100%;
  padding: 0 35px;
  top: 50%;
  left: 0;
  transform: translate3d(0, -50%, 35px) perspective(100px);
  z-index: 20;
  display: block;
}

.form-group {
  position: relative;
  display: block;
  margin: 0;
  padding: 0;
}
.form-style {
  padding: 13px 20px;
  padding-left: 55px;
  height: 48px;
  width: 100%;
  font-weight: 500;
  border-radius: 4px;
  font-size: 16px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #ffffff;
  background-color: #1f2029;
  border: none;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style:focus,
.form-style:active {
  border: none;
  outline: none;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style-sign-up {
  float: left;
  padding: 13px 20px;
  padding-left: 55px;
  height: 48px;
  width: 80%;
  font-weight: 500;
  border-radius: 4px;
  font-size: 16px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #ffffff;
  background-color: #1f2029;
  border: none;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style-sign-up:focus,
.form-style-sign-up:active {
  border: none;
  outline: none;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style-check-auth {
  float: left;
  padding: 13px 20px;
  padding-left: 55px;
  height: 48px;
  width: 72%;
  font-weight: 500;
  border-radius: 4px;
  font-size: 16px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #ffffff;
  background-color: #1f2029;
  border: none;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.form-style-check-auth:focus,
.form-style-check-auth:active {
  border: none;
  outline: none;
  box-shadow: 0 4px 8px 0 rgba(21, 21, 21, 0.2);
}
.input-icon {
  position: absolute;
  top: 0;
  left: 18px;
  height: 48px;
  font-size: 24px;
  line-height: 48px;
  text-align: left;
  color: #d76d77;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}

.form-group input:-ms-input-placeholder {
  color: #ffffff;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-moz-placeholder {
  color: #ffffff;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:-moz-placeholder {
  color: #ffffff;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-webkit-input-placeholder {
  color: #ffffff;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus:-ms-input-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus::-moz-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus:-moz-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:focus::-webkit-input-placeholder {
  opacity: 0;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}

.btn {
  border-radius: 4px;
  height: 44px;
  font-size: 18px;
  font-weight: 600;
  text-transform: uppercase;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  padding: 0 30px;
  letter-spacing: 1px;
  display: -webkit-inline-flex;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;
  text-align: center;
  border: none;
  background-color: #653fd3;
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(255, 235, 167, 0.2);
}
.btn:active,
.btn:focus {
  background-color: #3b2677;
  color: #faeaff;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}
.btn:hover {
  background-color: #3b2677;
  color: #faeaff;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}

.btncheck {
  border-radius: 4px;
  height: 48px;
  font-size: 12px;
  font-weight: 400;
  text-transform: uppercase;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  padding: 0 10px;
  letter-spacing: 1px;
  display: -webkit-inline-flex;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-align-items: center;
  -moz-align-items: center;
  -ms-align-items: center;
  align-items: center;
  -webkit-justify-content: center;
  -moz-justify-content: center;
  -ms-justify-content: center;
  justify-content: center;
  -ms-flex-pack: center;
  text-align: center;
  border: none;
  border-style: solid;
  border-width: 2px;
  border-color: #afa8ba;
  background-color: #f8f8f8;
  color: #000000;
  box-shadow: 0 8px 24px 0 rgba(255, 235, 167, 0.2);
}
.btncheck:active,
.btncheck:focus {
  background-color: #3b2677;
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}
.btncheck:hover {
  background-color: #3b2677;
  color: #ffffff;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}

.pw-msg {
  color: #757575;
  font-size: small;
}

.logo {
  position: absolute;
  top: 30px;
  right: 30px;
  display: block;
  z-index: 100;
  transition: all 250ms linear;
}
.logo img {
  height: 26px;
  width: auto;
  display: block;
}
</style>
