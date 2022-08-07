<template>
  <div class="row row-cols-1 row-cols-md-3 g-4">
    <study-space-item
      v-for="studySpace in studySpaceList"
      :key="studySpace.std_no"
      :studySpace="studySpace"
    ></study-space-item>

  </div>
</template>

<script>
import { mapActions,mapGetters } from 'vuex'
import StudySpaceItem from './StudySpaceItem.vue'

export default {

  name: 'StudySpaceList',
  components :{
    StudySpaceItem,
  },

  data(){
    return{
      isloading : false
      }
    },
  computed: {
    ...mapGetters(['studySpaceList']),

  },
  methods: {
    ...mapActions(['bringStudySpace']),
  },
  mounted(){
    this.bringStudySpace()
    
  },

}
</script>

<style>

</style>
