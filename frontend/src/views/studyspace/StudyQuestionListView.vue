<template>
  <div>


    {{commonQuestions.commonQuestions}}
    <div v-for="(commonquesions, index) in commonQuestions.commonQuestions" :key="index">
      {{ commonquesions.contents}}
    </div>
      <CommonQuestionList :commonquestions="commonQuestions.commonQuestions"></CommonQuestionList>
  </div>

</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import CommonQuestionList from './components/CommonQuestionList.vue';

export default {
  name: 'StudyQuestionsListView',
  components: {CommonQuestionList},
  data(){
    return {
      roomName : '',
      //여기서 데이터를 넘겨줌
      stdNo: this.$route.params.stdNo,
    }
  },
  computed:{
    ...mapGetters(['commonQuestion','commonQuestions']),
  },
  methods:{
    ...mapActions(['getStd','commonQuestions1']),

  },
  async mounted(){
    await this.commonQuestions1(this.stdNo)
  },
  created(){
    // this.getStd(this.stdNo);
    this.commonQuestions1(this.stdNo);
    console.log(this.stdNo)
  }
}
</script>

<style scoped>

</style>
