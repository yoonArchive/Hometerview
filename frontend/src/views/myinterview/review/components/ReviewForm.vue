<template>
  <div class="container">


    <div class="inner-container">
    <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="title" class="form-label" >제목: </label>
      <input type="text" class="form-control" v-model="newreview.reviewTitle" id="title" />
    </div>
    <div class="mb">
      <label for="exampleFormControlTextarea1" class="form-label">내용: </label>
      <textarea class="form-control" v-model="newreview.reviewContents" type="text" id="content" rows="3"></textarea>
    </div>
    <!-- <div>
        <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType">
        <label for="newreview.reviewType">REAL</label>
        <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType">
        <label for="newreview.reviewType">FAKE</label>
    </div> -->




    <div>
      <input type="radio" name="reviewType" value="REAL" v-model="newreview.reviewType">
      <label for="newreview.reviewType">REAL</label>
      <input  type="radio" name="reviewType" value="FAKE" v-model="newreview.reviewType">
      <label for="newreview.reviewType">FAKE</label>
    </div>


    <div class="buttonbundle">
        <button id="button-review" v-if="action==='create'">만들기</button>
        <button id="button-review" v-else>수정하기</button>
    </div>
     </form>
    </div>

  </div>

</template>

<script>
import { mapActions } from 'vuex'
// import review from '../../../main/store/modules/review'
  const day1 = new Date();
  const today = day1.toLocaleString('ko-kr');
  //.toLocaleString('ko-kr')
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
          // reviewNo : this.$route.params.reviewNo,
          reviewContents: this.review.reviewContents,
          reviewTitle: this.review.reviewTitle,
          reviewType: this.review.reviewType,
          reviewDate: today
          }




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

.form-control:focus{
  border: solid 3px #856ccf;
  outline: solid 1px #9376e9;
}
.container{
  display: flex;
  width: 100%;
}
#button-review{
   background-color: #653FD3;
   border-color : #653FD3;
   border-radius: 10%;
   margin: 15px;
   width: 100px;
   color: white;
}
.inner-container{
  flex-direction: column;
  width: 100%;
  height: 100%;
}
</style>
