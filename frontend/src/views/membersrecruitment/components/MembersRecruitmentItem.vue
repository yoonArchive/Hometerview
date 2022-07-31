<template>
  <div class="col">
    <div class="card">
      <img src="{{ recruitment.stdImg }}" class="card-img-top" alt="...">
      <div class="card-body">
        <h5 class="card-title">{{ recruitment.stdName }}</h5>
        <p class="card-text">
          {{ recruitment.recruitTitle }}
        </p>
        <p class="card-text">
          <small class="text-muted">{{ recruitment.stdDay }}</small><br>
          <small class="text-muted">{{ recruitment.recruitStatus.status }} </small>
          <button class="d-flex align-items-end" @click="moveToDetail()"> 상세보기 </button>
        </p>
      </div>
    </div>
  </div>

</template>

<script>
import router from '@/common/lib/vue-router'
export default {
  name : "MembersRecruitmentItem",
  props:{
    recruitment: Object
  },

  data(){
    return{
      }
    },
  computed: {
  },
  methods: {
    moveToDetail(){
      router.push({
        name:'membersrecruitmentdetail',
        params:{recruitNo:this.recruitment.recruitNo}
        })
    }
  },
  


}

</script>

<style>

</style>