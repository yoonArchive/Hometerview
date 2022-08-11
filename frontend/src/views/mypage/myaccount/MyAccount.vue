<template>
  <div class="container">
    <div id="myaccount-title"><h2>내 계정 정보</h2></div>
    <div id="inner-myaccount">
      <div id="profile">
        <img id="preview" src="@/assets/images/notloginimg.png" />
        <form enctype="multipart/form-data">
          <input
            type="file"
            @change="readURL"
            id="profileimg"
            accept=".jpg, .png, .gif"
            class="mt-3"
            plain
            style="display: none"
          />
        </form>
        <button
          onclick="document.getElementById('profileimg').click();"
          id="buttoni"
        >
          변경
        </button>
      </div>

      <div id="email">
        이메일
        <input
          type="email"
          v-model="this.userdata.userEmail"
          class="form-control"
        />
      </div>

      <div id="name">
        이름
        <input
          type="text"
          v-model="this.userdata.userName"
          class="form-control"
        />
      </div>
    </div>
    <button @click="this.updateUser(this.userdata)" id="button">
      변경하기
    </button>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  data() {
    return {
      userdata: {
        userId: "",
        userName: "",
        userEmail: "",
        userImg: File
      }
    };
  },

  computed: {
    ...mapGetters(["currentUser"])
  },
  methods: {
    ...mapActions(["updateUser", "fetchCurrentUser"]),
    inFileChange(e) {
      console.log(e);
      var file = e.target.files || e.dataTranfer.files;
      if (!file.length) {
        return;
      }
      this.readURL(file[0]);
    },
    readURL(input) {
      console.log(input.target.files[0]);
      this.userdata.userImg = input.target.files[0];
      var reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById("preview").src = e.target.result;
      };
      reader.readAsDataURL(input.target.files[0]);
    },
    setting() {
      console.log(this.currentUser);
      console.log(this.currentUser.userId);
      // document.getElementById("preview").src = this.currentUser.userImg;

      this.userdata.userId = this.currentUser.userId;
      this.userdata.userName = this.currentUser.userName;
      this.userdata.userEmail = this.currentUser.userEmail;
      this.userdata.userImg = this.currentUser.userImg;
      console.log(this.userdata);
      console.log(this.currentUser);
    },
    updateuserM() {
      console.log(this.userdata);
      this.updateUser(this.userdata);
    },
    async refreshsetting() {
      await this.fetchCurrentUser();
      await this.setting();
    }
  },
  mounted() {
    this.refreshsetting();
  },
  created() {
    this.refreshsetting();
  }
};
</script>

<style scoped>
#inner-myaccount {
  border: 1px solid;
  width: 200%;
  height: 300%;
  padding: 30px;
  border-color: rgb(134, 132, 132);
  border-radius: 10px;
}
#name {
  /* margin: 20px; */
}
#profile {
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  margin-bottom: 20px;
}
#button {
  margin: 10px;
  background-color: #6610f2;
  color: white;
  border-radius: 10%;
  border: white;
  width: 30%;
}
#buttoni {
  margin: 10px;
  background-color: #6610f2;
  color: white;
  border-radius: 10%;
  border: white;
  width: 10%;
}
#button:hover {
  background-color: #6f42c1;
}
#inner-myaccount {
  /* text-align: center; */
  text-align: left;
}
/* 내계정정보 타이틀 움직이는용도 */
#myaccount-title {
  margin-top: 50px;
  margin-bottom: 60px;
}
.form-control {
  /* margin-top: 10px; */
  /* margin-bottom: 10px; */
  width: 350px;
}
.container {
  display: flex;
  flex-direction: column;
  margin-bottom: 100px;
  height: 100%;
}
</style>
