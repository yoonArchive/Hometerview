<template>

  <form @submit.prevent="onSubmit">
    <div class="wrap">
      <div class="review">
        <div class="review-top">
          <label class="col-sm-2" for="title">제목: </label>
            {{ review.reviewType}}
          <input class="col-sm-10" v-model="newreview.reviewTitle" type="text" id="title" />
        </div>
        <div class="review-content">
          <textarea class="col-sm-12 review-textarea" v-model="newreview.reviewContents" type="text" id="content"></textarea>
        </div>


        <div>
          <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType">
          <label for="newreview.reviewType">REAL</label>
          <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType">
          <label for="newreview.reviewType">FAKE</label>
        </div>


        <div class="form-button">
           <button>{{ action }}</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex'
// import review from '../../../main/store/modules/review'

  export default {
    name: 'reviewForm',
    props: {
      review: Object,
      action: String
    },
    data() {
      return {
        newreview: {
          // title: this.review.reviewTitle,
          // content: this.review.reviewContents,
          reviewNo : this.$route.params.reviewNo,
          reviewContents: this.review.reviewContents,
          reviewTitle: this.review.reviewTitle,
          reviewType: this.review.reviewType

                  },




      }
    },

    methods: {
      ...mapActions(['createReview', 'updateReview']),
      onSubmit() {
        if (this.action === 'create') {
          // this.action = '작성하기'
          this.createReview(this.newreview)
        } else if (this.action === 'update') {
          const payload = {
            reviewNo: this.review.reviewNo,
            ...this.newreview,
          }
          this.updateReview(payload)
        }
      },
    },
    created(){
      console.log('리뷰타입'+this.review.reviewType)
    }

  }
</script>

<style scoped>
.wrap{  width: 70vw; height: 50vh; margin: auto; padding-top : 30px;}
.review{ display: flex; border: 1px solid; flex-direction: column; width: auto; height:100%; font-family : '엘리스디지털regular';}
.review-top{ border : 1px solid; background-color: white; padding: 10px; font-weight: bold;}
.review-content{padding: 20px; color:white;}
.review-textarea{resize : none; background-color: white; color: black}
.form-button{ margin-left : auto; margin-top : auto;}
#content{
  height : 250px;
}
</style>
