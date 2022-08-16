<template>
  <nav class="navbar">
    <div class="navbar__logo col-md-1">
      <router-link to="/home" class="header-routerlink-decoration">
        <img
          id="headerLogo"
          class="logo"
          :src="imagesrc"
          alt=""
          style="padding-left:2%;"
        />
      </router-link>
    </div>
    <div class="col-md-7">
      <ul class="navbar__menu">
        <router-link
          :to="item.path"
          v-for="(item, index) in state.headeritems"
          :key="index"
          :index="index.toString()"
          class="header-routerlink-decoration"
        >
          <li id="title">{{ item.title }}</li>
        </router-link>
      </ul>
    </div>
    <div class="account-menu col">
      <div class="d-flex flex-row-reverse">
        <div v-if="isLoggedIn" class="dropdown">
          <img class="profile-img" :src="profile" alt="" />
          <button
            type="button"
            class="btn  dropdown-toggle"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <span>{{ currentUser.userName }}</span
            >님 오늘도 화이팅하세요
          </button>
          <ul class="dropdown-menu dropdown-menu-end" style="">
            <router-link to="/home/mypage">
              <li>
                <button class="dropdown-item" type="button">
                  <span class="dropdown-title">마이페이지</span>
                </button>
              </li>
            </router-link>
            <router-link to="/home/logout" id="signup">
              <li>
                <button class="dropdown-item" type="button">
                  <span class="dropdown-title">로그아웃</span>
                </button>
              </li>
            </router-link>
          </ul>
        </div>
        <div v-else>
          <router-link to="/home/login" class="header-routerlink-decoration">
            <span class="account-title">로그인</span>
          </router-link>
          <router-link to="/home/signup" class="header-routerlink-decoration">
            <span class="account-title">회원가입</span>
          </router-link>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { computed, reactive } from "@vue/runtime-core";
import { useStore } from "vuex";
import { mapGetters, mapActions } from "vuex";
export default {
  name: "header",
  data() {
    return {
      imagesrc: require("../../../assets/images/hometerview.png"),
      profile: "",
      isLoggedIn: computed(() => store.getters.isLoggedIn)
    };
  },
  computed: {
    ...mapGetters(["isLoggedIn", "currentUser"])
  },
  methods: {
    ...mapActions(["updateUser", "fetchCurrentUser"]),
    async refreshsetting() {
      await this.fetchCurrentUser();
      console.log(currentUser);
      // await this.setting();

      // this.profile = this.currentUser.userImg;
    }
  },
  mounted() {
    this.refreshsetting();
    this.profile = this.currentUser.userImg;
  },
  created() {
    this.refreshsetting();
    this.profile = this.currentUser.userImg;
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
span {
  font-weight: bold;
}
a {
  text-decoration: none;
  color: black;
}
.navbar {
  display: flex;
  /* justify-content: left; */
  /* margin-left: 20px; */
  align-items: center;
  background-color: rgba(247, 247, 247, 0.589);
  border-radius: 30px;
  padding: 8px 12px;
  font-weight: bold;
  font-size: 20px;
}
.navbar__menu {
  display: flex;
  list-style: none;
  padding-left: 0;
  margin-left: 35px;
  /* margin-right: 1050px; */
}
.navbar__menu li {
  padding: 1px 25px;
  margin-top: 15px;
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
.logo {
  height: 70px;
  margin-left: 10px;
}
.profile-img {
  height: 40px;
  margin-left: 30px;
}
.route-active #title {
  color: #653fd3;
}
.dropdown {
  right: 10;
}
.dropdown-menu {
  top: 90%;
  /* margin-right: 30px; */
}
.dropdown-toggle {
  border: 0;
}
.dropdown-title {
  font-size: 17px;
}
.account-title {
  font-size: 15px;
  color: rgb(85, 85, 85);
  margin-left: 10px;
  margin-right: 20px;
}
</style>
