<template>
  <div>
    {{ this.recordings }}
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapGetters(["recordings"])
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
};
</script>
