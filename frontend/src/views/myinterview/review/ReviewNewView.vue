<template>
  <div>
    <!-- <article-form :article="article" action="create"></article-form> -->
    <review-form :review="review" action="create"></review-form>
  </div>
</template>

<script>
import ReviewForm from "./components/ReviewForm.vue";
export default {
  name: "ReviewNewView",
  components: { ReviewForm },
  data() {
    return {
      review: {
        // reviewNo: null,
        reviewContents: "",
        reviewTitle: "",
        reviewType: "REAL"
      }
    };
  },
  updated() {
    event;
  }
};
</script>

<style></style>
