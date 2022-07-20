<template>
  <header-vue></header-vue>
  <div class="main-container">
    <router-view></router-view>
  </div>
</template>
<style>
.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}

/* 컨테이너 */
.main-container {
  margin-right: 15%;
  margin-left: 15%;
  padding: 5%;
  padding-top: 7%;
  border: 1px black solid;
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
import Conference from "./components/conference";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import headerVue from "../components/header.vue";
export default {
  name: "Home",

  components: {
    headerVue,
    Conference
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
