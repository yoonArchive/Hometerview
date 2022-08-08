<template>
  <div class="member">
    <div class="study-member-wrapper">
      <div class="row justify-content-between">
        <div class="col-8">
          {{ member.user.userId }}  {{ member.user.userName }}  {{ member.user.userEmail }}
        </div>
        <div class="col-4">
          <div class="d-flex flex-row-reverse">
            <button @click="kickMember(stdMemberInfo)" v-if="studySpaceDetail.joinType==='LEADER' && member.joinType!=='LEADER'"> 추방하기 </button>
            <router-link :to="{ name: 'membercoverletter', params: { studentindex : studentindex } }">
              <i class="far fa-file-alt fa-lg" style="color:#653FD3"></i>
            </router-link>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
import router from '@/common/lib/vue-router'
import { mapActions,mapGetters } from 'vuex'

export default {
  name : "StudyMembers",
  props:{
    member:Object,
    studentindex:Number,
  },


  data(){
    return{
      stdMemberInfo:{
        stdNo:this.$route.params.stdNo,
        userNo: this.member.user.userNo
      },
    }
  },
  computed:{
    ...mapGetters(['studySpaceDetail'])
  },
  methods:{
    ...mapActions(['kickMember'])
  }

}
</script>


<style scoped>
.study-member-wrapper{
  padding: 5%;
}
.member{
  box-sizing: border-box;
  width: 100%;
  /* left: 300px;
  top: 391px; */

  background: #FCFCFC;
  border: 1px solid #653FD3;
  border-radius: 30px;

}
</style>
