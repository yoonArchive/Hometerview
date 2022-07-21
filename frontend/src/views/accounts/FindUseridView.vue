<template>

  <h1>FindID</h1>


  <form>
    <div>
      <label for="username">username: </label>
      <input v-model="credentials.username" type="text" id="username" required />
    </div>

    <div>
      <label for="email">e-mail: </label>
      <input v-model="credentials.password" type="text" id="email" required />
    </div>
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

      },
    methods: {

    },
  }
</script>




<style>

</style>