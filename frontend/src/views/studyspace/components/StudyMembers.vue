<template>

  {{ member.user.userId }} <br>
  {{ member.user.userName }}<br>
  {{ member.user.userEmail }}<br>
  {{ member.user.ueserImg }}<br>
  {{ member.user.joinTyp }}<br>
  {{ member.user.resumeNo }}


</template>

<script>
import router from '@/common/lib/vue-router'

export default {
  name : "StudyMembers",
  props:{
    member:Object,
  },

  data(){
    return{
      }
    },
}
</script>

<style>

</style>