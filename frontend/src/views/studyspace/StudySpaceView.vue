<template>
  <div>
    <h3>내 스터디 보기</h3>
    <hr>
    <study-space-list></study-space-list>
  </div>

</template>

<script>
import StudySpaceList from './components/StudySpaceList.vue'
export default {
  name : 'StudySpaceView',
  components :{
    StudySpaceList,
  },
  data(){

  }



}
</script>

<style>

</style>
