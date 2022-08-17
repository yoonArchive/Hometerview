<template>
  <div class="container select-member">
    <!-- interviewee :{{ interviewUser }} <br /> -->

    <div>
      <div
        v-for="(member, index) in studySpaceDetail.studyJoins"
        :key="member.joinNo"
        class="row"
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
.select-member {
  height: 85vh;
}
.select-interviewee-btn {
  border: 1px solid #653fd3;
  background-color: #f0ebff;
  margin-left: 3px;
}
</style>
