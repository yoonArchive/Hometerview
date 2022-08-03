<template>
  <!-- <section class="login">
     <h1 class="loginTitile">Login</h1>
     <account-error-list v-if="authError"></account-error-list>

    <form @submit.prevent="login(credentials)">
      <div class="idInput">
        <label for="id">아이디: </label>
        <input
          class="round-box"
          v-model="credentials.userId"
          type="text"
          id="id"
          required
        />
      </div>
      <div class="pwdInput">
        <label for="password">비밀번호: </label>
        <input
          class="round-box"
          v-model="credentials.userPw"
          type="password"
          id="password"
          required
        />
      </div>

      <button class="loginButton">로그인</button>
    </form>

    <router-link to="/home/findid">
      <div class="find_id">
        아이디 찾기
      </div>
    </router-link>
    <router-link to="/home/finduserpassword">
      <div class="find_pwd">
        비밀번호 찾기
      </div>
    </router-link>
  </section> -->

  <!-- <section class="test">
    <div v-on:click="kakaoLoginBtn" id="kakao">카카오 연동</div>
  </section>
  <img src="profile" alt="" /> -->

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
                      <!-- 아이디 input -->
                      <form @submit.prevent="login(credentials)">
                        <div class="form-group">
                          <input
                            type="text"
                            class="form-style"
                            placeholder="아이디"
                            id="id"
                            v-model="credentials.userId"
                            required
                          />
                          <i class="input-icon uil uil-at"></i>
                        </div>
                        <!-- 비밀번호 input -->
                        <div class="form-group mt-2">
                          <input
                            type="password"
                            class="form-style"
                            placeholder="비밀번호"
                            id="password"
                            v-model="credentials.userPw"
                            required
                          />
                          <i class="input-icon uil uil-lock-alt"></i>
                        </div>
                        <button class="btn mt-4">로그인</button>
                        <!-- <a href="#" class="btn mt-4">submit</a> -->
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
                      <div class="form-group">
                        <input
                          type="text"
                          name="logname"
                          class="form-style"
                          placeholder="Your Full Name"
                          id="logname"
                          autocomplete="off"
                        />
                        <i class="input-icon uil uil-user"></i>
                      </div>
                      <div class="form-group mt-2">
                        <input
                          type="email"
                          name="logemail"
                          class="form-style"
                          placeholder="Your Email"
                          id="logemail"
                          autocomplete="off"
                        />
                        <i class="input-icon uil uil-at"></i>
                      </div>
                      <div class="form-group mt-2">
                        <input
                          type="password"
                          name="logpass"
                          class="form-style"
                          placeholder="Your Password"
                          id="logpass"
                          autocomplete="off"
                        />
                        <i class="input-icon uil uil-lock-alt"></i>
                      </div>
                      <a href="#" class="btn mt-4">submit</a>
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
      profile: ""
    };
  },
  computed: {
    ...mapGetters(["authError"]),
    ...mapState(["token", "kakaoToken"])
  },
  methods: {
    ...mapActions(["login", "kakaoLoginBtn"])
  },
  setup() {
    return {};
  },
  mounted() {}
};
</script>

<style scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
input[type="text"]:focus {
  border: 3px solid #555;
}
.round-box {
  box-sizing: border-box;

  width: 350px;
  height: 40px;

  border: 1px solid #c7c8d2;
  border-radius: 8px;
}
.round-box:focus {
  border: 3px solid #9779f0;
  outline: 3px #9779f0;
}

.find_id {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 12px 16px;
  gap: 8px;

  /* position: absolute; */
  width: 350px;
  height: 44px;
  left: 593px;
  top: 672px;
}
.find_pwd {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 12px 16px;
  gap: 8px;

  /* position: absolute; */
  width: 350px;
  height: 44px;
  left: 700px;
  top: 672px;
}

.loginTitile {
  /* position: absolute; */
  margin-top: 100px;
  display: flex;
  justify-content: center;
  width: 262px;
  height: 52px;
  left: 589px;
  top: 248px;

  font-family: "BM JUA_TTF";
  font-style: normal;
  font-weight: 400;
  font-size: 60px;
  line-height: 20px;
  /* or 33% */

  letter-spacing: 0.1px;

  /* neutral/gray dark */

  color: #545563;
}

.idInput {
  /* position: absolute; */
  width: 350px;
  height: 84px;
  left: 593px;
  top: 368px;
}
.pwdInput {
  /* position: absolute; */
  width: 350px;
  height: 84px;
  left: 593px;
  top: 460px;
}
.loginButton {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 12px 16px;
  gap: 8px;

  /* position: absolute; */
  width: 350px;
  height: 44px;
  left: 593px;
  top: 579px;

  background: #653fd3;
  /* box-shadow: 0px 8px 20px rgba(78, 96, 255, 0.16); */
  border-radius: 8px;
  color: white;
  text-align: center;
}
.loginButton:hover {
  background-color: #9779f0;
}
.loginButton:active {
  background-color: #7455d1;
}

.test {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
/* div{ width: 200px; height:40px; background-color:#fdd101; color:white; display:flex; align-items: center; justify-content: center; cursor:pointer; } */
#kakao {
  border-radius: 30px;
  background-color: #fdd101;
  color: white;
  font-size: 15px;
}

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
  background-color: #ffeba7;
}
.checkbox:checked + label:before,
.checkbox:not(:checked) + label:before {
  position: absolute;
  display: block;
  width: 36px;
  height: 36px;
  border-radius: 50%;
  color: #ffeba7;
  background-color: #102770;
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
  width: 440px;
  max-width: 100%;
  height: 400px;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  perspective: 800px;
  margin-top: 60px;
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
  background-color: #ffffff;
  /*background-image: url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg");*/
  background-position: bottom center;
  background-repeat: no-repeat;
  background-size: 300%;
  position: absolute;
  border-radius: 6px;
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
  font-size: 14px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #c4c3ca;
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
.input-icon {
  position: absolute;
  top: 0;
  left: 18px;
  height: 48px;
  font-size: 24px;
  line-height: 48px;
  text-align: left;
  color: #ffeba7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}

.form-group input:-ms-input-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-moz-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input:-moz-placeholder {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
}
.form-group input::-webkit-input-placeholder {
  color: #c4c3ca;
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
  font-size: 13px;
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
  background-color: #ffeba7;
  color: #102770;
  box-shadow: 0 8px 24px 0 rgba(255, 235, 167, 0.2);
}
.btn:active,
.btn:focus {
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
}
.btn:hover {
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16, 39, 112, 0.2);
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
