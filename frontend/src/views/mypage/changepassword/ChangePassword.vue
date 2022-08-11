<template>
  <div class="container">
    <div id="myaccount-title"><h2>비밀번호 변경</h2></div>
    <div id="inner-myaccount">
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
.container {
  display: flex;
  flex-direction: column;
  margin-bottom: 100px;
  height: 100%;
}
#myaccount-title {
  margin-top: 20px;
}
#button {
  margin: 10px;
  background-color: #6610f2;
  color: white;
  border-radius: 10%;
  border: white;
  width: 30%;
}
#button:hover {
  background-color: #6f42c1;
}
.form-control {
  /* margin-top: 10px; */
  /* margin-bottom: 10px; */
  width: 350px;
}
#myaccount-title {
  margin-top: 50px;

  margin-bottom: 60px;
}
#inner-myaccount {
  border: 0.1px solid rgb(134, 132, 132);
  width: 200%;
  height: 300%;
  padding: 30px;
  border-color: rgb(134, 132, 132);
  border-radius: 10px;
}
</style>
