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

    <div class="router-view">
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
.router-view {
  height: 100%;
  margin: 10px;
}
#title-word {
  margin-bottom: 50px;
}
.mypage {
  display: flex;
  flex-direction: row;
  /* align-items: center; */
  margin-right: 50px;
}
.mypage-banner {
  display: flex;
  flex-direction: column;
  /* margin: 50px; */
  /* position: absolute; */
}
#mypage-button {
  border: 1px rgb(160, 160, 160);
  width: 300px;
  border-radius: 10px;
  height: 100px;
  background-color: #f3f4ff;
  margin: 10px;
  text-align: left;
  display: flex;
  align-items: center;
}
/* 라우터 버튼 호버*/
#mypage-button:hover #mypageicon {
  background-color: #653fd3;
  border-radius: 10px;
  color: white;
  padding: 10px;
  border: #653fd3;
}
#mypage-button:hover {
  border-radius: 10px;
  border: solid #653fd3;
}
/* #mypage-button:hover, */
.route-active #mypage-button {
  background-color: #653fd3;
  border-radius: 10px;
  color: white;
  padding: 10px;
  border: #653fd3;
  /* cursor: pointer; */
}
/* #mypage-button:active {
  border: solid #653fd3;
} */

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
