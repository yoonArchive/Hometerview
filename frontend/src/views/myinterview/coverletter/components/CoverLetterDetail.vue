<template>
  <div>
    <div class="member-cover-letter-detail-wrapper">
      <div>
        <div class="d-flex flex-row-reverse">
          <template v-if="currentResume.length != 0">
            <input
              class="cover-letter-button"
              :class="{ clicked: currentResume.length - item + 1 == 1 }"
              type="button"
              v-for="(item, index) in currentResume.length"
              :key="index"
              :value="currentResume.length - item + 1"
              @click="
                returnnumbermethod(currentResume.length - item + 1, $event)
              "
            />
          </template>
          <input
            class="cover-letter-button"
            type="button"
            @click="changeIsremove"
            value="-"
          />
          <input
            class="cover-letter-button"
            type="button"
            @click="addCurrentResume"
            value="+"
          />
        </div>
        <div
          class="member-cover-letter-contents"
          v-if="currentResume.length != 0"
        >
          <div class="cover-letter-question">
            <textarea
              class="cover-letter-question-textarea"
              v-model="currentResume[selectedNum].question"
              id="cover-write"
            >
            </textarea>
          </div>
          <hr
            style="border-top: 3px dashed #663399; margin:0; margin-top:1rem 0; margin-block-start:0"
          />
          <div class="cover-letter-answer">
            <textarea
              name=""
              id="cover-write"
              class="cover-letter-answer-textarea"
              v-model="currentResume[selectedNum].answer"
            ></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="d-flex flex-row-reverse bd-highlight">
      <button
        @click="saveResumeChange"
        id="b-button"
        type="button"
        class="btn"
        style="background-color:#653FD3; color: white;"
      >
        저장하기
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      selectedNum: 0,
      isremove: false
    };
  },
  mounted() {
    console.log(this.currentResume.length);
    this.setting();
  },
  props: {
    resumeindex: Number
  },
  computed: {
    ...mapGetters(["currentResume", "numberOfQuestion", "isLodding"]),
    length() {
      return this.currentResume.length;
    }
  },
  watch: {
    selectedNum() {
      console.log("선택된 질문 : " + this.selectedNum);
      var buttons = document.getElementsByClassName("cover-letter-button");
      for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("clicked");
      }
      console.log(this.selectedNum);
      buttons[this.currentResume.length - this.selectedNum - 1].classList.add(
        "clicked"
      );
    }
    // currentResume() {
    //   console.log("선택된 질문 : " + this.selectedNum);
    //   var buttons = document.getElementsByClassName("cover-letter-button");
    //   for (var i = 0; i < buttons.length; i++) {
    //     buttons[i].classList.remove("clicked");
    //   }
    //   console.log(this.selectedNum);
    //   buttons[this.currentResume.length - this.selectedNum - 1].classList.add(
    //     "clicked"
    //   );
    // }
  },
  methods: {
    ...mapActions([
      "saveResumeChange",
      "addItemCurrentResume",
      "removeItemCueentResume"
    ]),
    addCurrentResume() {
      this.addItemCurrentResume();
      this.selectedNum = 0;
    },
    changeIsremove() {
      this.isremove = !this.isremove;
    },
    returnnumbermethod(item, event) {
      if (this.isremove) {
        this.removeResumeQuestion(item);
        console.log("지우기 모드");
      } else {
        this.changeSelectedNum(item, event);
        console.log("선택 모드");
      }
    },
    async removeResumeQuestion(item) {
      this.selectedNum = 0;
      await this.saveResumeChange();
      console.log("지우기 시작");
      await this.removeItemCueentResume(item - 1);
    },
    setting() {
      this.selectedNum = 0;
      var buttons = document.getElementsByClassName("cover-letter-button");
      for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("clicked");
      }
      if (buttons.length > 2) {
        buttons[this.currentResume.length - this.selectedNum - 1].classList.add(
          "clicked"
        );
      }
    },
    changeSelectedNum(item) {
      this.selectedNum = item - 1;
      var buttons = document.getElementsByClassName("cover-letter-button");
      for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove("clicked");
      }
      buttons[this.currentResume.length - this.selectedNum - 1].classList.add(
        "clicked"
      );
    }
  }
};
</script>

<style scoped>
/* input[type=text]:focus {
  border: 3px solid #653FD3;
} */
.cover-letter-answer-textarea {
  width: 100%;
  height: 350px;
  font-size: 20px;
  padding: 2%;
  border: none;
  resize: none;
}
.cover-letter-question-textarea {
  width: 100%;
  height: 130px;
  font-size: 20px;
  padding: 2%;
  border: none;
  resize: none;
}
#cover-write:focus {
  border: solid 3px #856ccf;
  outline: solid 1px #9376e9;
}
.cover-inner {
  display: flex;
  flex-direction: column;
  margin-top: 40px;
  /* justify-content: space-between; */
}
.plus-buttons {
  display: flex;
  flex-direction: row;
  margin-bottom: 30px;
  /* align-items: right; */
}
.plus-container {
  margin-top: 50px;
  display: flex;
  flex-direction: column;
}
#cover-Pbutton {
  border-radius: 40%;
  width: 50px;
  height: 40px;
  background-color: #5cb85c;
  border: 0;
  outline: 0;
}
#cover-Pbutton:hover {
  background-color: #6ed36e;
}
#cover-Pbutton:active {
  background-color: #367c36;
}
#cover-Mbutton {
  border-radius: 40%;
  width: 50px;
  height: 40px;
  color: white;
  background-color: #e62626;
  border: 0;
  outline: 0;
}

#cover-Mbutton:hover {
  background-color: #ec6f6f;
}
#cover-Mbutton:active {
  background-color: #e42f2f;
}
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
  margin-bottom: 3%;
}
.member-cover-letter-contents {
  border: 1.5px solid black;
  box-shadow: 0px -1px 4px rgba(0, 0, 0, 0.25);
  padding: 0%;
  background-color: white;
}
.cover-letter-question {
  font-size: 15px;
}
.cover-letter-answer {
  font-size: 15px;
}
</style>
