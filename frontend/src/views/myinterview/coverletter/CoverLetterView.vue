<template>
  <div>
    <div>
      {{resumeContents[resumeindex].resumeTitle}}
    </div>
    <div>

    </div>
    <cover-letter-detail :resumeindex="resumeindex"></cover-letter-detail>


  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import CoverLetterDetail from './components/CoverLetterDetail.vue'
export default {
  data(){
    return{
      resumeindex : this.$route.params.resumeindex
    }
  },
  created(){
    this.getResumeInfo();
    this.getCurrentResume(this.resumeindex);
    this.settingResumeIndex(this.resumeindex);
  },
  methods:{
    ...mapActions(['settingResumeIndex','getCurrentResume','getResumeInfo']),
  },
  components:{
    CoverLetterDetail,
  },

  computed:{
    ...mapGetters(['resumeContents', 'currentResume']),
  },


}
</script>

<style>

</style>
