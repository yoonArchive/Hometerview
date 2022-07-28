<template>

  <h1> 모집글 작성 </h1>

  <create-members-recruitment-form :recruitDetail="recruitDetail" action='create'></create-members-recruitment-form>


</template>

<script>
  import CreateMembersRecruitmentForm from './components/CreateMembersRecruitmentForm.vue'
  import { mapGetters } from 'vuex'

  export default {
    name : 'CreateMembersRecruitmentView',
    components :{
      CreateMembersRecruitmentForm,
    },
    computed:{
      ...mapGetters(['recruitDetail'])
    }

  }
</script>

<style>

</style>