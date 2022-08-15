<template>
  fixed User :{{ interviewUserFixed }}
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
      // await this.changeInterviewUserFixed(this.interviewUser);
      console.log("픽스된 인터뷰어 : ", this.interviewUser);
      this.$emit("streamUpdate", this.interviewUser);
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
