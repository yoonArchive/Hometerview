<template>
  <div id="mainBackground" class="container" ref="mainBackground">
    <div id="mainDivLogo">
      <img :src="logo" id="mainImageLogo" />
    </div>

    <div>
      <img :src="images[0]" alt="" class="main-image" />
    </div>
    <router-link to="/home">
      <button id="mainBtn">홈페이지 바로가기</button>
    </router-link>
    <div>
      <img :src="images[1]" alt="" class="main-image" />
    </div>
    <div>
      <img :src="images[2]" alt="" class="main-image" />
    </div>
    <div>
      <img :src="images[3]" alt="" class="main-image" />
    </div>
    <div>
      <img :src="images[4]" alt="" class="main-image" />
    </div>

    <login-dialog
      :open="loginDialogOpen"
      @closeLoginDialog="onCloseLoginDialog"
    />
  </div>



</template>
<style>
@import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
@import "./main.css";
@import "../../common/css/common.css";
@import "../../common/css/element-plus.css";
</style>
<script>
import LoginDialog from "./components/login-dialog";
import MainHeader from "./components/main-header";
import MainSidebar from "./components/main-sidebar";
import MainFooter from "./components/main-footer";

// window.addEventListener('scroll', () => {

//   const [red, green, blue] = [101, 63, 211]
//   const section1 = document.querySelector(".mainBackground");
//   let y = 1 + (window.scrollY || window.pageYOffset) / 5000
//   y = y < 1 ? 1 : y // ensure y is always >= 1 (due to Safari's elastic scroll)
//   const [r, g, b] = [red*y, green/y, blue*y]
//   section1.style.background = `rgb(${r}, ${g}, ${b})`



export default {
  name: "Main",
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog
  },

  data() {
    return {
      loginDialogOpen: false,
      images: [
        require("../../assets/images/main1.png"),
        require("../../assets/images/main2.png"),
        require("../../assets/images/main-1.png"),
        require("../../assets/images/main-2.png"),
        require("../../assets/images/main-3.png")
      ],
      logo: require("../../assets/images/ssafy-logo.png")
    };
  },
  methods: {
    onOpenLoginDialog() {
      this.loginDialogOpen = true;
    },
    onCloseLoginDialog() {
      this.loginDialogOpen = false;
    }
  },
  created() {},
  destroyed() {},
  mounted() {}
};
</script>
<style scoped>

.middle-1{
  /* padding: 10px; */
  justify-content: right;
  /* object-fit: cover; */
  margin-top: 100px;

  /* overflow: hidden; */
  display: flex;
  align-items: center;
  justify-content: center;


}
.middle-2{
  /* padding: 10px; */
  /* justify-content: left; */
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 100px;

}
#mainBackground{
  background-color: #653fd3;
  width: 100%;


}
.container {
  display: flex;
  flex-direction: column;
}
.main-image {
  justify-content: center;
  width: 100%;
}
section {
  height: 100vh;
}
#mainBtn {
  background-color: orange;
  border-color: orange;
  border-radius: 10%;
  color: white;
  margin: 15px;
  width: 150px;
}

#middle-img{
  display: flex;
  flex-direction: column;

}
</style>
