<template>

  <h1>모집글 리스트</h1>

  <button @click="moveToCreate">새로 만들기</button>
  <members-recruitment-list></members-recruitment-list>

</template>


<script>
  // import { mapActions, mapGetters } from 'vuex'
  import MembersRecruitmentList from './components/MembersRecruitmentList.vue'
  import router from "@/common/lib/vue-router.js";

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
      moveToCreate(){
        router.push({name:'createmembersrecruitment'})
      }
    },
  }
</script>




<style>


</style>
