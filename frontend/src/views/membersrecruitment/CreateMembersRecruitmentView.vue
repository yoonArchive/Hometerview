<template>

  <h1> 모집글 작성 </h1>

  <create-members-recruitment-form action='create'></create-members-recruitment-form>


</template>

<script>
  import CreateMembersRecruitmentForm from './components/CreateMembersRecruitmentForm.vue'


export default {
  name : 'CreateMembersRecruitmentView',
  components :{
    CreateMembersRecruitmentForm,
  },

}
</script>

<style>

</style>