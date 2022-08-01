<template>
  <div class="container">
    <common-question-item v-for="commonquestion in commonquestions"
     :commonquestion="commonquestion" :key="commonquestion.questionNo">
    </common-question-item>
    <common-question-form></common-question-form>



  </div>
</template>
<script>

import CommonQuestionForm from './CommonQuestionForm.vue'
import CommonQuestionItem from './CommonQuestionItem.vue'


export default {
  name: 'CommonQuestionList',
  props:{
    commonquestions: Array
  },
  components: { CommonQuestionForm, CommonQuestionItem},
  data() {
    return {
      stdNo: this.$route.params.stdNo
    }
  },
  setup() {
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}

</script>
