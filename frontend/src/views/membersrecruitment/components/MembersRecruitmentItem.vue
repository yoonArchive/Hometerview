<template>
  
  {{ recruitment.recruitTitle }} <br>
  {{ recruitment.stdName }} <br>
  {{ recruitment.stdDay }} <br>
  {{ recruitment.recruitStatus.status }} <br>
  {{ recruitment.stdImg }} <hr>

</template>

<script>
export default {
  name : "MembersRecruitmentItem",
  props:{
    recruitment: Object
  },

  data(){
    return{
      }
    },
  computed: {
  },
  methods: {
  },
  


}

</script>

<style>

</style>