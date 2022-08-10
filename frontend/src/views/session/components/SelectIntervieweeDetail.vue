<template>

  <!-- <label :for="clientData">
    {{ clientData }}
  </label>
  <input type="radio" :id="clientData" :value="sub" name="selectInterviewee"> -->

  <label :for="member.user.userId">
    {{ member.user.userNo }} <br>
    {{ member.user.ueserImg }} <br>
    {{ member.user.userName }} <br>
    {{ member.user.userId }}
  </label>
  <input @change="change()" type="radio" :id="member.user.userId" :value="member.user.userId" name="selectInterviewee" v-model="interviewUser">
</template>

<script>
import { mapActions,mapGetters } from 'vuex'
export default {
  name:'SelectIntervieweeDetail',
  props:{
    member:Object,
    studentindex:Number,
    sub:Object,
  },
  data(){
    return{
      interviewUser : '',
      
    }
  },
  computed:{
    ...mapGetters(['interviewUser']),
    clientData () {
			// 이름 띄우기
			const { clientData, clinetNo } = this.getConnectionData();
			return clientData, clinetNo;
		},
  },
  methods:{
    ...mapActions(['changeInterviewUser']),
    change(){
      console.log(this.interviewUser)
      this.changeInterviewUser(this.interviewUser)
    },
    getConnectionData () {
			const { connection } = this.sub.stream;
			return JSON.parse(connection.data);
		},
  },


}
</script>



<style>

</style>
