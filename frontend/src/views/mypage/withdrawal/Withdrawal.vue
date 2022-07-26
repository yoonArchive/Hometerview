<template>
  <div>회원 탈퇴 페이지</div>
  <form @submit.prevent>
    <input type="checkbox" name="withdrawal-agree" v-model="withdrawalAgree"/>
    <input type="button" @click="this.deleteaccount" value="계정삭제"/>
    <button @click="this.deleteaccount">계정삭제</button>
  </form>
</template>

<script>
import { mapActions } from 'vuex'
export default {

  data(){
    return{
      withdrawalAgree : false,
    }
  },

  methods :{
    ...mapActions(['deleteUser']),
    deleteaccount(){
      if(this.withdrawalAgree == true){

        console.log('???');
        alert('???');
        this.deleteUser();
      }else{
        console.log('???');
        alert('동의를 눌러주세요');
      }

    },
  }
}
</script>

<style>

</style>
