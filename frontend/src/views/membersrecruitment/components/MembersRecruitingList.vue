<template>

  <h2>모집중</h2>
  <div class="row row-cols-1 row-cols-md-3 g-4">
    <members-recruitment-item
      v-for="recruitment in recruitmentList"
      :key="recruitment.recruitNo"
      :recruitment="recruitment"
    >
    </members-recruitment-item>
  </div>

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