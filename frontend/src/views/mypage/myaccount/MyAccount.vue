<template>
  <div>내 계정 정보</div>
  <form enctype="multipart/form-data">
    <img id="preview" src="@/assets/images/notloginimg.png" />
    <input type="file"
      @change="readURL"
      id="profileimg"
      v-on:change = "inFileChange"
      accept=".jpg, .png, .gif"
      class="mt-3"
      plain
      style="display: none"/>
    <button onclick="document.getElementById('profileimg').click();">변경</button>

    <div>이메일</div>
    <input type="email" v-model="this.userdata.userEmail"/>
    <div>이름</div>
    <input type="text" v-model="this.userdata.userName"/>
    <button @click="updateuser">변경 저장하기</button>
  </form>
</template>

<script>


import { mapGetters, mapActions } from 'vuex';


export default {
  setup(){
      const userdata={
        userId :"",
        userName : "",
        userEmail : "",
        userImg : "",
      }
      return {userdata}
  },

  computed:{
    ...mapGetters(['currentUser']),

  },
  methods:{
    ...mapActions(['updateUser','fetchCurrentUser']),
    inFileChange(e){
      var file = e.target.files || e.dataTranfer.files;
      if(!file.length){
        return
      }
      readURL(file[0]);
    },
    readURL(input) {
      var reader = new FileReader();
      reader.onload = function (e) {
        document.getElementById("preview").src = e.target.result;
        this.userdata.userImg = e.target.result;
      };
      reader.readAsDataURL(input.target.files[0]);
    },
    setting(){
      console.log(this.currentUser);
      // document.getElementById("preview").src = this.currentUser.userImg;

      this.userdata.userId  =this.currentUser.userId;
      this.userdata.userName = this.currentUser.userName;
      this.userdata.userEmail = this.currentUser.userEmail;
      this.userdata.userImg = this.currentUser.userImg;
      console.log(this.currentUser);
    },
    updateuser(){
      console.log(this.userdata);
      updateUser()
    }

  },
  async mounted(){
    console.log("???")
    await this.fetchCurrentUser();
    console.log(this.currentUser);
    this.setting();

  }

}
</script>

<style>

</style>
