<template>

  <div class="container">
    <study-space-info></study-space-info>
  </div>


</template>

<script>
import StudySpaceInfo from './components/StudySpaceInfo.vue'

export default {
  name:"StudySpaceDetailView",
  data(){
    return{
      stdNo:this.$route.params.stdNo,
    }
  },
  components:{
    StudySpaceInfo,

  },
}
</script>

<style>

</style>
