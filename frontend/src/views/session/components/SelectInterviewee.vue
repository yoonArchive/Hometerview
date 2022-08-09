<template>
 {{ studySpaceDetail }}
  <select-interveiwee-detail
    v-for="(member,index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
  ></select-interveiwee-detail>
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
  // ...mapActions(['bringStudySpaceDetail']),
  },
  // created(){
  //   this.bringStudySpaceDetail(this.sessionNo)
  // }

}
</script>

<style>

</style>