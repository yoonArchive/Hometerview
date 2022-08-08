<template>
  <select-interveiwee-detail
    v-for="(member,index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
  ></select-interveiwee-detail>
</template>

<script>
import SelectInterveiweeDetail from './SelectInterveiweeDetail.vue'

export default {
  name:'SelectInterveiwee',
  components:{
    SelectInterveiweeDetail
  },
  data(){
    return{
      sessionNo: this.$route.params.sessionNo,
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail']),
  },
  methods:{
  ...mapActions(['bringStudySpaceDetail']),
  },
  created(){
    this.bringStudySpaceDetail(this.sessionNo)
  }

}
</script>

<style>

</style>