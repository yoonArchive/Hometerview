<template>
  <div class="container">
    <div id="myaccount-title"><h2>비밀번호 변경</h2></div>
    <!-- <div id="inner-myaccount">
      <from>
        <div>현재 비밀번호</div>

        <input type="password" v-model="currentpassword" class="form-control" />
        <br />
        <div>변경할 비밀번호</div>

        <input
          placeholder="영문, 숫자, 특수기호 포함 8~20자"
          type="password"
          v-model="tochange"
          class="form-control"
        />
        <br />
        <div>비밀번호 확인</div>
        <input type="password" v-model="tochangeconfirm" class="form-control" />
        <br />
      </from>
    </div>
    <button id="button" @click="signupComfirmation">변경하기</button>
  </div> -->
    <div id="inner-myaccount">
      <div class="wrap">
        <div class="form-item">
          <label class="content">현재 비밀번호</label>
          <input
            type="password"
            v-model="currentpassword"
            class="form-control"
          />
        </div>
        <div id="tochange" class="form-item">
          <label class="content">변경 비밀번호</label>
          <input
            placeholder="영문, 숫자, 특수기호 포함 8~20자"
            type="password"
            v-model="tochange"
            class="form-control"
          />
        </div>
        <div class="form-item">
          <label class="content">비밀번호 확인</label>
          <input
            type="password"
            v-model="tochangeconfirm"
            class="form-control"
          />
        </div>
      </div>
      <button id="button" class="btn" @click="signupComfirmation">
        비밀번호 변경
      </button>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  methods: {
    ...mapActions(["changePassword"]),
    changepassword() {
      //앞에서 유효성 검사
      console.log(this.currentpassword + " : " + this.tochange);
      const credentials = {
        userPw: this.currentpassword,
        newPw: this.tochange
      };
      this.changePassword(credentials);
    },
    signupComfirmation() {
      const credentials = {
        userPw: this.currentpassword,
        newPw: this.tochange
      };

      if (credentials.newPw === "") {
        alert("모든 내용을 입력해주세요.");
        return;
      } else if (!this.pwdCheckPattern.test(credentials.newPw)) {
        alert(
          "비밀번호는 영문자+숫자+특수문자 조합으로 9~16자리를 사용해야합니다."
        );
        return;
      } else {
        this.changePassword(credentials);
        alert("성공적으로 변경하였습니다.");
      }
    }
  },
  setup() {
    const currentpassword = "";
    const tochange = "";
    const tochangeconfirm = "";

    const pwdCheckPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+-])(?=.*[0-9]).{9,16}$/;

    return { currentpassword, tochange, tochangeconfirm, pwdCheckPattern };
  }
};
</script>

<style scoped>
.wrap {
  display: flex;
  flex-direction: column;
  /* margin-top: 50px; */
}

.wrap > .form-item {
  margin-bottom: 20px;
}

.form-item {
  display: flex;
  flex-wrap: wrap;
}

.form-item > label {
  width: 140px;
  padding-right: 20px;
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.form-item > input {
  flex-grow: 1;
}

#myaccount-title {
  font-family: "티머니 둥근바람 볼드";
  margin-bottom: 50px;
}

#inner-myaccount {
  border: 2px solid;
  padding: 35px;
  border-color: rgb(241, 241, 241);
  border-radius: 20px;
  width: 800px;
}

label {
  font-size: 18px;
}

.btn {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 5px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #ffffff;
  color: #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 600;
  height: 3em;
  width: 11em;
  letter-spacing: 0.15em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.btn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.btn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
</style>
