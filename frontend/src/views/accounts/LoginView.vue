<template>

  <h1 class="loginTitile">Login</h1>
  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="login(credentials)">
    <div class="idInput">
      <label for="id">ID: </label>
      <input class="round-box" v-model="credentials.userId" type="text" id="id" required />
    </div>

    <div class="pwdInput">
      <label for="password">password: </label>
      <input class="round-box" v-model="credentials.userPw" type="password" id="password" required />
    </div>

    <button class="loginButton">Login</button>
  </form>
  <router-link to="/home/findid">
    <div class="find_id" >
      아이디 찾기
    </div>
  </router-link>
  <router-link to="/home/finduserpassword">
    <div class="find_pwd" >
      비밀번호 찾기
    </div>
</router-link>

</template>


<script>
  import { mapActions, mapGetters } from 'vuex'
  // import AccountErrorList from './components/AccountErrorList.vue'


  export default {
    name: 'LoginView',
    components: {
      // AccountErrorList
    },

    data() {
      return {
        credentials: {
          userId: '',
          userPw: ''
        }
      }
    },
    computed: {
      ...mapGetters(['authError'])
      },
    methods: {

      ...mapActions(['login'])
    },
  }
</script>




<style>
 .round-box{
  box-sizing: border-box;
  
  width: 350px;
  height: 40px;

  border: 1px solid #C7C8D2;
  border-radius: 8px;
 }


.find_id{
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 12px 16px;
  gap: 8px;

  position: absolute;
  width: 350px;
  height: 44px;
  left: 593px;
  top: 672px;
}
.find_pwd{
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 12px 16px;
  gap: 8px;

  position: absolute;
  width: 350px;
  height: 44px;
  left: 700px;
  top: 672px;
}

.loginTitile{
  position: absolute;
  width: 262px;
  height: 52px;
  left: 589px;
  top: 248px;

  font-family: 'BM JUA_TTF';
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
  position: absolute;
  width: 350px;
  height: 84px;
  left: 593px;
  top: 368px;
}
.pwdInput{
  position: absolute;
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

  position: absolute;
  width: 350px;
  height: 44px;
  left: 593px;
  top: 579px;

  background: #653FD3;
  box-shadow: 0px 8px 20px rgba(78, 96, 255, 0.16);
  border-radius: 8px;
}


</style>
