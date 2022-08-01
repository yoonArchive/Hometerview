<template>
  <h1>Detail Page</h1>
  
  <study-space-info></study-space-info>


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
  mounted(){
    this.bringStudySpaceDetial(this.stdNo)
  }


}
</script>

<style>

</style>
