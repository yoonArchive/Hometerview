<template>

  <h1>모집글 리스트</h1>
  <div class="container">
    <div class="d-flex justify-content-end">
      <div >
        <label for="recruitSearch">전체 : </label>
        <input type="radio" id="recruitSearch" name="recruitSearch" value="1" v-model="recruitType" @change="bringRecruitTypeList(recruitType)">
        <label for="recruitSearch"> | 기업 면접 : </label>
        <input type="radio" id="recruitSearch" name="recruitSearch" value="2" v-model="recruitType" @change="bringRecruitTypeList(recruitType)">
        <label for="recruitSearch"> | 자율 면접 : </label>
        <input type="radio" id="recruitSearch" name="recruitSearch" value="3" v-model="recruitType" @change="bringRecruitTypeList(recruitType)">
      </div>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" name="recruiting" id="recruiting" v-model="recruiting" @change="isRecruiting(recruitState)">
        <label class="form-check-label" for="recruiting">| 모집 중</label>
      </div>
    </div>
    <div class="d-flex justify-content-end">
      <button class="new-create-button" @click="moveToCreate">스터디 만들기</button>
      <div>
        <label for="recruitSearch"></label>
        <input type="text" id="recruitSearch" v-model="recruitSearchKeyword" @keyup.enter="bringRecruitSearchList(recruitSearchKeyword)">
      </div>
    </div>

    <members-recruitment-list v-if="recruitState"></members-recruitment-list>
    <members-recruiting-list v-if="recruitingState"></members-recruiting-list>
  </div>

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
        recruitSearchKeyword : '',
        recruitType : ''
      }
    },
    computed: {

      },
    methods: {
      ...mapActions(['bringRecruitSearchList', 'bringRecruitTypeList']),

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
 .new-create-button{
  height: 40px;
  background: #F3F0FB;
  border: 1px solid #653FD3;
  border-radius: 16px;
 }

</style>
