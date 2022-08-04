<template>
  <input type="password" placeholder="비밀번호를 입력하세요!" v-model="passwordToConfirm.userPw"/>
  <button @click="confirmpassword">확인</button>
</template>

<script>

import { mapActions } from 'vuex'
export default {
  data(){
    return{

      passwordToConfirm : {
        userPw:"",
      }
    }
  },
  methods:{
    ...mapActions(['passwordConfirm']),
    confirmpassword(){
      this.passwordConfirm(this.passwordToConfirm);
    }
  },

}
</script>

<style>

</style>
