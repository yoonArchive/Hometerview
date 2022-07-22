<template>

  <h1>FindID</h1>
<!-- <account-error-list v-if="authError"></account-error-list> -->

  <form @submit.prevent="findUserid(credentials)">
    <div>
      <label for="userName">username: </label>
      <input v-model="credentials.userName" type="text" id="userName"/>
    </div>

    <div>
      <label for="userEmail">e-mail: </label>
      <input v-model="credentials.userEmail" type="text" id="userEmail"/>
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
          userName : '',
          userEmail : ''
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