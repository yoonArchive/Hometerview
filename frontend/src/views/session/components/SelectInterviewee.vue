<template>
  {{ interviewUserFixed }}
  <div class="container select-member">
    <div class="d-flex justify-content-center">
      <form @submit.prevent="intervieweeApply()">
        <div>
          <select-interviewee-detail
            v-for="(member, index) in studySpaceDetail.studyJoins"
            :key="member.joinNo"
            :member="member"
            :studentindex="index"
          ></select-interviewee-detail>
          <div class="buttons">
            <div>
              <button>면접자 초기화</button>
            </div>
            <div>
              <button>Apply</button>
            </div>
          </div>
        </div>
      </form>
    </div>
    <!-- <button @click="intervieweeCancel()"> 면접자 없애기 </button> -->
  </div>
</template>

<script>
import SelectIntervieweeDetail from "./SelectIntervieweeDetail.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "SelectInterviewee",
  props: {
    subscribers: Object,
    publisher: Object
  },
  components: {
    SelectIntervieweeDetail
  },
  data() {
    return {
      sessionNo: this.$route.params.sessionNo
    };
  },
  computed: {
    ...mapGetters(["studySpaceDetail", "interviewUser", "interviewUserFixed"])
  },
  methods: {
    ...mapActions(["bringStudySpaceDetail", "changeInterviewUserFixed"]),
    async intervieweeApply() {
      console.log("확인");
      await this.changeInterviewUserFixed(this.interviewUser);
      // emit을 보내서 update함수를 실행시켜야함 ==> 값은 아이디 값
      // 바로 실행을 시키는 것이 아니라 for문을 돌려서 유효성 검사 진행후
      this.$emit("streamUpdate", this.interviewUserFixed);

      // this.updateMainVideoStreamManager(this.interviewUserFixed)
    },
    intervieweeCancel() {
      console.log("check");
      this.changeInterviewUserFixed("");
    }
  }

  // created(){
  //   this.bringStudySpaceDetail(this.sessionNo)
  // }
};
</script>

<style scoped>
.select-member {
  height: 85vh;
}
</style>
