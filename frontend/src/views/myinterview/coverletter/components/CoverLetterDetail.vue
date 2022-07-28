<template>
  <div>
    <button>-</button>
    <button>+</button>
    <button v-for="(item , index) in countOfCoverLetter" :key="index" @click="getCurrentCoverLetter(item)"></button>
    <div>{{currentResume.question}}</div>
    <div>{{currentResume.answer}}</div>
  </div>
</template>

<script>

import { mapGetters, mapActions } from 'vuex'
export default {
  data(){
    return{
      itemNum: Number,

    }
  },
  props:{
    questionNum : Number,
  },
  computed:{
    ...mapGetters(['currentResume'])
  },
  methods:{
    ...mapActions(['getCurrentResume']),
    getCurrentCoverLetter(item){
      getCurrentResume(item);
    }
  }
}
</script>

<style>

</style>
