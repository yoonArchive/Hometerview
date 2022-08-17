<template>
  <div class="container select-member">
    <!-- interviewee :{{ interviewUser }} <br /> -->
    <div class="d-flex justify-content-center">
      <form @submit.prevent="intervieweeApply()">
        <div>
          <select-interviewee-detail
            v-for="(member, index) in studySpaceDetail.studyJoins"
            :key="member.joinNo"
            :member="member"
            :studentindex="index"
          ></select-interviewee-detail>
        </div>
        <button>Apply</button>
      </form>
    </div>
    <button @click="intervieweeCancel()">인터뷰모드 해제</button>
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
</style>
