<template>


  <h1>Edit Page</h1>
  <create-members-recruitment-form :recruitNo="recruitNo" :recruitDetail="recruitDetail" action="수정하기"></create-members-recruitment-form>

</template>
<script>
import CreateMembersRecruitmentForm from './components/CreateMembersRecruitmentForm.vue'
import { mapGetters } from 'vuex'

export default {
  name : 'MembersRecruitmentView',

  data(){
    return{
      recruitNo:this.$route.params.recruitNo,
    }
  },

  components : {
    CreateMembersRecruitmentForm,
  },
  computed :{
    ...mapGetters(['recruitDetail']),

  },


}
</script>

<style>

</style>
