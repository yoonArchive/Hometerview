<template>

  <div>
    <!-- {{ studySpace }} -->
    {{ studySpace.stdName }}<br>
    {{ studySpace.stdDetail }}<br>
    {{ studySpace.stdImg }}<br>
    {{ studySpace.stdType }}<br>
    {{ studySpace.comName }}<br>
    {{ studySpace.startDate }}<br>
    {{ studySpace.endDate }}<br>
    {{ studySpace.stdDay }}<br>
    {{ countStudyJoins }} / {{ studySpace.stdLimit }}<br>
    {{ studySpace.stdNotice }} <br>
    
    Study Leader : {{ studySpace.studyJoins[0].user.userName }} <br>
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
    countStudyJoins(){
        const count = this.studyJoins.length
        return count
    }

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
