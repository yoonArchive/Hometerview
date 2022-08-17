<template>
  <div class="container">
    <form @submit.prevent="onSubmit" class="comment-list-form">
      <!-- <input class="comment-input" type="textarea"  v-model="content" required> -->
      <div id="check">
        <input
          class="form-check-input"
          type="radio"
          name="reviewType"
          value="JOB"
          v-model="questionType"
        />
        <label for="questionType">직무 면접</label>
        <br />
        <input
          class="form-check-input"
          type="radio"
          name="reviewType"
          value="FREE"
          v-model="questionType"
        />
        <label for="questionType">인성 면접</label>
      </div>
      <div class="form-bottom">
        <input
          class="form-control"
          type="text"
          v-model="content"
          required
          cols="30"
          rows="10"
          placeholder="공유할 질문을 작성해 보세요"
        />
        <button class="bubbly-button">작성</button>
      </div>
    </form>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  name: "CommentListForm",
  data() {
    return {
      content: "",
      questionType: "",
      date: ""
    };
  },

  computed: {
    ...mapGetters(["commonQuestions", "studentIndex", "studySpaceDetail"])
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
  },
  mounted() {
    console.log(this.studentIndex);
  }
};
</script>
<style lang="scss" scoped></style>
<style scoped>
.comment-list-form {
  border: 2px solid white;

  width: 800px;
}
.comment-input {
  width: 100%;
  height: 5rem;
  word-break: break-all;
  resize: none;
}
.comment-push {
  /* display: block; */
  width: 200px;
}
#check {
  display: flex;
}
.form-bottom {
  display: flex;
  flex-direction: row;
}
.bubbly-button {
  width: 100px;
  border-radius: 10px;
}
.container {
}
</style>
