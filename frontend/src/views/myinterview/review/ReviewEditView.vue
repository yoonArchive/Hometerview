<template>
  <div>

    <!-- <review-form v-if="isreview" :review="review" action="update">

    </review-form> -->
  <review-form :review="currentReview" action="update"></review-form>
  {{ currentReview }}
  </div>

</template>

<script>
import ReviewForm from './components/ReviewForm.vue'
import { mapGetters, mapActions } from 'vuex'
  export default {
    data() {
      return {
        review: {
          reviewNo: null,
          reviewContents: this.currentReview.reviewContents,
          reviewTitle: this.currentReview.reviewTitle,
          reviewType: this.currentReview.reviewType

          // reviewNo: null,
          // reviewContents: '',
          // reviewTitle: '',
          // reviewType:''
        }
      }
    },
    name: 'ReviewEditView',
    components: { ReviewForm },
    computed: {
      ...mapGetters(['review', 'currentReview'])
    },
    methods: {
      ...mapActions(['getReviewDetail'])
    },
    created() {
      this.getReviewDetail(this.$route.params.reviewNo)
      console.log(this.$route.params.reviewNo)
      console.log('리뷰타입 '+ this.currentReview)
    },
  }
</script>

<style></style>
