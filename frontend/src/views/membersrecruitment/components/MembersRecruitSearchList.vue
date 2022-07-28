<template>
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
  name: 'MembersRecruitSearchList',
  components :{
    MembersRecruitmentItem,
  },
  computed: {
    ...mapGetters(['recruitmentList']),

  },
  methods: {
    ...mapActions(['bringRecruitSearchList']),
  },
}
</script>

<style>

</style>