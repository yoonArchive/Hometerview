<template>

  <h1>FindID</h1>
<!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="findUserid(credentials)">
    <div>
      <label for="username">username: </label>
      <input v-model="credentials.username" type="text" id="username" required />
    </div>

    <div>
      <label for="email">e-mail: </label>
      <input v-model="credentials.password" type="text" id="email" required />
    </div>

    <button>submit</button>

  </form>

</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  // import AccountErrorList from './components/AccountErrorList.vue'


  export default {
    name: 'FindUseridView',
    components: {
      // AccountErrorList
    },

    data() {
      return {
        credentials: {
          username: '',
          email: '',
        }
      }
    },
    computed: {
      ...mapGetters(['authError'])
    },
    methods: {
      ...mapActions(['findUserid'])
    },
  }
</script>




<style>

</style>