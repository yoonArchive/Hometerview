<template>
  <h1>Detail Page</h1>

  <study-space-info></study-space-info>


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
