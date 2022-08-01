<template>
  <h1>Detail Page</h1>
  <div>


    대표 이미지 : {{ studySpaceDetail.stdImg }}<br>
    스터디 이름 : {{ studySpaceDetail.stdName }} <br>
    스터디 소개 : {{ studySpaceDetail.stdDetail }}<br>
    타입 : {{ studySpaceDetail.stdType }}<br>
    기업 이름 : {{ studySpaceDetail.comName }}<br>
    기간 : {{ studySpaceDetail.startDate }} ~ {{ studySpaceDetail.endDate }}<br>
    진행 일자 : {{ studySpaceDetail.stdDay }}<br>
    모집 인원 : {{ studySpaceDetail.stdLimit }}<br>

    <!-- {{ studySpaceDetail.studyJoins }} -->
    <div>
      <study-members
        v-for="(member, index) in studySpaceDetail.studyJoins"
        :key="member.joinNo"
        :member="member"
        :studentindex=index
      ></study-members>
    </div>

  </div>
</template>

<script>
import router from '@/common/lib/vue-router'
import StudyMembers from './components/StudyMembers.vue'
import {mapActions, mapGetters} from 'vuex'

export default {
  name:"StudySpaceDetailView",
  components:{
    StudyMembers
  },

  data(){
    return{
      stdNo:this.$route.params.stdNo,
      // memberList: this.studySpaceDetail.studyJoins
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail'])
  },
  methods:{
    ...mapActions(['bringStudySpaceDetial'])
  },
  mounted(){
    this.bringStudySpaceDetial(this.stdNo)
  }


}
</script>

<style>

</style>
