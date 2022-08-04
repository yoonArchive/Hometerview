<template>

  <div>
    <div class="d-flex-content-between">
      <div>
        이미지 : {{ studySpace.stdImg }}<br>
      </div>
      <div>
        스터디 이름 : {{ studySpace.stdName }}<br>
        타입 : {{ studySpace.stdType }}<br>
        기업 : {{ studySpace.comName }}<br>
        기간 : {{ studySpace.startDate }} ~ {{ studySpace.endDate }}<br>
        진행 일자 :{{ studySpace.stdDay }}<br>
        인원제한 : {{ studySpace.stdLimit }}<br>
      </div><hr>

    </div>
    <div>
      스터디 소개 : {{ studySpace.stdDetail }}<br>
      <!-- Study Leader : {{ studySpace.studyJoins[0].user.userName }} <br> -->
    </div>
    <div>
      공지사항 : {{ studySpace.stdNotice }} <br>
    </div>

    <button @click="moveToStudyDetail"> 스터디 가기 </button>
    <hr>



  </div>

</template>

<script>
import router from '@/common/lib/vue-router'

export default {
  name : "StudySpaceItem",
  props:{
    studySpace : Object
  },

  data(){
    return{
        studyJoins : this.studySpace.studyJoins
      }
    },
  computed: {
    // countStudyJoins(){
    //     const count = this.studyJoins.length
    //     return count
    // }

  },
  methods: {
    moveToStudyDetail(){
      router.push({
        name:"studydetail",
        params: {stdNo:this.studySpace.stdNo}
      })
    }
  },



}

</script>

<style>

</style>
