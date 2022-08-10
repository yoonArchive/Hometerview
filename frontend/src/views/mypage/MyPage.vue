<template>
  <row>
    <div>
      <router-link to="/home/mypage/myaccount">
        <button>계정</button>
      </router-link>
      <router-link to="/home/mypage/changepassword">
        <button>비밀번호 변경</button>
      </router-link>
      <router-link to="/home/mypage/withdrawal">
        <button>회원 탈퇴</button>
      </router-link>
    </div>

    <div>
      <router-view></router-view>
    </div>
  </row>
</template>

<script>
export default {
  setup() {}
};
</script>

<!-- <style lang="scss">
.row {
  margin-bottom: 20px;
}
.row:last-child {
  margin-bottom: 0;
}
.col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
} -->
<!-- </style> -->
