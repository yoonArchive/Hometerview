<template>
  <div class="mypage">
    <div class="mypage-banner">
      <router-link to="/home/mypage/myaccount" id="word">
        <button id="mypage-button">
          <i class="input-icon uil uil-user-square"></i>
          <span>계정 기본정보</span>
        </button>
      </router-link>
      <router-link to="/home/mypage/changepassword" id="word">
        <button id="mypage-button">
          <i class="input-icon uil uil-shield-check"></i>
          <span>비밀번호 변경</span>
        </button>
      </router-link>
      <router-link to="/home/mypage/withdrawal" id="word">
        <button id="mypage-button">
          <i class="input-icon uil uil-exclamation-triangle"></i>
          <span>회원 탈퇴</span>
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
  margin-left: 170px;
  /* margin-right: 50px; */
}
.mypage-banner {
  display: flex;
  flex-direction: column;
  margin-top: 104px;
  margin-right: 30px;
  /* margin: 50px; */
  /* position: absolute; */
}
#mypage-button {
  /* border: 1px rgb(160, 160, 160); */
  border: none;
  width: 300px;
  border-radius: 10px;
  height: 50px;
  background-color: #f3f4ff;
  margin: 6px;
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
span {
  font-size: 17px;
  letter-spacing: 0.04em;
}
</style>
