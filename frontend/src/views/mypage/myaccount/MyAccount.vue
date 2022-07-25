<template>
  <div>내 계정 정보</div>
  <div>
    <img id="preview" src="@/assets/images/notloginimg.png" />
    <form enctype="multipart/form-data">
    <input type="file"
      @change="readURL"
      id="profileimg"
      accept=".jpg, .png, .gif"
      class="mt-3"
      plain
      style="display: none"/>
    </form>
    <button onclick="document.getElementById('profileimg').click();">변경</button>

    <div>이메일</div>
    <input type="email" v-model="this.userdata.userEmail"/>
    <div>이름</div>
    <input type="text" v-model="this.userdata.userName"/>
    <input type="text" v-model="this.userdata.userName"/>
    <button @click="this.updateuser">변경 저장하기</button>
  </div>
</template>

<script>


import { mapGetters, mapActions } from 'vuex';


export default {
  data(){
    return{
      userdata : {
        userId : "",
        userName : "",
        userEmail : "",
        userImg : File,
      }
    }
  },

  computed:{
    ...mapGetters(['currentUser']),


  },
  methods:{
    ...mapActions(['updateUser','fetchCurrentUser']),
    inFileChange(e){
      console.log(e)
      var file = e.target.files || e.dataTranfer.files;
      if(!file.length){
        return
      }
      this.readURL(file[0]);
    },
    readURL(input) {
      console.log(input.target.files[0]);
      this.userdata.userImg = input.target.files[0];
      var reader = new FileReader();
      reader.onload = function (e) {
        document.getElementById("preview").src = e.target.result;
      };
      reader.readAsDataURL(input.target.files[0]);
    },
    setting(){
      console.log(this.currentUser);
      console.log(this.currentUser.userId);
      // document.getElementById("preview").src = this.currentUser.userImg;

      this.userdata.userId  = this.currentUser.userId;
      this.userdata.userName = this.currentUser.userName;
      this.userdata.userEmail = this.currentUser.userEmail;
      this.userdata.userImg = this.currentUser.userImg;
      console.log(this.userdata);
      console.log(this.currentUser);
    },
    updateuser(){
      console.log(this.userdata);
      this.updateUser(this.userdata);
    },
    async refreshsetting(){
      await this.fetchCurrentUser();
      await this.setting();
    }
  },
  mounted(){
    this.refreshsetting();

  },
  created(){
    this.refreshsetting();
  }

}
</script>

<style>

</style>
