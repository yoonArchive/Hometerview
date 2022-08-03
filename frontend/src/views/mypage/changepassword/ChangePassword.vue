<template>
  <div>비밀번호 변경 페이지</div>
  <from>
    <div>현재 비밀번호</div>
    <input type="password" v-model="currentpassword"/>
    <div>변경할 비밀번호</div>
    <input type="password" v-model="tochange"/>
    <div>비밀번호 확인</div>
    <input type="password" v-model="tochangeconfirm"/>
    <button @click="signupComfirmation">변경</button>
  </from>
</template>

<script>
import { mapActions } from 'vuex';
export default {

  methods:{
    ...mapActions(['changePassword']),
    changepassword(){
      //앞에서 유효성 검사
      console.log(this.currentpassword +  " : " + this.tochange);
      const credentials = {
        userPw: this.currentpassword,
        newPw : this.tochange
      }
      this.changePassword(credentials);

    },
    signupComfirmation(){
      const credentials = {
        userPw: this.currentpassword,
        newPw : this.tochange
      }

        if(
          credentials.newPw=== ''
        ){
          alert("모든 내용을 입력해주세요.")
          return
        }
        else if(!this.pwdCheckPattern.test(credentials.newPw)){
          alert('비밀번호는 영문자+숫자+특수문자 조합으로 9~16자리를 사용해야합니다.')
          return
        }else{
          this.changePassword(credentials)
          alert("성공적으로 변경하였습니다.")
        }

  }

  },
  setup(){
    const currentpassword = "";
    const tochange = "";
    const tochangeconfirm = "";

    const pwdCheckPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+-])(?=.*[0-9]).{9,16}$/


    return {currentpassword, tochange, tochangeconfirm, pwdCheckPattern}
  }

  }
</script>

<style>

</style>
