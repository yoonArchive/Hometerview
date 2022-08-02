<template>
  <h1>Detail Page</h1>
  
  <div class="container">
    <study-space-info></study-space-info>
  </div>


</template>

<script>
import router from '@/common/lib/vue-router'
import StudySpaceInfo from './components/StudySpaceInfo.vue'

export default {
  name:"StudySpaceDetailView",
  components:{
    StudySpaceInfo,

  },

  data(){
    return{
      stdNo:this.$route.params.stdNo,
    }
  },
  computed:{
  },
  methods:{
  },
  // mounted(){
  //   this.bringStudySpaceDetail(this.stdNo)
  // }


}
</script>

<style>

</style>
