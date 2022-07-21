<template>

  <h1>Login</h1>
  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="login(credentials)">
    <div>
      <label for="username">username: </label>
      <input v-model="credentials.username" type="text" id="username" required />
    </div>

    <div>
      <label for="password">password: </label>
      <input v-model="credentials.password" type="password" id="password" required />
    </div>

    <button>Login</button>
  </form>


</template>


<script>
  import { mapActions, mapGetters } from 'vuex'
  // import AccountErrorList from './components/AccountErrorList.vue'
  // import { mapGetters,mapActions } from './map_state.js'


  export default {
    name: 'LoginView',
    components: {
    },

    data() {
      return {
        credentials: {
          username: '',
          password: '',
        }
      }
    },
    computed: {
      ...mapGetters(['authError'])
      },
    methods: {
      ...mapActions(['login'])
    },
  }
</script>




<style>

</style>