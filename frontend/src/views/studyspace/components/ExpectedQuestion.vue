<template>
  <div v-if="IsHavingCoverLetter" class="expected-question-wrapper">
    <div class="row">
      <div class="col-8">
        <h4>예상 질문 리스트</h4>
      </div>
      <div class="col">
        <div class="d-flex flex-row-reverse bd-highlight">
          <button
            type="button"
            class="btn btn-secondary"
            @click="addquestion"
            style="background-color:#653FD3"
          >
            질문 추가
          </button>
        </div>
      </div>
    </div>
    <hr />

    <div v-if="IsAdding" class="question-wrapper">
      <div class="row">
        <div class="col-8">
          <textarea
            @keydown="autoResizeTextarea"
            @keyup="autoResizeTextarea"
            class="question-textarea"
            v-model="toaddtext"
          ></textarea>
        </div>
        <div class="col">
          <div class="d-flex flex-row-reverse bd-highlight">
            <button @click="confirmadding" class="question-button">확인</button>
            <button @click="canceladding" class="question-button">취소</button>
          </div>
        </div>
      </div>
    </div>

    <div>
      <div
        v-for="(item, index) in expectedQuestionList.personalQuestions"
        :key="index"
      >
        <div class="question-wrapper">
          <template v-if="changeIndex == index">
            <div class="row">
              <div class="col-8">
                <div class="d-flex justify-content-between">
                  <textarea
                    @keydown="autoResizeTextarea"
                    @keyup="autoResizeTextarea"
                    class="question-textarea"
                    v-model="item.contents"
                  ></textarea>
                </div>
              </div>
              <div class="col">
                <div class="row">
                  <div class="d-flex flex-row-reverse bd-highlight">
                    작성자 : {{ item.writerName }}
                  </div>
                </div>
                <div class="row question-button-wrapper">
                  <div class="d-flex flex-row-reverse bd-highlight">
                    <button
                      @click="confirmchange(item.questionNo, item.contents)"
                      class="question-button"
                    >
                      확인
                    </button>
                    <button @click="cancelchange" class="question-button">
                      취소
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </template>
          <template v-else>
            <div class="row">
              <div class="col-8">
                <div class="d-flex justify-content-between">
                  <div>{{ item.contents }}</div>
                </div>
              </div>
              <div class="col">
                <div class="row ">
                  <div class="d-flex flex-row-reverse bd-highlight">
                    작성자 : {{ item.writerName }}
                  </div>
                </div>
                <div class="row question-button-wrapper">
                  <div class="d-flex flex-row-reverse bd-highlight">
                    <button
                      class="question-button"
                      @click="changechangeindex(index)"
                    >
                      수정
                    </button>
                    <button
                      @click="deletequestion(item.questionNo)"
                      class="question-button"
                    >
                      삭제
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  props: {
    IsHavingCoverLetter: Boolean
  },
  data() {
    return {
      IsAdding: false,
      changeIndex: -1,
      toaddtext: ""
    };
  },
  methods: {
    ...mapActions([
      "getQuestionList",
      "addQuestionList",
      "changeQuestionList",
      "deleteQuestionList"
    ]),
    autoResizeTextarea(e) {
      let textarea = document.querySelector(".autoTextarea");
      console.log(e.srcElement);
      if (e.srcElement) {
        e.srcElement.style.height = "auto";
        let height = e.srcElement.scrollHeight; // 높이
        e.srcElement.style.height = `${height + 8}px`;
      }
    },
    canceladding() {
      this.IsAdding = false;
    },
    async confirmadding() {
      await this.addQuestionList(this.toaddtext);
      this.toaddtext = "";
      this.IsAdding = false;
    },
    changechangeindex(index) {
      this.changeIndex = index;
    },
    deletequestion(questionNo) {
      this.deleteQuestionList(questionNo);
    },
    addquestion() {
      this.IsAdding = true;
    },
    confirmchange(questionNo, contents) {
      console.log(questionNo);
      console.log(contents);
      const data = {
        questionNo: questionNo,
        contents: contents
      };
      this.changeQuestionList(data);
      this.changeIndex = -1;
    },
    cancelchange() {
      this.changeIndex = -1;
    }
  },
  async mounted() {
    await this.getQuestionList();
  },
  computed: {
    ...mapGetters(["expectedQuestionList"])
  }
};
</script>

<style>
.question-textarea {
  border: none;
  resize: none;
  width: 100%;
}
.question-button {
  border: none;
  color: #d9d9d9;
  background-color: white;
  padding: 3% 5% 3% 5%;
}
.question-button:hover {
  text-decoration: underline;
  color: black;
}
.question-button-wrapper {
}
.question-wrapper {
  border: 1px black solid;
  border-radius: 10px;
  background-color: white;
  padding: 3%;
  margin-bottom: 2%;
}
.expected-question-wrapper {
  margin-top: 10%;
}
</style>
