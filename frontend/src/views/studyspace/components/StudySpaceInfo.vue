<template>
  <button @click="leaveStudy(stdNo)"> 스터디 탈퇴 </button><br>
  대표 이미지 : {{ studySpaceDetail.stdImg }}<br>
  스터디 이름 : {{ studySpaceDetail.stdName }} <br>
  스터디 소개 : {{ studySpaceDetail.stdDetail }}<br>
  타입 : {{ studySpaceDetail.stdType }}<br>
  기업 이름 : {{ studySpaceDetail.comName }}<br>
  기간 : {{ studySpaceDetail.startDate }} ~ {{ studySpaceDetail.endDate }}<br>
  진행 일자 : {{ studySpaceDetail.stdDay }}<br>
  모집 인원 : {{ studySpaceDetail.stdLimit }}<br>


  <div>
    <study-members
      v-for="(member,index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
    ></study-members>
  </div>

</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import StudyMembers from './StudyMembers.vue'
export default {
  name:'StudySpaceInfo',

  components:{
    StudyMembers,
  },
  data(){
    return{
      stdNo:this.$route.params.stdNo,
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail'])
  },
  methods:{
    ...mapActions(['bringStudySpaceDetial','leaveStudy'])
  },
  mounted(){
    this.bringStudySpaceDetial(this.stdNo)
  }
}
</script>

<style>

</style>
