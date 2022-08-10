<template>
  <div>
    <h1>잘못된 접근입니다.</h1>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  methods: {
    ...mapActions(["logout"])
  },
  computed: {
    ...mapGetters(["isLoggedIn"])
  },
  created() {
    if (this.isLoggedIn) {
      this.logout();
    } else {
      alert("잘못된 접근");
      this.$router.back();
    }
  }
};
</script>

<style></style>
