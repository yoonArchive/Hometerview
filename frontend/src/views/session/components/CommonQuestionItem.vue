<template>
  <div
    class="bubble-empty-box border-blue"
    style="margin-top: 20px"
    @click="playttsquestion(question.contents)"
  >
    <template v-if="question.questionType == 'JOB'" style="color:#653fd3"
      >[직무]</template
    >
    <template v-else style="color:red">[인성]</template>
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
    questionIndex: Number,
    myJoinType: String
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
      if (this.myJoinType != "LEADER") {
        alert("팀장만 TTS 기능을 사용가능합니다.");
      } else if (this.isTTSMode) {
        this.changettsrequest(data);
      } else {
        alert("TTS 모드를 켜주세요!");
      }
    }
  }
};
</script>

<style scoped lang="scss">
.bubble-empty-box {
  position: relative;

  border: 2px solid #f90;
  border-radius: 5px;
  width: 100%;
  height: auto;
  line-height: 46px;
  text-align: center;
  font-size: 20px;
  color: #f90;
  &::before {
    position: absolute;
    left: 0%;
    top: 50%;
    margin: -5px 0 0 -12px;
    border: 5px solid transparent;
    border-right-color: #f90;
    content: "";
  }
  &::after {
    position: absolute;
    left: 0%;
    top: 50%;
    margin-top: -4px;
    margin-left: -8px;
    border: 4px solid transparent;
    border-right-color: #fff;
    content: "";
  }
}

.border-blue {
  border-color: #653fd3;
  color: #653fd3;
  &::before {
    border-right-color: #653fd3;
  }
}
.box {
  width: 80%;
  margin: 20px auto;
  background: #f0ebff;
  border: #653fd3 1.5px solid;
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
