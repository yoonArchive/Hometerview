<template>
  <div v-if="IsHavingCoverLetter">
    <div class="row">
      <div class="col-8">
        <h2>예상 질문 리스트</h2>
      </div>
      <div class="col">
        <button type="button" class="btn btn-secondary">질문 추가</button>
      </div>
    </div>
    <hr />
    <div class="question-wrapper">
      <div v-if="IsAdding">
        <textarea> </textarea>
      </div>
      <div v-if="!IsChanging">
        <div
          v-for="(item, index) in expectedQuestionList.personalQuestions"
          :key="index"
        >
          <div class="d-flex bd-highlight mb-3" :class="`index--${index}`">
            <div class="me-auto p-2 bd-highlight">{{ item.contents }}</div>
            <div class="p-2 bd-highlight"><button>수정</button></div>
            <div class="p-2 bd-highlight"><button>삭제</button></div>
          </div>
        </div>
      </div>
      <div v-else>
        <div
          v-for="(item, index) in expectedQuestionList.personalQuestions"
          :key="index"
        >
          <div class="d-flex bd-highlight mb-3" v-if="index !=">
            <div class="me-auto p-2 bd-highlight">{{ item.contents }}</div>
            <div class="p-2 bd-highlight"><button>수정</button></div>
            <div class="p-2 bd-highlight"><button>삭제</button></div>
          </div>
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
      IsAdding: false
    };
  },
  methods: {
    ...mapActions(["getQuestionList"])
  },
  mounted() {
    this.getQuestionList();
  },
  computed: {
    ...mapGetters(["expectedQuestionList"])
  }
};
</script>

<style></style>
