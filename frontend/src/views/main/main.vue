<template>
  <div id="mainBackground" class="mainBackground" ref="mainBackground">

    <div  id="mainDivLogo">
      <img :src="logo" id="mainImageLogo"/>
    </div>



    <img :src="images[0]" alt="" class="main-image" />

      <router-link to="/home">
        <button id="mainBtn">홈페이지 바로가기

        </button>
      </router-link>
    <img :src="images[1]" alt="" class="main-image" />
    <img :src="images[2]" alt="" class="main-image" />

  </div>
  <login-dialog
    :open="loginDialogOpen"
    @closeLoginDialog="onCloseLoginDialog"/>
</template>
<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';

</style>
<script>
import LoginDialog from './components/login-dialog'
import MainHeader from './components/main-header'
import MainSidebar from './components/main-sidebar'
import MainFooter from './components/main-footer'





window.addEventListener('scroll', () => {

  const [red, green, blue] = [101, 63, 211]
  const section1 = document.querySelector(".mainBackground");
  let y = 1 + (window.scrollY || window.pageYOffset) / 2500
  y = y < 1 ? 1 : y // ensure y is always >= 1 (due to Safari's elastic scroll)
  const [r, g, b] = [red*y, green/y, blue*y]
  section1.style.background = `rgb(${r}, ${g}, ${b})`

})
export default {
  name: 'Main',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog
  },



  data () {
    return {
      loginDialogOpen: false,
      images:[ require('../../assets/images/sample-image.png'), require('../../assets/images/ssafy-logo.png'),require('../../assets/images/sample-image.png')],
      logo: require('../../assets/images/ssafy-logo.png'),
    }
  },
  methods: {
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },

  },
  created() {

  },
  destroyed () {

  },
    mounted() {

  },


}


</script>
<style scoped>
.mainBackground{
  background-color: #653fd3;
}

section {
  height: 100vh;
}
</style>
