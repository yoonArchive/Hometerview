<template>
 <h1>Detail Page</h1>
    <div>
      대표 이미지 : 
      <img src="{{ recruitDetail.stdImg }}" alt=""><br>
    </div>
    스터디 이름 : {{ recruitDetail.stdName }} <br>
    모집인원 : {{ recruitDetail.stdLimit }} <br>
    활동기간 : {{ recruitDetail.startDate }} ~  {{ recruitDetail.endDate }}<br>
    모집 타이틀 : {{ recruitDetail.recruitTitle }} <br>
    스터디 타입 : {{ recruitDetail.stdType }} <br>
    기업명 : {{ recruitDetail.comName }} <br>
    진행 일자 : {{ recruitDetail.stdDay }} <br>
    스터디 현황 : {{ recruitDetail.recruitStatus }} <br>
    스터디 소개 : <br>
    {{ recruitDetail.stdDetail }} <br>
    <button @click="moveToUpdate">수정</button>
    <button @click="deleteRecruitmentDetail([recruitNo])"> 삭제 </button>
    <button @click="studyStart(recruitNo)"> 스터디 시작 </button>

</template>

<script>
  import router from '@/common/lib/vue-router'
  import { mapActions, mapGetters } from 'vuex'


  export default {
    name:'MembersRecruitmentDetailView',

    data(){
      return{
        recruitNo:this.$route.params.recruitNo,
        studyType : '',
      }
    },
    computed:{
      ...mapGetters(['recruitDetail'])
      
    },
    methods:{
      ...mapActions([
        'bringRecruitmentDetail',
        'updateRecruitmentDetail',
        'deleteRecruitmentDetail',
        'createStudySpace'
        ]),
      interviewType(){
        if(this.recruitDetail.stdType === 'COM'){
          this.studyType = '기업 면접'
        }else{
          this.studyType = '자율 면접'
        }
      },
      moveToUpdate(){
        router.push({
        name:'membersrecruitmentedit',
        params:{recruitNo:this.recruitNo}
        })
      },
      async studyStart(){
        await this.createStudySpace(this.recruitNo)
        router.push({ name:'study'})
      }


    },
    created(){
      this.interviewType()
      this.bringRecruitmentDetail(this.recruitNo)
    }

  }
</script>

<style>

</style>