<template>

  <form @submit.prevent="intervieweeApply()">
    <select-interviewee-detail
      v-for="(member,index) in studySpaceDetail.studyJoins"
        :key="member.joinNo"
        :member="member"
        :studentindex="index"
    ></select-interviewee-detail>
    <button> Apply </button>
  </form>
</template>

<script>
import SelectIntervieweeDetail from './SelectIntervieweeDetail.vue'
import {mapGetters,mapActions} from 'vuex'

export default {
  name:'SelectInterviewee',
  components:{
    SelectIntervieweeDetail
  },
  data(){
    return{
      sessionNo: this.$route.params.sessionNo,
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail']),
  },
  methods:{
  ...mapActions(['bringStudySpaceDetail']),
  },
  intervieweeApply(){
    console.log('확인')
  },

  // created(){
  //   this.bringStudySpaceDetail(this.sessionNo)
  // }

}
</script>

<style>

</style>