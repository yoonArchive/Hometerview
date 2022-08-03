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
        <div class="header-btn" >
          <!-- <div class="header-btn-text"> -->
            로그인
          <!-- </div> -->
        </div>
      </router-link>
      <router-link to="/home/signup" class="header-routerlink-decoration">
        <div class="header-btn" v-if="!isLoggedIn" id="signup">
          <!-- <div class="header-btn-text" style="color:white"> -->
            회원가입
          <!-- </div> -->
        </div>
      </router-link>
    </div>
    <div class="header-btngroup" v-else>
      <router-link to="/home/logout" class="header-routerlink-decoration" >
        <div class="header-btn" v-if="isLoggedIn" id="signup">
          <!-- <div class="header-btn-text"> -->
            로그아웃
          <!-- </div> -->
        </div>
      </router-link>
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
    return {
      state,
      isLoggedIn: computed(() => store.getters.isLoggedIn)
    };
  }
};
</script>

<style>
#signup{
  color: white;
  background-color: #653FD3;
}
#signup:hover{
  background-color: #9779f0;
}
#signup:active{
  background-color:#7455d1;
}
.header-btn{
  color: black;
  width: 100px;
  /* height: 50px; */
  font-weight: bold;

}
.header-btn:hover{
  background-color: #653FD3;
  color: white;
}
.header-btn:active{
  background-color:#7455d1;
  color: white;
}

.header-btngroup{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
  gap: 16px;
  order: 2;
  color: black;

}

</style>
