<template>
  <div class="container" style="margin-top:1vh;">
    <div>
      <form @submit.prevent="onSubmit" class="comment-list-form">
        <!-- <input class="comment-input" type="textarea"  v-model="content" required> -->
        <div id="check" style="margin-bottom: 0.6vh;">
          <input
            class="form-check-input"
            type="radio"
            name="reviewType"
            value="JOB"
            v-model="questionType"
          />
          <label for="questionType">직무 면접</label>
          &nbsp;&nbsp;
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
            placeholder="공유할 질문을 작성해 보세요"
          />
          <button class="bubbly-button">작성</button>
        </div>
      </form>
    </div>
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

  width: 100%;
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
.form-control {
  border-radius: 0.6vh;
}
.bubbly-button {
  width: 100px;
  border-radius: 0.6vh;
  background-color: #f6f2ff;
  color: #383561;
  box-shadow: 0px 0px 7px 1px rgb(0 0 0 / 20%);
  border: 0.3vh solid #9c7bfd;
}
.container {
  display: flex;
  justify-content: center;
}
.form-check-input:checked {
  background-color: #6a46d1;
  border-color: #6a46d1;
}
</style>
