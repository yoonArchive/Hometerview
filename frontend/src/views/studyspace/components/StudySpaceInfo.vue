<template>
  <button @click="leaveStudy(stdNo)"> 스터디 탈퇴 </button><br>
  <div class="d-flex justify-content-around">
    <div>
      대표 이미지 : {{ studySpaceDetail.stdImg }}<br>
    </div>

    <button> 스터디 입장하기 </button>

    <div class="information-box">
      <div class="box1">
        스터디 명 : {{ studySpaceDetail.stdName }} <br>
        기업 이름 : {{ studySpaceDetail.comName }}<br>
      </div>
      <div>
        타입 : {{ studySpaceDetail.stdType }}<br>
        기간 : {{ studySpaceDetail.startDate }} ~ {{ studySpaceDetail.endDate }}<br>
        진행 일자 : {{ studySpaceDetail.stdDay }}<br>
        모집 인원 : {{ studySpaceDetail.stdLimit }}<br>
        
      </div>
    </div>
  </div>
  <div>
    스터디 소개 : {{ studySpaceDetail.stdDetail }}<br>
  </div>

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
      stdNo: this.$route.params.stdNo,

    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail'])
  },
  methods:{
    ...mapActions(['bringStudySpaceDetail','leaveStudy'])
  },
  created(){
    this.bringStudySpaceDetail(this.stdNo)
    // await this.deleteStudySpace([this.stdNo])
  }
}
</script>

<style>
 .information-box{
    width: 440px;
    height: 300px;
    background: #FFFFFF;
    box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
 }
 .box1{
  width: 400px;
  height: 50px;
  background: #F3F4FF;
  border-radius: 10px;
 }

</style>
