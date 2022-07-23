<template>
  <el-row>
    <el-col :span="8">
      <div>
        <el-button type="primary">Primary</el-button>
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
    </el-col>
    <el-col :span="16">
      <div>
        <router-view></router-view>
      </div>
    </el-col>
  </el-row>
</template>

<script>

export default {
  setup(){

  }
}
</script>

<style lang="scss">
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
