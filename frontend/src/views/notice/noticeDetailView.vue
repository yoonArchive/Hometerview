<template>
  <div class="container">
    <div class="inner-container">
      <p id="title">{{ notice.noticeTitle }}</p>
      <p>작성자 : 관리자</p>
      <p>작성시간 : {{ notice.writeDate }}</p>

      <hr />
    </div>
    <h3>{{ notice.noticeContents }}</h3>
    <!-- <div class="component1" v-if="isAuthor"> -->
    <div class="buttonbundle">
      <button id="button-review">
        <router-link :to="{ name: 'noticeEdit', params: { noticeNo } }">
          <p id="a">수정하기</p>
        </router-link>
      </button>
      <button id="button-review" @click="deleteNotice(noticeNo)">
        <p id="a">삭제하기</p>
      </button>
      <button id="button-review">
        <router-link class="routerlink" :to="{ name: 'notices' }">
          <p id="a">목록으로</p>
        </router-link>
      </button>
    </div>
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
    ...mapGetters(["isAuthor", "notice"])
  },
  methods: {
    ...mapActions(["fetchNotice", "deleteNotice"]),

    onIconAnimationEnds() {
      this.animating = false;
    }
  },
  created() {
    this.fetchNotice(this.noticeNo);
  }
};
</script>

<style scoped>
#title {
  font-size: 50px;
  text-align: left;
}

#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin-top: 1px;
}
#button-review {
  background-color: #653fd3;
  border-color: #653fd3;
  border-radius: 10%;
  margin: 15px;
  width: 100px;
}
.buttonbundle {
  /* display: flex; */
  /* justify-content: flex-start; */
  flex-direction: row;
  margin: 20px;
  flex-basis: 150px;
  flex-shrink: 0;
  width: 100%;
  text-align: center;
  height: 100%;
  display: flex;
  align-items: flex-end;
  /* justify-content: space-evenly; */
  /* justify-content: space-between; */
}

.container {
  display: flex;
  flex-direction: column;
  margin-top: 5%;
  justify-content: center;
  /* text-align: center; */
  width: 90%;
  height: 500px;
  max-height: 100%;
  background-color: white;
  margin: 10%;
  border: 1px;
  border: solid;
}

.inner-container {
  width: 50;
}
</style>
