<template>
  <div id="headerFrame">
    <router-link to="/home" class="header-routerlink-decoration">
      <img id="headerLogo" :src="imagesrc" alt="" style="padding-left:2%;" />
    </router-link>
    <div id="headerMenu">
      <router-link
        :to="item.path"
        v-for="(item, index) in state.headeritems"
        :key="index"
        :index="index.toString()"
        class="header-routerlink-decoration"
      >
        <div class="header-menu-item">{{ item.title }}</div>
      </router-link>
    </div>

    <div id="headerBtnGroup" v-if="!isLoggedIn">
      <router-link to="/home/login" class="header-routerlink-decoration">
        <div class="header-btn">
          <div class="header-btn-text">
            login
          </div>
        </div>
      </router-link>
      <router-link to="/home/signup" class="header-routerlink-decoration">
        <div class="header-btn" style="background-color:#653FD3;">
          <div class="header-btn-text" style="color:white">
            sign up
          </div>
        </div>
      </router-link>
    </div>
    <div class="header-btngroup" v-else>
      <div>
        <router-link to="/home/mypage">
          <img :src="imagesrc" alt="" >
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, reactive } from "@vue/runtime-core";
import { useStore } from "vuex";
import { mapGetters } from "vuex";
export default {
  name: "header",
  computed:{
    ...mapGetters(['isLoggedIn'])
  },
  data() {
    return {
      imagesrc: require("../../assets/images/ssafy-logo.png")
    };
  },
  setup() {
    const store = useStore();
    const state = reactive({
      headeritems: computed(() => {
        const headeritems = store.getters["root/getMenus"];
        console.log(headeritems + "?");
        let keys = Object.keys(headeritems);
        console.log(keys + "!");
        let headmenu = [];
        for (var i = 0; i < keys.length; i++) {
          let menuobject = {};
          menuobject.path = headeritems[keys[i]].path;
          menuobject.title = headeritems[keys[i]].name;
          headmenu.push(menuobject);
        }
        return headmenu;
      })
    });
    return { state };
  }
};
</script>

<style>
.header-btngroup{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
  gap: 16px;
  order: 2;
}
</style>
