<template>

  <button @click="kickMember(stdMemberInfo)"> 추방하기 </button><br>
  {{ member.user.userId }} <br>
  {{ member.user.userName }}<br>
  {{ member.user.userEmail }}<br>
  {{ member.user.ueserImg }}<br>
  {{ member.user.joinTyp }}<br>
  {{ member.user.resumeNo }} <br>
  <router-link :to="{ name: 'membercoverletter', params: { studentindex : studentindex } }">
    자소서 보기
  </router-link>

</template>

<script>
import router from '@/common/lib/vue-router'
import { mapActions } from 'vuex'

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
      }
      }
    },
  methods:{
    ...mapActions(['kickMember'])
  }

}
</script>

<style>

</style>
