<template>
  <div>자소서 추가 삭제 버튼</div>
  <button @click="addResume">+</button>
  <button>-</button>
  <div v-for="(item, index) in numberOfResume" :key="index">
    <router-link :to="{name : 'coverletter', params : {'resumeindex' : item - 1}}">
      <div>{{resumeContents[item-1].resumeTitle}}</div>
    </router-link>
  </div>

  <!-- <div>{{ reviewContents }}</div> -->
  <!-- <h1>{{numberOfReview}}</h1> -->
  <div class="review">
    <h1>회고록</h1>
    <!-- {{ reviewContents}} -->
    <div v-for="(reviews, index) in reviewContents" :key="index">
      <!-- <p>{{ reviews }}</p> -->

      <router-link :to="{name : 'review', params : {'reviewNo' : reviews.reviewNo}}">

        <div>  {{reviews.reviewTitle}}
        <p v-if="reviews.reviewType==='FAKE'">(혼자)</p>
        <p v-else>(단체)</p>
        </div></router-link>



          </div>
          </div>
       <div class="buttonbundle">
          <router-link class="routerlink" :to="{ name: 'reviewNew' }">
            <b-button class="makenewbutton" squared>새로 만들기</b-button>
          </router-link>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'


export default {
  data(){
    return {
      roomName : ''
    }
  },
  computed:{
    ...mapGetters(['resumeContents','numberOfResume', 'reviewContents','numberOfReview']),
  },
  methods:{
    ...mapActions(["getResumeInfo","getReviewInfo"]),
    findresumes(){
      this.getResumeInfo();
    },
    addResume(){
    },

    findreveiw(){
      this.getReviewInfo();
    },
    changeName(){
    }
  },
  created(){
    this.findresumes();
    this.findreveiw();
  }
}
</script>

<style>

</style>
