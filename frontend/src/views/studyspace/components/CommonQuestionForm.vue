<template>
  <form @submit.prevent="onSubmit" class="comment-list-form">
    <!-- <input class="comment-input" type="textarea"  v-model="content" required> -->
    <textarea
      class="comment-input"
      type="textarea"
      v-model="content"
      required
      cols="30"
      rows="10"
    ></textarea>
    <button class="comment-push">Comment</button>

    <div>
      <input
        type="radio"
        name="reviewType"
        value="JOB"
        v-model="questionType"
      />
      <label for="questionType">기업면접</label>
      <input
        type="radio"
        name="reviewType"
        value="FREE"
        v-model="questionType"
      />
      <label for="questionType">자율면접</label>
    </div>
  </form>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "CommentListForm",
  data() {
    return {
      content: "",
      questionType: ""
    };
  },
  computed: {
    ...mapGetters(["commonQuestions"])
  },
  methods: {
    ...mapActions(["createcommonQuestion"]),
    onSubmit() {
      if (this.questionType === "") {
        alert("질문타입을 설정해주세요");
      } else {
        this.createcommonQuestion({
          stdNo: this.$route.params.stdNo,
          content: this.content,
          questionType: this.questionType
        });
        this.content = "";
        console.log(this.commonQuestions);
      }
    }
  }
};
</script>

<style scoped>
.comment-list-form {
  border: 2px solid white;
  margin-top: 40px;
  margin-bottom: 40px;
  padding: 5px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-content: flex-start;
}
.comment-input {
  width: 100%;
  height: 5rem;
  word-break: break-all;
  resize: none;
}
.comment-push {
  display: block;
}
</style>
