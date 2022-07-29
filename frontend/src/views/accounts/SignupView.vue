<template>

    <h1 class="signup-title">Signup</h1>

    {{ comfirmEmail }}
    <!-- <account-error-list v-if="authError"></account-error-list> -->

    <!-- <div v-if="sdf"></div> -->

    <form @submit.prevent="signupComfirmation(credentials)" >
      <div class="id-input">
        <label for="userId">ID: </label>
        <input class="round-box" v-model="credentials.userId" type="text" id="id"  required/>
        <form @submit.prevent="idDuplicateCheck(credentials.userId)">
          <button>중복체크</button>
        </form>
      </div>
      <div class="pwd-input">
        <label for="userPw">Password: </label>
        <input class="round-box" v-model="credentials.userPw" type="password" id="userPw" placeholder="영문자+숫자+특수문자 조합으로 9~16자리" required />
      </div>
      <div class="pwd-comfirm-input">
        <label for="userPw2">Password Confirmation:</label>
        <input class="round-box" v-model="credentials.userPw2" type="password" id="userPw2" required />
      </div>
      <div class="user-name-input">
        <label for="userName">User Name: </label>
        <input class="round-box"  v-model="credentials.userName" type="text" id="userName" required/>
      </div>
      <div class="email-input">
        <label for="userEmail">e-mail: </label>
        <input class="round-box" v-model="credentials.userEmail" type="email" id="userEmail" placeholder="ssafy@gmail.com" required/>
        <form @submit.prevent="emailDuplicateCheck(credentials.userEmail)">
          <button>중복체크</button>
        </form>
        <form @submit.prevent="sendAuthKeyToEmail(credentials.userEmail)">
          <button>인증번호 받기</button>
        </form>
        <form @submit.prevent="checkAuthKey([authKey,credentials.userEmail])">
          <label for="checkAuthKey"></label>
          <input  v-model="authKey" type="text" id="checkAuthKey">
          <button>인증번호 확인</button>
        </form>
      </div>
      <div>
        <button class="signup-button">Signup</button>
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
      ...mapGetters([
        'authError',
        'isDuplicatedEmail',
        'isDuplicatedId',
        'isAuthorized',
        'comfirmEmail',
        ]),
    },
    methods: {
      ...mapActions([
        'signup',
        'emailDuplicateCheck',
        'idDuplicateCheck',
        'sendAuthKeyToEmail',
        'checkAuthKey',
        'changeFalseAuthState',
        'changeAuthState'
        ]),
      async signupComfirmation(){
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
        else if(this.isAuthorized===false){
          alert('인증번호 확인을 해주세요')
          return
        }
        else{
          await this.signup(this.credentials)
          await this.changeFalseAuthState()
        }
      },

    },
    watch:{
      // 이메일창이랑 confirm함수가 다르면 changeAuthState=> false로 변환


      
      // checkSameEmail(){
      //   console.log(this.comfirmEmail)
      //   console.log(this.credentials.userEmail)
      //   if (this.credentials.userEmail===this.comfirmEmail){
      //     this.changeAuthState(true)
      //   }else{
      //     this.changeAuthState(false)
      //   }
      // }
    }
  }
</script>

<style>
 .signup-title{
  position: absolute;
  width: 350px;
  height: 82px;
  left: 545px;
  top: 120px;

  font-family: 'BM JUA_TTF';
  font-style: normal;
  font-weight: 400;
  font-size: 60px;
  line-height: 82px;
  /* identical to box height, or 137% */

  letter-spacing: 0.1px;

  /* neutral/black */

  color: #2B2B43;
 }
 .id-input{
  position: absolute;
  width: 350px;
  height: 84px;
  left: 545px;
  top: 252px;
 }
 .pwd-input{
  position: absolute;
  width: 350px;
  height: 84px;
  left: 545px;
  top: 336px;
 }
 .pwd-comfirm-input{
  position: absolute;
  width: 350px;
  height: 84px;
  left: 545px;
  top: 420px;
 }
 .user-name-input{
  position: absolute;
  width: 350px;
  height: 84px;
  left: 545px;
  top: 504px;
 }
 .email-input{
  position: absolute;
  width: 350px;
  height: 123px;
  left: 545px;
  top: 588px;
 }

 .signup-button{
  position: absolute;
  width: 200px;
  height: 40px;
  left: 634px;
  top: 761px;

  background: #653FD3;
  border-radius: 8px;
 }


 .round-box{
  box-sizing: border-box;
  
  width: 350px;
  height: 40px;

  border: 1px solid #C7C8D2;
  border-radius: 8px;
 }



</style>
