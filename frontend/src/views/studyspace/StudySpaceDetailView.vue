<template>
  <div class="container">
    <study-space-info></study-space-info>
  </div>
</template>

<script>
import StudySpaceInfo from "./components/StudySpaceInfo.vue";
import { mapActions } from "vuex";
export default {
  name: "StudySpaceDetailView",
  data() {
    return {
      stdNo: this.$route.params.stdNo
    };
  },
  methods: {
    ...mapActions(["setStudyNoAction"])
  },
  components: {
    StudySpaceInfo
  },
  mounted() {
    this.setStudyNoAction(this.stdNo);
  }
};
</script>

<style></style>
