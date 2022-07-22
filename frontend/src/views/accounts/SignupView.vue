<template>
  <div>
    <h1>Signup</h1>

    <!-- <account-error-list v-if="authError"></account-error-list> -->

    <!-- <div v-if="sdf"></div> -->

    <form @submit.prevent="signup(credentials)">

      <div>
        <label for="userEmail">e-mail: </label>
        <input  v-model="credentials.userEmail" type="text" id="userEmail" required/>
      </div>
      <div>
        <label for="userName">User Name: </label>
        <input  v-model="credentials.userName" type="text" id="userName" required/>
      </div>
      <div>
        <label for="userId">ID: </label>
        <input  v-model="credentials.userId" type="text" id="id" required/>
      </div>
      <div>
        <label for="userPw">Password: </label>
        <input v-model="credentials.userPw" type="password" id="userPw" required />
      </div>
      <div>
        <label for="userPw2">Password Confirmation:</label>
        <input v-model="credentials.userPw2" type="password" id="userPw2" required />
      </div>
      <div>
        <button @click="signupComfirmation">Signup</button>
      </div>
    </form>
    
  </div>
  
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  // import AccountErrorList from '@/components/AccountErrorList'

  export default {
    name: 'SignupView',
    components: {
      // AccountErrorList,
    },
    data() {
      return {
        credentials: {
          userEmail:'',
          userName:'',
          userId: '',
          userPw: '',
          userPw2: ''
        },
      }
    },
    computed: {
      ...mapGetters(['authError']),
      // 함수 == userPw != userPw >> false

    },
    methods: {
      ...mapActions(['signup']),
      signupComfirmation(){
        if(
          this.credentials.userEmail===''||
          this.credentials.userName===''||
          this.credentials.userId=== ''||
          this.credentials.userPw=== ''||
          this.credentials.userPw2=== ''
        ){
          alert("모든 내용을 입력해주세요")
          return
        }else if (this.credentials.userPw !== this.credentials.userPw2){
          alert('비밀번호가 일치하지 않습니다.')
          return
        }
        // 아이디 중복검사 구현



      }
    },
  }
</script>

<style>


</style>
