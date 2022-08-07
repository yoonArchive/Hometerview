<template>
  <div>
     &nbsp;&nbsp;&nbsp;
     {{ member.joinType }}
    &nbsp;&nbsp; {{ member.user.userId }} &nbsp; {{ member.user.userName }} &nbsp; {{ member.user.userEmail }} <br>
    {{ member.user.ueserImg }}   {{ member.user.joinTyp }}   {{ member.user.resumeNo }}
    <button @click="changeToCoverLetter(['coverletter',studentindex])">자소서 보기</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import MemberCoverLetter from './MemberCoverLetter.vue'
export default {
  name: "StudyMembers",
  components:{
    MemberCoverLetter
  },

  props:{
  member:Object,
  studentindex:Number,
},
  data(){
    return{
      stdMemberInfo:{
        stdNo:this.$route.params.stdNo,
        userNo: this.member.user.userNo
      },

    }
  },
  computed:{
    ...mapGetters(['resumeQuestionList']),
  },
  methods:{
    ...mapActions(['getStudyResume','changeToCoverLetter']),
    // async changeToCoverLetter(content){
    //   if(content==="coverletter"){
    //     await this.getStudyResume(this.studentindex)
    //     this.coverletter = true
    //     this.memberlist = false
    //   }
    //   else if(content==="memberlist"){
    //     this.coverletter = false
    //     this.memberlist = true
    //   }
    // }
  }
  
}
</script>

<style>

</style>