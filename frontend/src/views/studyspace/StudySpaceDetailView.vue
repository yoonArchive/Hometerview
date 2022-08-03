<template>
  <h1>Detail Page</h1>
  
  <div class="container">
    <study-space-info></study-space-info>
  </div>


</template>

<script>
import { mapActions } from 'vuex'
import StudySpaceInfo from './components/StudySpaceInfo.vue'

export default {
  name:"StudySpaceDetailView",
  data(){
    return{
      stdNo:this.$route.params.stdNo,
    }
  },
  methods:{
    ...mapActions(['bringStudySpaceDetial']),
  },
  // mounted(){
  //   this.bringStudySpaceDetail(this.stdNo)
  // }

  mounted(){
    this.bringStudySpaceDetial(this.stdNo)
  },
  components:{
    StudySpaceInfo,

  },
}
</script>

<style>

</style>
