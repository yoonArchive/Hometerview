<template>
  <div class="container">
    <common-question-item
      v-for="(question, index) in commonQuestions.commonQuestions"
      :key="index"
      :question="question"
      :questionIndex="index"
    ></common-question-item>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import CommonQuestionItem from "./CommonQuestionItem.vue";

export default {
  name: "StudyMemberList",
  components: {
    CommonQuestionItem
  },
  data() {
    return {
      sessionNo: this.$route.params.sessionNo
    };
  },
  computed: {
    ...mapGetters(["commonQuestions"])
  },
  methods: {
    ...mapActions(["getStd"])
  },
  created() {
    this.getStd;
  }
};
</script>

<style scoped>
.container {
  background: #ffff;
  overflow: scroll;
  border-radius: 10px 10px 10px 10px;
  height: 85vh;
}
</style>
