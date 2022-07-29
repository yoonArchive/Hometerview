<template>
  <div>



  </div>

</template>

<script>
import { mapActions, mapGetters } from 'vuex'


export default {
  data(){
    return {
      roomName : '',
      headers: ['번호','제목', '작성시간', '유형']
    }
  },
  computed:{
    ...mapGetters(['resumeContents','numberOfResume', 'reviewContents','numberOfReview']),
  },
  methods:{
    ...mapActions(["getResumeInfo","getReviewInfo","addNewResume"]),
    findresumes(){
      this.getResumeInfo();
    },
    addResume(){
      this.addNewResume();
    },

    findreveiw(){
      this.getReviewInfo();
    },

  },
  mounted(){
    this.findresumes();
    this.findreveiw();
  }
}
</script>

<style scoped>

</style>
