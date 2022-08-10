<template>
  <div>
    <!-- <notice-form v-if="isnotice" :notice="notice" action="update">

    </notice-form> -->
    <notice-form :notice="notice" action="update"></notice-form>
  </div>
</template>

<script>
import noticeForm from "./components/noticeForm.vue";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "NoticeEditView",
  components: { noticeForm },
  computed: {
    ...mapGetters(["notice", "isnotice"])
  },
  methods: {
    ...mapActions(["fetchNotice"])
  },
  created() {
    this.fetchNotice(this.$route.params.noticeNo);
  }
};
</script>

<style></style>
