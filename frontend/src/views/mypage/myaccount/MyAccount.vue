<template>
  <div class="container">
    <div id="myaccount-title"><h2>내 계정 정보</h2></div>
    <!-- <div id="inner-myaccount">
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

      <div id="name">
        <span class="content">이름</span>
        <input
          type="text"
          v-model="this.userdata.userName"
          class="form-control name"
        />
      </div>

      <div id="email">
        <span class="content">이메일</span>
        <div class="row" style="margin-left:1px">
          <input
            type="email"
            v-model="this.userdata.userEmail"
            class="form-control email col-md-9"
          />
          <button class="col-md-2" style="margin-left:1px">인증</button>
        </div>
      </div>
    </div>
    <button @click="this.updateUser(this.userdata)" id="button">
      변경하기
    </button> -->
    <div id="inner-myaccount">
      <div class="wrap">
        <div class="form-item">
          <label class="content">프로필 사진</label>
          <input type="file" placeholder="파일을 선택해주세요." />
        </div>
        <div id="name" class="form-item">
          <label class="content">이름</label>
          <input
            type="text"
            v-model="this.userdata.userName"
            class="form-control name"
          />
        </div>
        <div class="form-item">
          <label class="content">이메일</label>
          <div class="input-group mb-3">
            <input
              type="email"
              class="form-control"
              v-model="this.userdata.userEmail"
              aria-describedby="basic-addon2"
            />
            <div class="input-group-append">
              <button
                class="btn btn-outline-secondary"
                type="button"
                style="margin-left:10px"
              >
                인증
              </button>
            </div>
          </div>
        </div>
      </div>
      <button @click="this.updateUser(this.userdata)" id="button" class="btn">
        저장
      </button>
    </div>
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
.wrap {
  display: flex;
  flex-direction: column;
  /* margin-top: 50px; */
}

.wrap > .form-item {
  margin-bottom: 20px;
}

.form-item {
  display: flex;
  flex-wrap: wrap;
}

.form-item > label {
  width: 140px;
  padding-right: 20px;
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.form-item > input {
  flex-grow: 1;
}

#myaccount-title {
  font-family: "티머니 둥근바람 볼드";
  margin-bottom: 50px;
}

#inner-myaccount {
  border: 2px solid;
  padding: 35px;
  border-color: rgb(241, 241, 241);
  border-radius: 20px;
  width: 800px;
}

label {
  font-size: 18px;
}

.btn {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 5px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #ffffff;
  color: #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 600;
  height: 3em;
  width: 5em;
  letter-spacing: 0.15em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.btn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.btn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
/*

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
  text-align: left;
}

.content {
  font-size: 17px;
}

.name {

  width: 700px;
}
.email {

  width: 550px;
}
.container {
  display: flex;
  flex-direction: column;
  margin-bottom: 100px;
  height: 100%;
} */
</style>
