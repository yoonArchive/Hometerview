<template>
  <div>비밀번호 변경 페이지</div>
  <from>
    <div>현재 비밀번호</div>
    <input type="password" v-model="currentpassword"/>
    <div>변경할 비밀번호</div>
    <input type="password" v-model="tochange"/>
    <div>비밀번호 확인</div>
    <input type="password" v-model="tochangeconfirm"/>
    <button @click="changepassword">변경</button>
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
    }
  },
  setup(){
    const currentpassword = "";
    const tochange = "";
    const tochangeconfirm = "";


    return {currentpassword, tochange, tochangeconfirm}
  }

}
</script>

<style>

</style>
