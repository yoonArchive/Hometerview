<template>

  <h1>Login</h1>
  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="login(credentials)">
    <div>
      <label for="id">ID: </label>
      <input v-model="credentials.userId" type="text" id="id" required />
    </div>

    <div>
      <label for="password">password: </label>
      <input v-model="credentials.userPw" type="password" id="password" required />
    </div>

    <button>Login</button>



  </form>
  <section class="test">
    <div v-on:click="kakaoLoginBtn" id="kakao">카카오 연동</div>
  </section>
<img src="profile" alt="">
</template>


<script>

import { mapActions, mapGetters, mapState } from 'vuex'

  // import AccountErrorList from './components/AccountErrorList.vue'
  // import { mapGetters,mapActions } from './map_state.js'


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
        },
        profile:''
      }
    },
    computed: {
      ...mapGetters(['authError']),
      ...mapState(['token','kakaoToken']),

      },
    methods: {

      ...mapActions(['login', 'kakaoLoginBtn']),
    },
    setup() {
      return { }
    },
    mounted() {

    }

  }
</script>




<style scoped>
.test{  display:flex; justify-content: center; align-items: center; height:100vh; }
/* div{ width: 200px; height:40px; background-color:#fdd101; color:white; display:flex; align-items: center; justify-content: center; cursor:pointer; } */
#kakao {
  border-radius: 30px;
  background-color:#fdd101;
  color:white;
  font-size: 15px
}

</style>
