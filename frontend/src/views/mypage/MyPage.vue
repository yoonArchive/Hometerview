<template>
  <div class="mypage">
    <div class="mypage-banner">
      <router-link to="/home/mypage/myaccount" id="word">
        <button id="mypage-button">
          <font-awesome-icon
            id="mypageicon"
            icon="fa-solid fa-person-shelter"
          />
          계정 기본정보
        </button>
      </router-link>
      <router-link to="/home/mypage/changepassword" id="word">
        <button id="mypage-button">
          <font-awesome-icon id="mypageicon" icon="fa-solid fa-shield-halved" />
          비밀번호 변경
        </button>
      </router-link>
      <router-link to="/home/mypage/withdrawal" id="word">
        <button id="mypage-button">
          <font-awesome-icon
            id="mypageicon"
            icon="fa-solid fa-person-running"
          />
          회원 탈퇴
        </button>
      </router-link>
    </div>

    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  setup() {}
};
</script>

<style scoped>
.mypage {
  display: flex;
  flex-direction: row;
}
.mypage-banner {
  display: flex;
  flex-direction: column;
  margin: 50px;
}
#mypage-button {
  border: white;
  width: 400px;
  border-radius: 10%;
  height: 100px;
  background-color: #f3f4ff;
  margin: 10px;
  text-align: left;
  display: flex;
  align-items: center;
}
#mypage-button:hover #mypageicon {
  background-color: #653fd3;
  border-radius: 10%;
  color: white;
  padding: 10px;
  border: #653fd3;
}
#mypage-button:hover {
  border-radius: 10%;
  border: solid #653fd3;
}
#mypageicon {
  width: 50px;
  height: 50px;
  padding: 10px;
  border-radius: 10%;
  margin-right: 30px;
}
#word {
  text-decoration-line: none;
}
</style>
