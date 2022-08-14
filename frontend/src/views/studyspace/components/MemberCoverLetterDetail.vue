<template>
  <div class="member-cover-letter-detail-wrapper">
    <div v-if="resumeQuestionList.length != 0">
      <div class="d-flex flex-row-reverse">
        <input
          class="cover-letter-button "
          :class="{ clicked: item == resumeQuestionList.length - 1 }"
          type="button"
          v-for="(item, index) in resumeQuestionList.length"
          :key="index"
          :value="resumeQuestionList.length - item + 1"
          @click="
            changeSelectedNum(resumeQuestionList.length - item + 1, $event)
          "
        />
      </div>
      <div class="member-cover-letter-contents">
        <div class="cover-letter-question">
          {{ resumeQuestionList[selectedQuestionNum].question }}
        </div>
        <hr style="border-top: 3px dashed #663399;" />
        <div class="cover-letter-answer">
          {{ resumeQuestionList[selectedQuestionNum].answer }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
export default {
  props: {
    studentindex: Number
  },
  computed: {
    ...mapGetters(["resumeQuestionList", "selectedQuestionNum", "selStdNo"])
  },

  methods: {
    ...mapActions(["getQuestionList"]),
    ...mapMutations(["SET_SELECTED_QUESTION_NUM"]),
    changeSelectedNum(item, event) {
      this.SET_SELECTED_QUESTION_NUM(item - 1);
      this.getQuestionList();
    },
    settingvalue() {
      var buttons = document.getElementsByClassName("cover-letter-button");
      for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("clicked");
      }
      if (buttons.length != 0) {
        buttons[
          this.resumeQuestionList.length - this.selectedQuestionNum - 1
        ].classList.add("clicked");
      }
    }
  },
  watch: {
    selectedQuestionNum() {
      console.log("선택된 질문 : " + this.selectedQuestionNum);
      var buttons = document.getElementsByClassName("cover-letter-button");
      console.log(buttons);
      for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("clicked");
      }
      buttons[
        this.resumeQuestionList.length - this.selectedQuestionNum - 1
      ].classList.add("clicked");
    }
    // resumeQuestionList() {
    //   var buttons = document.getElementsByClassName("cover-letter-button");
    //   for (var i = 0; i < buttons.length; i++) {
    //     buttons[i].classList.remove("clicked");
    //   }
    //   console.log(this.selectedQuestionNum);
    //   console.log(buttons[this.selectedQuestionNum]);
    //   buttons[this.selectedQuestionNum].classList.add("clicked");
    // }
  },
  created() {
    this.SET_SELECTED_QUESTION_NUM(0);
  },
  mounted() {
    this.SET_SELECTED_QUESTION_NUM(0);
    this.settingvalue();
  },
  unmounted() {
    this.SET_SELECTED_QUESTION_NUM(0);
  }
};
</script>

<style scoped>
.clicked {
  color: white;
  background-color: #653fd3;
}
.cover-letter-button {
  width: 30px;
  height: 45px;
  border: none;
  filter: drop-shadow(2px 0px 4px rgba(0, 0, 0, 0.25));
  margin-right: 1%;
}
.member-cover-letter-detail-wrapper {
  margin-top: 10%;
}
.member-cover-letter-contents {
  border: 1.5px solid black;
  box-shadow: 0px -1px 4px rgba(0, 0, 0, 0.25);
  padding: 5%;
  background-color: white;
}
.cover-letter-question {
  font-size: 15px;
}
.cover-letter-answer {
  font-size: 15px;
  word-break: break-all;
}
</style>
