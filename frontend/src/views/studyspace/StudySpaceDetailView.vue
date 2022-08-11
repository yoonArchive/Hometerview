<template>
  <div class="container">
    <study-space-info></study-space-info>
    <member-common-questions
      :commonquestions="commonQuestions.commonQuestions"
    ></member-common-questions>
  </div>
</template>

<script>
import StudySpaceInfo from "./components/StudySpaceInfo.vue";

import MemberCommonQuestions from "./components/CommonQuestionList.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "StudySpaceDetailView",
  data() {
    return {
      stdNo: this.$route.params.stdNo
    };
  },

  components: {
    StudySpaceInfo,
    MemberCommonQuestions
  },
  computed: {
    ...mapGetters(["commonQuestion", "commonQuestions"])
  },
  methods: {
    ...mapActions(["getStd", "commonQuestions1", "setStudyNoAction"])
  },
  created() {
    //this.commonQuestions1(this.stdNo);
    console.log(this.stdNo);
  },
  mounted() {
    this.setStudyNoAction(this.stdNo);
  }
};
</script>

<style></style>
