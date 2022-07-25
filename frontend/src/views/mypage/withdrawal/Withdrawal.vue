<template>
  <div>회원 탈퇴 페이지</div>
  <input type="checkbox" name="withdrawal-agree" v-model="withdrawalAgree"/>
  <button>계정 삭제</button>
</template>

<script>
export default {
  data(){
    return{
      withdrawalAgree,
    }
  },

  mehtod:{
    deleteaccount(){

    },
  }
}
</script>

<style>

</style>
