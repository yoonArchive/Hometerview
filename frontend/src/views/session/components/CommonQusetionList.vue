<template>
  <div class="container">
    <div style="margin: 1.5vh; font-size: 2vh; font-weight: 500;">
      TTS 모드를 켜고 말풍선을 눌러주세요.
    </div>
    <button
      v-if="myJoinType === 'LEADER'"
      class="TTS-mode-btn"
      @click="changeTTSModevue"
    >
      <!-- v-if="myJoinType === 'LEADER'" -->
      <template v-if="!isTTSMode">
        TTS 모드 켜기
      </template>
      <template v-else>
        TTS 모드 끄기
      </template>
    </button>

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
  props: {
    myJoinType: String
  },
  components: {
    CommonQuestionItem
  },
  data() {
    return {
      sessionNo: this.$route.params.sessionNo
    };
  },
  computed: {
    ...mapGetters(["commonQuestions", "isTTSMode"])
  },
  methods: {
    ...mapActions(["getStd", "changeTTSMode"]),
    async changeTTSModevue() {
      await this.changeTTSMode();
      await this.$emit("sendTTS", this.isTTSMode);
    }
  },
  created() {
    this.getStd;
  }
};
</script>

<style scoped>
.TTS-mode-btn {
  border: 1px #653fd3 solid;
  background-color: #f0ebff;
  border-radius: 5px;
}
.container {
  background: #ffff;
  overflow: scroll;
  border-radius: 10px 10px 10px 10px;
  height: 90vh;
}
</style>
