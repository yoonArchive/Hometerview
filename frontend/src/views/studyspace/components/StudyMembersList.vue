<template>
  
    {{ studySpaceDetail }}
    <study-space-item
      v-for="member in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
    ></study-space-item>

</template>

<script>
import { mapActions,mapGetters } from 'vuex'
import StudyMembers from './StudyMembers.vue'

export default {

  name: 'StudySpaceList',
  components :{
    StudyMembers,
  },

  data(){
    return{
      }
    },
  computed: {
    ...mapGetters(['studySpaceDetail'])

  },
  methods: {
    // ...mapActions(['bringStudySpaceDetial'])
  },
  // created(){
  //   this.bringStudySpaceDetial(this.stdNo)
  // },

}
</script>

<style>

</style>