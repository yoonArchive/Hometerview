<template>

    <h1>Signup</h1>

    <!-- <account-error-list v-if="authError"></account-error-list> -->

    <!-- <div v-if="sdf"></div> -->

    <form @submit.prevent="signupComfirmation(credentials)" >
      <div>
        <label for="userEmail">e-mail: </label>
        <input v-model="credentials.userEmail" type="email" id="userEmail"  required/>
        <form @submit.prevent="emailDuplicateCheck(credentials.userEmail)">
          <button>check</button>
        </form>
      </div>
      <div>
        <label for="userName">User Name: </label>
        <input  v-model="credentials.userName" type="text" id="userName" required/>
      </div>
      <div>
        <label for="userId">ID: </label>
        <input  v-model="credentials.userId" type="text" id="id"  required/>
        <form @submit.prevent="idDuplicateCheck(credentials.userId)">
          <button>check</button>
        </form>

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
        <button>Signup</button>
      </div>
    </form>


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
        validationPattern:{
          pwdCheckPattern : /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+-])(?=.*[0-9]).{9,16}$/,
          idCheckPattern : /^[a-zA-z0-9].{1,16}$/,
          eamilCheckPattern : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/,
        },

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
      ...mapGetters(['authError','isDuplicatedEmail','isDuplicatedId']),
    },
    methods: {
      ...mapActions(['signup','emailDuplicateCheck', 'idDuplicateCheck']),



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
        }
        else if(!this.validationPattern.idCheckPattern.test(this.credentials.userId)){
          console.log()
          console.log(!this.validationPattern.idCheckPattern.test(this.credentials.userId))
          alert('아이디는 영문자 또는 숫자만 가능합니다.')
          return
        }
        else if(!this.validationPattern.pwdCheckPattern.test(this.credentials.userPw)){
          alert('비밀번호는 영문자+숫자+특수문자 조합으로 9~16자리를 사용해야합니다.')
          return
        }
        else if(!this.validationPattern.eamilCheckPattern.test(this.credentials.userEmail)){
          alert('이메일 형태가 아닙니다. 다시 확인해주세요')
          return
        }
        else if (this.credentials.userPw !== this.credentials.userPw2){
          alert('비밀번호가 일치하지 않습니다.')
          return
        }
        else if (this.isDuplicatedEmail){
          alert('이메일 중복검사를 해주세요')
          return
        }
        else if (this.isDuplicatedId){
          alert('아이디 중복검사를 해주세요')
          return
        }
        else{
          this.signup(this.credentials)
        }


      },

    },
  }
</script>

<style>


</style>
