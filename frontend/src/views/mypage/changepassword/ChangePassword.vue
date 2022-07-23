<template>
  <div>비밀번호 변경 페이지</div>
  <from>
    <div>현재 비밀번호</div>
    <input type="password" v-model="currentpassword"/>
    <div>변경할 비밀번호</div>
    <input type="password"/>
    <div>비밀번호 확인</div>
    <input type="password"/>
  </from>
</template>

<script>
export default {

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
