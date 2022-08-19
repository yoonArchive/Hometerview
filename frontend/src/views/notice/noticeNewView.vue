<template>
  <div>
    <!-- <article-form :article="article" action="create"></article-form> -->
    <notice-form :notice="notice" action="create"></notice-form>
  </div>
</template>

<script>
import NoticeForm from "./components/noticeForm.vue";
export default {
  name: "NoticeNewView",
  components: { NoticeForm },
  data() {
    return {
      notice: {
        noticeNo: null,
        noticeContents: "",
        noticeTitle: "",
        noticeDate: ""
      }
    };
  }
};
</script>

<style></style>
