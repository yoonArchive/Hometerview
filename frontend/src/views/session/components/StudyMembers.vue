<template>
    <div class="member"> 
     &nbsp;&nbsp;&nbsp;
     {{ member.joinType }}
    &nbsp;&nbsp; {{ member.user.userId }} &nbsp; {{ member.user.userName }} &nbsp; {{ member.user.userEmail }} <br>
    {{ member.user.ueserImg }}   {{ member.user.joinTyp }}   {{ member.user.resumeNo }}
    <router-link :to="{ name: 'membercoverletter', params: { studentindex : studentindex } }">
      자소서 보기
    </router-link>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: "StudyMembers",
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
    ...mapGetters(['studySpaceDetail'])
  },
  
}
</script>

<style>

</style>