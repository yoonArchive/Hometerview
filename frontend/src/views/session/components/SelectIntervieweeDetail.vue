<template>

  {{ member.user.userNo }} <br>
  {{ member.user.ueserImg }} <br>
  {{ member.user.userName }}
  <label for={{member.user.userName}}>선택</label>
  <input @change="change()" type="radio" id={{member.user.userName}} :value="member.user.userNo" name="selectInterviewee" v-model="interviewUser">

</template>

<script>
import { mapActions,mapGetters } from 'vuex'
export default {
  name:'SelectIntervieweeDetail',
  props:{
    member:Object,
    studentindex:Number,
  },
  data(){
    return{
      interviewUser : ''
    }
  },
  computed:{
    ...mapGetters(['interviewUser']),
  },
  methods:{
    ...mapActions(['changeInterviewUser']),
    change(){
      this.changeInterviewUser(this.interviewUser)
    }
  },


}
</script>



<style>

</style>
