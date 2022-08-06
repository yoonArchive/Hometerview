<template>
  
  <study-members
      v-for="(member,index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
  ></study-members>


</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import StudyMembers from './StudyMembers.vue'

export default {
  name:"StudyMemberList",
  components:{
    StudyMembers
  },
  data(){
    return{
      sessionNo: this.$route.params.sessionNo,
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail']),
  },
  methods:{
  ...mapActions(['bringStudySpaceDetail']),
  },

  created(){
    this.bringStudySpaceDetail(this.sessionNo)
  }

}
</script>

<style>

</style>