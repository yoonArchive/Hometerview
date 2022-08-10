<template>
  <nav class="navbar">
    <div class="navbar__logo">
      <!-- <i class="fab fa-apple"></i>
      <a href="">민브로 코딩</a> -->
      <router-link to="/home" class="header-routerlink-decoration">
        <img id="headerLogo" :src="imagesrc" alt="" style="padding-left:2%;" />
      </router-link>
    </div>
    <ul class="navbar__menu">
      <!-- <li><a href="">홈</a></li>
      <li><a href="">핫딜</a></li>
      <li><a href="">포럼</a></li>
      <li><a href="">FAQ</a></li>
      <li><a href="">채용</a></li> -->
      <router-link
        :to="item.path"
        v-for="(item, index) in state.headeritems"
        :key="index"
        :index="index.toString()"
        class="header-routerlink-decoration"
      >
        <!-- <div class="header-menu-item">{{ item.title }}</div> -->
        <li>{{ item.title }}</li>
      </router-link>
    </ul>
    <ul class="navbar__links" v-if="!isLoggedIn">
      <!-- <li><i class="fab fa-facebook-square"></i></li>
      <li><i class="fab fa-instagram"></i></li> -->
      <router-link to="/home/login" class="header-routerlink-decoration">
        <div class="header-btn">
          로그인
        </div>
      </router-link>
      <router-link to="/home/signup" class="header-routerlink-decoration">
        <div class="header-btn" v-if="!isLoggedIn" id="signup">
          회원가입
        </div>
      </router-link>
    </ul>
    <ul class="navbar__links" v-else>
      <!-- <li><i class="fab fa-facebook-square"></i></li>
      <li><i class="fab fa-instagram"></i></li> -->
      <router-link to="/home/logout" class="header-routerlink-decoration">
        <div class="header-btn" v-if="isLoggedIn" id="signup">
          로그아웃
        </div>
      </router-link>
      <div>
        <router-link to="/home/mypage">
          <img id="headerLogo" :src="imagesrc" alt="" />
        </router-link>
      </div>
    </ul>

    <a href="#" class="navbar__toggleBtn"><i class="fas fa-bars"></i></a>
  </nav>
  <!-- <div id="headerFrame" class="row">
    <div class="col-md-1">
      <router-link to="/home" class="header-routerlink-decoration">
        <img id="headerLogo" :src="imagesrc" alt="" style="padding-left:2%;" />
      </router-link>
    </div>
    <div id="headerMenu" class="col-md-3">
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
    <div id="headerBtnGroup" v-if="!isLoggedIn" class="col-md-7">
      <router-link to="/home/login" class="header-routerlink-decoration">
        <div class="header-btn">
          로그인
        </div>
      </router-link>
      <router-link to="/home/signup" class="header-routerlink-decoration">
        <div class="header-btn" v-if="!isLoggedIn" id="signup">
          회원가입
        </div>
      </router-link>
    </div>
    <div class="header-btngroup col-md-1" v-else>
      <router-link to="/home/logout" class="header-routerlink-decoration">
        <div class="header-btn" v-if="isLoggedIn" id="signup">
          로그아웃
        </div>
      </router-link>
      <div>
        <router-link to="/home/mypage">
          <img id="headerLogo" :src="imagesrc" alt="" />
        </router-link>
      </div>
    </div>
  </div> -->
</template>

<script>
import { computed, reactive } from "@vue/runtime-core";
import { useStore } from "vuex";
import { mapGetters } from "vuex";
export default {
  name: "header",
  computed: {
    ...mapGetters(["isLoggedIn"])
  },
  data() {
    return {
      imagesrc: require("../../../assets/images/hometerview.png")
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

<style scoped>
a {
  text-decoration: none;
  color: black;
}

.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgba(247, 247, 247, 0.589);
  border-radius: 30px;
  padding: 8px 12px;
  font-family: "티머니 둥근바람 볼드";
  font-size: 20px;
  /* height: 5%; */
}

.navbar__menu {
  display: flex;
  list-style: none;
  padding-left: 0;
}

.navbar__menu li {
  padding: 1px 13px;
}
.navbar__menu li:hover {
  background-color: rgb(206, 206, 206);
  border-radius: 4px;
}

.navbar__links {
  list-style: none;
  padding-left: 0;
  color: rgb(255, 255, 255);
  display: flex;
}

.navbar__links li {
  padding: 8px 12px;
}

.navbar__toggleBtn {
  display: none;
  position: absolute;
  right: 32px;
  font-size: 24px;
}

@media screen and (max-width: 600px) {
  .navbar {
    position: fixed;
    flex-direction: column;
    align-items: flex-start;
    padding: 8px 24px;
  }
  .navbar__menu {
    display: none;
    flex-direction: column;
    /* align-items: center; */
    width: 100%;
  }
  .navbar__menu li {
    width: 100%;
    text-align: center;
  }
  .navbar__links {
    display: none;
    justify-content: center;
    width: 100%;
  }
  .navbar__toggleBtn {
    display: block;
  }

  .navbar__menu.active,
  .navbar__links.active {
    display: flex;
  }
}
.header-btn {
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  border-radius: 4px;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  background-color: #ffffff;
  color: #653fd3;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 700;
  height: 3em;
  width: 6em;
  letter-spacing: 0.1em;
  text-align: center;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  /* margin-left: 10px; */
  margin-right: 30px;
  font-family: "티머니 둥근바람 볼드";
}
.header-btn:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
.header-btn:active {
  background-color: rgba(161, 104, 253, 0.15);
}
img {
  height: 60px;
}
/* #signup {
  color: white;
  background-color: #653fd3;
}
#signup:hover {
  background-color: #9779f0;
}
#signup:active {
  background-color: #7455d1;
}
.header-btn {
  color: black;
  width: 100px;
  font-weight: bold;
}
.header-btn:hover {
  background-color: #653fd3;
  color: white;
}
.header-btn:active {
  background-color: #7455d1;
  color: white;
}
.header-btngroup {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px;
  gap: 16px;
  order: 2;
  color: black;
}
img {
  width: 100%;
} */
</style>
