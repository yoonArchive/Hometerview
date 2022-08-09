<template>

  {{ member }}
  {{ member.user.ueserImg }} <br>
  {{ member.user.userName }}
  <label for={{member.user.userName}}>선택</label>
  <input @change="change()" type="radio" id={{member.user.userName}} :value="member.user.userName" name="selectInterviewee" v-model="interviewUser">


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
    ...mapGetters([]),
  },
  methods:{
    ...mapActions([]),
    change(){
      console.log(this.interviewUser)
    }
  },


}
</script>



<style>

</style>
