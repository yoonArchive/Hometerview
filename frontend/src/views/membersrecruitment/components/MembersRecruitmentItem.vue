<template>
  
  <div>
    {{ recruitment.recruitTitle }} <br>
    {{ recruitment.stdName }} <br>
    {{ recruitment.stdDay }} <br>
    {{ recruitment.recruitStatus.status }} <br>
    {{ recruitment.stdImg }}
    <button @click="moveToDetail()"> 상세보기 </button>
    <hr>

  </div>

</template>

<script>
import router from '@/common/lib/vue-router'
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
    moveToDetail(){
      router.push({
        name:'membersrecruitmentdetail',
        params:{recruitNo:this.recruitment.recruitNo}
        })
    }
  },
  


}

</script>

<style>

</style>