<template>
  <div>
    {{ studySpaceDetail }}
    {{ studySpaceDetail.studyJoins }}
    {{  }}
  </div>
  <!-- 자소서 보기 -->
  <div v-if="coverLetter">
    <button @click="changeToCoverLetter(['memberlist',null])">닫기</button>
    <member-cover-letter :studentindex="studentIndex"></member-cover-letter>
  </div>

  <!-- 리스트 보기 -->
  <div v-if="memberList">
    <study-members
        v-for="(member,index) in studySpaceDetail.studyJoins"
        :key="member.joinNo"
        :member="member"
        :studentindex="index"
    ></study-members>

  </div>




</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import StudyMembers from './StudyMembers.vue'
import MemberCoverLetter from './MemberCoverLetter.vue'

export default {
  name:"StudyMemberList",
  components:{
    StudyMembers,
    MemberCoverLetter
  },
  data(){
    return{
      sessionNo: this.$route.params.sessionNo,
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail','studentIndex','coverLetter','memberList']),
  },
  methods:{
  ...mapActions(['changeToCoverLetter']),
  },

  // created(){
  //   this.bringStudySpaceDetail(this.sessionNo)
  // }

}
</script>

<style>

</style>