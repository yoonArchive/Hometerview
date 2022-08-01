<template>

  {{ member.user.userId }} <br>
  {{ member.user.userName }}<br>
  {{ member.user.userEmail }}<br>
  {{ member.user.ueserImg }}<br>
  {{ member.user.joinTyp }}<br>
  {{ member.user.resumeNo }}
  <router-link :to="{ name: 'membercoverletter', params: { studentindex } }">
    자소서 보기
  </router-link>

</template>

<script>
import router from '@/common/lib/vue-router'

export default {
  name : "StudyMembers",
  props:{
    member:Object,
    studentindex:Number,
  },

  data(){
    return{
      }
    },
}
</script>

<style>

</style>
