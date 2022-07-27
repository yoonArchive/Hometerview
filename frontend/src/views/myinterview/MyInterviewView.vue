<template>

  <div v-for="(item, index) in numberOfResume" :key="index">
    <router-link :to="{name : 'coverletter', params : {'resumeindex' : item - 1}}">
      <div>{{resumeContents[item-1].resumeTitle}}</div>
    </router-link>

  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'


export default {
  computed:{
    ...mapGetters(['resumeContents','numberOfResume']),
  },
  methods:{
    ...mapActions(["getResumeInfo"]),
    findresumes(){
      this.getResumeInfo();
    }
  },
  created(){
    this.findresumes();

  }
}
</script>

<style>

</style>
