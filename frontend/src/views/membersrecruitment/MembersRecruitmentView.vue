<template>

  <h1>모집글 리스트</h1>
  <div>
    <label for="recruiting">모집 중</label>
    <input type="checkbox" name="recruiting" id="recruiting" v-model="recruiting" @change="isRecruiting(recruitState)">
  </div>
  <button @click="moveToCreate">새로 만들기</button>
  <div>
    <label for="recruitSearch"></label>
    <input type="text" id="recruitSearch" v-model="recruitSearchKeyword" @keyup.enter="bringRecruitSearchList(recruitSearchKeyword)">
  </div>

  <members-recruitment-list v-if="recruitState"></members-recruitment-list>
  <members-recruiting-list v-if="recruitingState"></members-recruiting-list>

</template>


<script>
  import MembersRecruitmentList from './components/MembersRecruitmentList.vue'
  import MembersRecruitingList from './components/MembersRecruitingList.vue'
  import router from "@/common/lib/vue-router.js";
  import { mapActions } from 'vuex';

  export default {
    name: 'MembersRecruitmentView',
    components: {
      MembersRecruitmentList,
      MembersRecruitingList,

    },

    data() {
      return {
        recruitState : true,
        recruitingState : false,
        recruitSearchKeyword : ''
      }
    },
    computed: {

      },
    methods: {
      ...mapActions(['bringRecruitSearchList']),

      moveToCreate(){
        router.push({name:'createmembersrecruitment'})
      },
      // async moveToRecruitSearch(recruitSearchKeyword){
      //   await this.bringRecruitSearchList(recruitSearchKeyword)

      // },
      isRecruiting(){
        if(this.recruitState===true){
          this.recruitState = false
          this.recruitingState = true

        }else{
          this.recruitState = true
          this.recruitingState = false
        }
      }
    },
  }
</script>




<style>


</style>
