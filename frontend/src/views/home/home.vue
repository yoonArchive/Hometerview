<template>
  <div class="full-container">
  <header-vue></header-vue>
  <div class="main-container">
    <router-view></router-view>
  </div>
  </div>
  <footer-vue></footer-vue>
</template>


<style>


.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}
.full-container{
  /* background-color: blue; */
}
/* 컨테이너 */
.main-container {
  height: 100%;
  margin-right: 15%;
  margin-left: 15%;
  padding: 5%;
  padding-top: 7%;
  border: 1px;
  background-color: white;
}
@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
</style>
<script>
window.addEventListener('scroll', () => {

  const [red, green, blue] = [101, 63, 211]
  const section1 = document.querySelector(".full-container");
  let y = 1 + (window.scrollY || window.pageYOffset) / 3000
  y = y < 1 ? 1 : y // ensure y is always >= 1 (due to Safari's elastic scroll)
  const [r, g, b] = [red*y, green/y, blue*y]
  section1.style.background = `rgb(${r}, ${g}, ${b})`

})


import Conference from "./components/conference";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import headerVue from "../components/header.vue";
import footerVue from "../components/footer.vue"
export default {
  name: "Home",

  components: {
    headerVue,
    Conference,
    footerVue
  },

  setup() {
    const router = useRouter();

    const state = reactive({
      count: 12
    });

    const load = function() {
      state.count += 4;
    };

    const clickConference = function(id) {
      router.push({
        name: "conference-detail",
        params: {
          conferenceId: id
        }
      });
    };

    return { state, load, clickConference };
  }
};
</script>
