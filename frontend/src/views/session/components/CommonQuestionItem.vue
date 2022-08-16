<template> 질문 {{ questionIndex }} : {{ question.contents }} <br /> </template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "CommonQuestionItem",
  components: {},

  props: {
    question: Object,
    questionIndex: Number
  },
  data() {
    return {};
  },
  computed: {
    ...mapGetters([])
  },
  methods: {
    ...mapActions([])
  }
};
</script>

<style scoped></style>
