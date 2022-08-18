<template>
  <div class="container select-member">
    <!-- interviewee :{{ interviewUser }} <br /> -->
    <div>
      <div style="margin: 1.5vh; font-size: 2vh; font-weight: 500;">
        면접자를 지정해주세요
      </div>
      <div
        v-for="(member, index) in studySpaceDetail.studyJoins"
        :key="member.joinNo"
        class="member-list row"
      >
        <select-interviewee-detail
          :member="member"
          :studentindex="index"
        ></select-interviewee-detail>
      </div>
      <div class="buttons">
        <div class="d-flex justify-content-end">
          <button class="select-interviewee-btn" @click="intervieweeApply">
            인터뷰 지정
          </button>
          <button class="select-interviewee-btn" @click="intervieweeCancel()">
            인터뷰모드 해제
          </button>
        </div>
      </div>
    </div>
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
    ...mapGetters(["studySpaceDetail", "interviewUser"])
  },
  methods: {
    ...mapActions(["bringStudySpaceDetail"]),
    async intervieweeApply() {
      console.log("픽스된 인터뷰어 : ", this.interviewUser);
      this.$emit("streamUpdate", this.interviewUser);
    },
    async intervieweeCancel() {
      console.log("check");
      this.$emit("streamUpdate", "");
    }
  }

  // created(){
  //   this.bringStudySpaceDetail(this.sessionNo)
  // }
};
</script>

<style scoped>
.member-list {
  height: 70vh;
}
.select-member {
  height: 85vh;
}
.select-interviewee-btn {
  border: 1px solid #c4c1c9;
  background-color: #baaaea;
  margin-left: 0.5vh;
  color: #272930;
}
</style>
