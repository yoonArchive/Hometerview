<template>
  <div>
    <div>
      {{currentReview}}

      <br/>
      <br/>
      <p>번호 : {{currentReview.reviewNo}}</p>
      <p>제목 : {{ currentReview.reviewTitle}}</p>
      <p>내용 :{{ currentReview.reviewContents}}</p>
      <p v-if="currentReview.reviewType==='FAKE'">유형 : 혼자</p>
      <p v-else>유형 : 단체</p>
      </div>
      <!-- {{reviewContents[reviewNo].reviewTitle}} -->
    </div>


    <div class="buttonbundle">
          <router-link class="routerlink" :to="{ name: 'reviewEdit', params: { 'reviewNo' : reviewNo } }">
            <b-button class="makenewbutton" squared>수정하기</b-button>
          </router-link>
    </div>
    <span class="delete">
            <button @click="deleteReview(reviewNo)">Delete</button>
    </span>
    <div>
        <router-link class="routerlink" :to="{ name: 'myinterview' }" id="a">
            <button>목록으로</button>
          </router-link>
      </div>
    <!-- <cover-letter-detail :questionNum="reviewContents.length"></cover-letter-detail> -->
    <!-- <review-detail :reviewContents="reviewContents"></review-detail> -->


</template>

<script>
import { mapActions, mapGetters } from 'vuex'
// import CoverLetterDetail from './components/ReviewDetail.vue'
import ReviewDetail from './components/ReviewForm.vue'
export default {
  data(){
    return{
      reviewNo : this.$route.params.reviewNo
    }
  },

  components:{
    ReviewDetail
},

  computed:{
    ...mapGetters(['reviewContents','currentReview']),
  },
  methods:{
    ...mapActions(["getReviewInfo", "getReviewDetail","fetchReview","deleteReview"]),
    // findreviews(){
    //   this.getreviewInfo(this.reviewNo);

    // },
    findreviewDetail(){
      this.getReviewDetail(this.reviewNo)
    },
  },
  created(){
    this.findreviewDetail();
    console.log(this.reviewNo)

  }
}
</script>

<style>

</style>
