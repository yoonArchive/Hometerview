<template>
  <div class="container">
    <div class="commonQ">
      <common-question-item
        v-for="commonquestion in this.commonquestions"
        :commonquestion="commonquestion"
        :questionNo="commonquestion.questionNo"
        :stdNo="stdNo"
        :key="commonquestion.questionNo"
      >
      </common-question-item>
    </div>
    <div class="comments-from">
      <common-question-form></common-question-form>
    </div>
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
  created() {
    this.commonQuestions1(this.stdNo);
    console.log(this.commonQuestions);
  },
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
<style>
.commonQ {
  padding: 90px;
  height: 800px;
  overflow-y: auto;
}
.commonQ::-webkit-scrollbar {
  width: 10px;
}
.commonQ::-webkit-scrollbar-thumb {
  background-color: #653fd3;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.commonQ::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.container {
  display: flex;
}
.comments-from {
  padding: 90px;
  width: 80%;
}
</style>
