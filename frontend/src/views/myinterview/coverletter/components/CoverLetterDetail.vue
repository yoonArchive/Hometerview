<template>
  <div>
    <button @click="changeIsremove">-</button>
    <button @click="addCurrentResume">+</button>
    <div v-if="currentResume.length !=0 ">

      <input type="button" v-for="(item , index) in currentResume.length" :key="index" :value="item" @click="returnnumbermethod(item)"/>
      <div>

        <input type="text" v-model="currentResume[selectedNum].detailNo"/>
        <input type="text" v-model="currentResume[selectedNum].question"/>
        <input type="text" v-model="currentResume[selectedNum].answer"/>

        <button @click="saveResumeChange">저장하기</button>
      </div>

    </div>
  </div>
</template>

<script>

import { mapGetters, mapActions } from 'vuex'
export default {
  data(){
    return{
      selectedNum: 0,
      isremove: false,
    }
  },
  mounted(){
    console.log(this.currentResume.length)
  },
  props:{
    resumeindex : Number,
  },
  computed:{
    ...mapGetters(['currentResume', 'numberOfQuestion', 'isLodding']),
    length(){
      return this.currentResume.length;
    }
  },

  methods:{
    ...mapActions(['saveResumeChange', 'addItemCurrentResume', 'removeItemCueentResume']),
    addCurrentResume(){
      this.addItemCurrentResume();
    },
    changeIsremove(){
      this.isremove = !this.isremove;
    },
    returnnumbermethod(item){
      if(this.isremove){
        this.removeResumeQuestion(item);
        console.log("지우기 모드");
      }else{
        this.changeSelectedNum(item);
        console.log("선택 모드");
      }
    },
    async removeResumeQuestion(item){
      await this.saveResumeChange();
      console.log("지우기 시작");
      await this.removeItemCueentResume(item - 1);
    },
    changeSelectedNum(item){
      this.selectedNum = item - 1;
      console.log(this.selectedNum);
    }
  },



}
</script>

<style>

</style>
