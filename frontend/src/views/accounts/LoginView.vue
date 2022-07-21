<template>

  <h1>Login</h1>
  <!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="login(credentials)">
    <div>
      <label for="id">ID: </label>
      <input v-model="credentials.id" type="text" id="id" required />
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
      // AccountErrorList
    },

    data() {
      return {
        credentials: {
          id: '',
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