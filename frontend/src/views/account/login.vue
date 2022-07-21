<template>
  LOGIN
  <form>
    <input type="text"/>
    <input type="text"/>
    <imput type="button" @click="login">로그인</imput>
  </form>
</template>

<script>
import { useStore } from 'vuex'
export default {
  setup(){
    let store = useStore();
    let logininfo = {
      id:'',
      password:"",
    }
    function login (){
      store.dispatch('loginUser',logininfo);
    }
    return { logininfo, login }
  }
}
</script>

<style>

</style>
