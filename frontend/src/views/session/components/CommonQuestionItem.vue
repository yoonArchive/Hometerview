<template>
  <div class="box sb2" @click="playttsquestion(question.contents)">
    {{ question.contents }}
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "CommonQuestionItem",
  components: {},

  props: {
    question: Object,
    questionIndex: Number
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["isTTSMode"])
  },
  methods: {
    ...mapActions(["changettsrequest"]),
    playttsquestion(data) {
      if (this.isTTSMode) {
        this.changettsrequest(data);
      } else {
        alert("TTS 모드를 켜주세요!");
      }
    }
  }
};
</script>

<style scoped>
.box {
  width: 80%;
  margin: 20px auto;
  background: #653fd3;
  padding: 20px;
  text-align: center;
  font-weight: 900;
  color: #fff;
  font-family: arial;
  position: relative;
  border-radius: 10px;
}
.sb2:before {
  content: "";
  width: 0px;
  height: 0px;
  position: absolute;
  border-left: 10px solid transparent;
  border-right: 10px solid #653fd3;
  border-top: 10px solid #653fd3;
  border-bottom: 10px solid transparent;
  left: -19px;
  top: 6px;
}
</style>
