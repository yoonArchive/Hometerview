<template>
  <div class="container">
    <div class="inner-container">
      <p id="title">{{ notice.noticeTitle }}</p>
      <p id="notice-info">
        <i class="input-icon uil uil-edit"></i>&nbsp;관리자<span
          style="width:2em"
        ></span
        ><i class="input-icon uil uil-calendar-alt"></i> {{ notice.writeDate
        }}<span style="float:right; margin-right:40px;">
          <span v-if="isAdmin">
            <span style="margin-right:3px;">
              <router-link :to="{ name: 'noticeEdit', params: { noticeNo } }">
                수정
              </router-link>
            </span>
            <span class="deleteBtn" @click="deleteNotice(noticeNo)">
              삭제
            </span>
          </span>
          <span style="margin-right:3px;">
            <router-link class="routerlink" :to="{ name: 'notices' }">
              목록
            </router-link>
          </span>
        </span>
      </p>
      <hr />
    </div>
    <span style="margin-top:20px">{{ notice.noticeContents }}</span>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "noticeDetail",
  components: {},
  data() {
    return {
      noticeNo: this.$route.params.noticeNo,
      // notice: useStore.state.notice,
      animating: false
    };
  },
  computed: {
    ...mapGetters(["isAuthor", "notice", "isAdmin"])
  },
  methods: {
    ...mapActions(["fetchNotice", "deleteNotice"]),

    onIconAnimationEnds() {
      this.animating = false;
    }
  },
  created() {
    this.fetchNotice(this.noticeNo);
    console.log("이즈어드민" + this.isAdmin);
  }
};
</script>

<style scoped>
#title {
  font-size: 40px;
  text-align: left;
}
#notice-info {
  font-size: 17px;
  color: rgb(129, 129, 129);
}
a {
  text-decoration: none;
  color: rgb(129, 129, 129);
}
a:hover {
  color: #653fd3;
  font-weight: bold;
}
.deleteBtn:hover {
  color: red;
  font-weight: bold;
}
.buttonbundle {
  flex-direction: row;
  margin: 20px;
  flex-basis: 150px;
  flex-shrink: 0;
  width: 100%;
  text-align: center;
  height: 100%;
  display: flex;
  align-items: flex-end;
}
.container {
  display: flex;
  flex-direction: column;
  margin-top: 5%;
  /* justify-content: center; */
  /* text-align: center; */
  width: 80%;
  height: 500px;
  max-height: 100%;
  background-color: white;
  /* margin: 10%; */
  border: 1px;
  border: solid;
  border-radius: 20px;
  border-color: rgb(207, 207, 207);
}
.inner-container {
  width: 50;
  padding-left: 15px;
  margin-top: 40px;
}
span {
  padding-left: 15px;
}
</style>
