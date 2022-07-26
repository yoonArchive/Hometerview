<template>

  <h1>모집글 리스트</h1>

  <members-recruitment-list></members-recruitment-list>

</template>


<script>
  // import { mapActions, mapGetters } from 'vuex'
  import MembersRecruitmentList from './components/MembersRecruitmentList.vue'


  export default {
    name: 'MembersRecruitmentView',
    components: {
      MembersRecruitmentList,
    },

    data() {
      return {

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
