<template>
  
  <!-- {{ recruitmentList }} -->
  <members-recruitment-item
    v-for="recruitment in recruitmentList"
    :key="recruitment.recruitNo"
    :recruitment="recruitment"
  >
  </members-recruitment-item>

</template>

<script>
import { mapActions,mapGetters } from 'vuex'
import MembersRecruitmentItem from './MembersRecruitmentItem.vue'

export default {

  name: 'MembersRecruitmentList',
  components :{
    MembersRecruitmentItem,
  },

  data(){
    return{
      }
    },
  computed: {
    ...mapGetters(['recruitmentList']),


  },
  methods: {
    ...mapActions(['bringRecruitmentList'])
  },
  
  created(){
    this.bringRecruitmentList()
    console.log('되니?')
  }
}
</script>

<style>

</style>