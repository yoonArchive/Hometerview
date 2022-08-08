<template>
  <div class="row row-cols-1 row-cols-md-3 g-4">
    <div class="col" v-for="(studySpace,index) in studySpaceList" :key="studySpace.std_no">
      <study-space-item
        :studySpace="studySpace"
        :itemIndex="index"
      ></study-space-item>

    </div>
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
<<<<<<< HEAD
  mounted(){
    this.bringStudySpace()
=======
 mounted(){
    this.bringStudySpace()

>>>>>>> ec6dfff68a975f91ae0959f45aad8e60acbba26b
  },

}
</script>

<style>

</style>
