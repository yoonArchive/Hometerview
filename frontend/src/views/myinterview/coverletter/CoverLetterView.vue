<template>
  <div>
    <div>

      {{resumeContents[resumeNo].resumeTitle}}
    </div>
    <div>

    </div>
    <cover-letter-detail :questionNum="resumeContents.length"></cover-letter-detail>


  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import CoverLetterDetail from './components/CoverLetterDetail.vue'
export default {
  data(){
    return{
      resumeNo : this.$route.params.resumeNo
    }
  },

  components:{
    CoverLetterDetail,
  },

  computed:{
    ...mapGetters(['resumeContents']),
  },
  methods:{
    ...mapActions(["getResumeInfo"]),
    findresumes(){
      this.getResumeInfo(this.resumeNo);

    }
  },
  created(){
    this.findresumes();

  }
}
</script>

<style>

</style>
