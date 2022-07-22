<template>

  <from>
    <input type="email" placeholder="이메일" v-model="userData.userEmail"/>
    <input type="text" placeholder="아이디" v-model="userData.userId"/>
    <input type="text" placeholder="아이디" v-model="userData.userName"/>
    <input type="submit" value="몰라"/>
    <button @click="findPassword">ㄱㄱ</button>
  </from>
</template>

<script>
import api_url from '@/api/api_url'
import axios from 'axios';
import http from '@/api/http'
export default {
  setup(){
    const userData = {
      userEmail:'',
      userId:'',
      userName:'',
    }
    function findPassword(){
      console.log(this.userData);
      http.post('/users/findpw/',null,{
        params:this.userData
      }).then(data=>{
        console.log(data);
      }).catch(error =>{
        console.log("또야?" + error);
      })

      // axios({
      //   url:api_url.accounts.findUserPassword(),
      //   method:'post',
      //   data: JSON.stringify(userData)
      // })
      // .then((data)=>{
      //   console.log(data);
      // })
      // .catch((error)=>{
      //   alert('잘못된 정보를 입력하셨습니다.' + error);
      // })
      // axios({
      //   url: api_url.accounts.findPassWord(),
      //   method:'POST',
      //   data : userData
      // }).then((data)=>{
      //   console.log(data);
      //   alert(userData.name +'님의 임시 비밀번호는 '+ data.userPw + '입니다.');
      // }).catch((error)=>{
      //   alert('잘못된 정보를 입력하셨습니다.' + error);
      // })
    }
    return {userData, findPassword}
  }
}
</script>

<style>

</style>
