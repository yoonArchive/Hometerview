<template>

  <div class="member"> 
     &nbsp;&nbsp;&nbsp;
     {{ member.joinType }}
    <button @click="kickMember(stdMemberInfo)" v-if="studySpaceDetail.joinType==='LEADER' && member.joinType!=='LEADER'"> 추방하기 </button><br>
    &nbsp;&nbsp; {{ member.user.userId }} &nbsp; {{ member.user.userName }} &nbsp; {{ member.user.userEmail }} <br>
    {{ member.user.ueserImg }}   {{ member.user.joinTyp }}   {{ member.user.resumeNo }}
    <router-link :to="{ name: 'membercoverletter', params: { studentindex : studentindex } }">
      자소서 보기
    </router-link>
    
  </div>

</template>
<script>
import router from '@/common/lib/vue-router'
import { mapActions,mapGetters } from 'vuex'

export default {
  name : "StudyMembers",
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
  methods:{
    ...mapActions(['kickMember'])
  }

}
</script>

<style>

.member{
  box-sizing: border-box;
  width: 840px;
  height: 150px;
  /* left: 300px;
  top: 391px; */
  
  background: #FCFCFC;
  border: 1px solid #000000;
  border-radius: 30px;

}
</style>
