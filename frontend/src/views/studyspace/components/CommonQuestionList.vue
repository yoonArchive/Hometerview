<template>
  <div class="container">
    <common-question-item
      v-for="commonquestion in this.commonquestions"
      :commonquestion="commonquestion"
      :questionNo="commonquestion.questionNo"
      :stdNo="stdNo"
      :key="commonquestion.questionNo"
    >
    </common-question-item>

    <common-question-form></common-question-form>
  </div>
</template>
<script>
import CommonQuestionForm from "./CommonQuestionForm.vue";
import CommonQuestionItem from "./CommonQuestionItem.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "CommonQuestionList",
  props: {
    commonquestions: Array
  },
  components: { CommonQuestionForm, CommonQuestionItem },
  data() {
    return {
      stdNo: this.$route.params.stdNo
    };
  },
  setup() {},

  beforeUpdate() {
    // this.commonQuestions1(this.stdNo);
    // console.log("김" + this.stdNo);
  },
  methods: {
    ...mapActions(["getStd", "commonQuestions1"])
  },
  computed: {
    ...mapGetters(["commonQuestions"])
  }
};
</script>
