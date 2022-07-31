<template>
  <form @submit.prevent="onSubmit" class="comment-list-form">

    <!-- <input class="comment-input" type="textarea"  v-model="content" required> -->
    <textarea class="comment-input" type="textarea"  v-model="content" required cols="30" rows="10"></textarea>
    <button class="comment-push">Comment</button>


    <div>
      <input type="radio" name="reviewType" value="JOB" v-model="questionType">
      <label for="questionType">JOB</label>
      <input  type="radio" name="reviewType" value="FAKE" v-model="questionType">
      <label for="questionType">NoJOB</label>
    </div>
  </form>

</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'CommentListForm',
  data() {
    return {

      content: '',
      questionType:''
    }
  },
  computed: {
    ...mapGetters(['commonQuestions']),
  },
  methods: {
    ...mapActions(['createcommonQuestion']),
    onSubmit() {
      this.createcommonQuestion({ stdNo: this.$route.params.stdNo, content: this.content, questionType: this.questionType})
      this.content = ''
    }
  }
}
</script>

<style scoped>
.comment-list-form {
  border: 2px solid white;
  margin-top : 40px;
  margin-bottom : 40px;
  padding: 5px;
  display : flex;
  flex-direction: row;
  justify-content: flex-start;
  align-content: flex-start;

}
.comment-input{
  width : 100%;
  height : 5rem;
  word-break:break-all;
  resize : none;
}
.comment-push{
  display: block;

}
</style>
