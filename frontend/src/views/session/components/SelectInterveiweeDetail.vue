<template>

  {{ member.user.ueserImg }}
  {{ member.user.userName }}
  
  <div>
    <label for="selectInterviewee">선택</label>
    <input type="checkbox" id="selectInterviewee" name="selectInterviewee">
  </div>

</template>

<script>
import { mapActions,mapGetters } from 'vuex'
export default {
  name:'SelectInterveiweeDetail',
  props:{
    member:Object,
    studentindex:Number,
  },
  data(){
    return{

    }
  },
  computed:{
    ...mapGetters([]),
  },
  methods:{
  ...mapActions([]),
  },


}
</script>



<style>

</style>
