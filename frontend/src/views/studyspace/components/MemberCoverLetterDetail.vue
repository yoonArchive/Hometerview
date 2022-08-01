<template>
  <div>
    <div v-if="resumeQuestionList.length !=0 ">
      <input type="button" v-for="(item , index) in resumeQuestionList.length" :key="index" :value="item" @click="changeSelectedNum(item)"/>
      <div>
        <div>{{resumeQuestionList[selectedNum].detailNo}}</div>
        <div>{{resumeQuestionList[selectedNum].question}}</div>
        <div>{{resumeQuestionList[selectedNum].answer}}</div>
      </div>
    </div>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'
export default {
  data(){
    return{
      selectedNum: 0,
    }
  },
  props:{
    studentindex : Number,
  },
  computed:{
    ...mapGetters(['resumeQuestionList']),

  },

  methods:{
    changeSelectedNum(item){
      this.selectedNum = item - 1;
      console.log(this.selectedNum);
    }
  },



}
</script>

<style>

</style>
