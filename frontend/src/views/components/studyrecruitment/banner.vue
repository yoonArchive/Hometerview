<template>
  <div class="banners-wrapper">
    <banneritem-vue
      v-for="(item, index) in num2"
      :key="index"
      :bannernumber="index"
    ></banneritem-vue>
  </div>
</template>

<script>
import banneritemVue from "./banneritem.vue";

export default {
  components: {
    banneritemVue
  },
  setup() {
    const num2 = 2;

    return { num2 };
  }
};
</script>

<style>
.banners-wrapper {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10%;
}
</style>
