<template>
  <div class="container">
    <hr />
    <div>
      <p>번호 : {{ currentReview.reviewNo }}</p>
      <p>제목 : {{ currentReview.reviewTitle }}</p>
      <p>내용 : {{ currentReview.reviewContents }}</p>
      <p>날짜 : {{ currentReview.reviewDate }}</p>
      <p v-if="currentReview.reviewType === 'FAKE'">유형 : 모의</p>
      <p v-else>유형 : 실제</p>

      <div class="buttonbundle">
        <button id="button-review">
          <router-link
            class="routerlink"
            :to="{ name: 'reviewEdit', params: { reviewNo: reviewNo } }"
          >
            <p id="a">수정</p>
          </router-link>
        </button>
        <button @click="deleteReview(reviewNo)" id="button-review">
          <p id="a">삭제</p>
        </button>
        <button id="button-review">
          <router-link class="routerlink" :to="{ name: 'diary' }">
            <p id="a">목록</p>
          </router-link>
        </button>
      </div>
      <hr />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
// import CoverLetterDetail from './components/ReviewDetail.vue'
import ReviewDetail from "./components/ReviewForm.vue";
export default {
  data() {
    return {
      reviewNo: this.$route.params.reviewNo,
      headers: ["번호", "제목", "내용", "날짜", "유형"]
    };
  },

  components: {
    ReviewDetail
  },

  computed: {
    ...mapGetters(["reviewContents", "currentReview"])
  },
  methods: {
    ...mapActions(["getReviewInfo", "getReviewDetail", "deleteReview"]),
    // findreviews(){
    //   this.getreviewInfo(this.reviewNo);

    // },
    findreviewDetail() {
      this.getReviewDetail(this.reviewNo);
    }
  },
  mounted() {
    this.findreviewDetail();
    // console.log(this.reviewNo);
    window.scrollTo(0, 0);
  }
};
</script>

<style scoped>
#a {
  text-decoration-line: none;
  color: white;
  font-size: 15px;
  font-family: "티머니 둥근바람";
  margin: 2px;
}
#button-review {
  background-color: #653fd3;
  border-color: #653fd3;
  border-radius: 10%;
  margin: 15px;
  width: 100px;
  text-align: center;
}
.buttonbundle {
  flex-direction: row;
  margin: 10px;
  flex-basis: 150px;
  flex-shrink: 0;
  width: 100%;
  padding: 10px;
}
.container {
  display: flex;

  flex-direction: column;
  justify-content: center;

  width: 100%;
  height: 100%;
}
</style>
