<template>
  <div class="account-error-list">
    <p>{{ authError }}</p>
    <!-- <p v-for="(errors, field) in authError" :key="field">
      {{ errors }} -->
    <!-- </p> -->

  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  
  export default {
    name: 'AccountErrorList',
    computed: {
      ...mapGetters(['authError'])
    },
  }
</script>

<style>
  .account-error-list {
    color: red;
  }
</style>
