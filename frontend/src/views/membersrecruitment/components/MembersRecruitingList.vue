<template>

  <h2>모집중</h2>
  <members-recruitment-item
    v-for="recruitment in recruitmentList"
    :key="recruitment.recruitNo"
    :recruitment="recruitment"
  >
  </members-recruitment-item>


</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import MembersRecruitmentItem from './MembersRecruitmentItem.vue'

export default {
  name:"MembersRecruitingList",
  components :{
    MembersRecruitmentItem,
  },

  computed:{
    ...mapGetters(['recruitmentList'])
  },
  methods:{
    ...mapActions(['bringRecruitingList'])
  },
  created(){
    this.bringRecruitingList()
  }
}
</script>

<style>

</style>