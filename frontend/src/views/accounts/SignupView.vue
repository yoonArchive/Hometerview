<template>
  <div>
    <h1>Signup</h1>

    <!-- <account-error-list v-if="authError"></account-error-list> -->

    <form @submit.prevent="signup(credentials)">

      <div>
        <label for="email">e-mail: </label>
        <input  v-model="credentials.email" type="text" id="email" required/>
      </div>
      <div>
        <label for="userName">User Name: </label>
        <input  v-model="credentials.userName" type="text" id="userName" required/>
      </div>
      <div>
        <label for="id">ID: </label>
        <input  v-model="credentials.id" type="text" id="id" required/>
      </div>
      <div>
        <label for="password1">Password: </label>
        <input v-model="credentials.password1" type="password" id="password1" required />
      </div>
      <div>
        <label for="password2">Password Confirmation:</label>
        <input v-model="credentials.password2" type="password" id="password2" required />
      </div>
      <div>
        <button>Signup</button>
      </div>
    </form>
    
  </div>
  
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  // import AccountErrorList from '@/components/AccountErrorList'

  export default {
    name: 'SignupView',
    components: {
      // AccountErrorList,
    },
    data() {
      return {
        credentials: {
          userEmail:'',
          userName:'',
          id: '',
          password1: '',
          password2: '',
        }
      }
    },
    computed: {
      ...mapGetters(['authError']),

    },
    methods: {
      ...mapActions(['signup'])
    },
  }
</script>

<style>


</style>
