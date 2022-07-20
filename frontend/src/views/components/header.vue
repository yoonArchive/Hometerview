<template>
  <div id="headerFrame">
    <img id="headerLogo" :src="imagesrc" alt=""/>
    <div id="headerMenu">
      <router-link :to="item.path" v-for="(item,index) in state.headeritems" :key="index" :index="index.toString()" class="header-routerlink-decoration">
        <div class="header-menu-item">{{ item.title }}</div>
      </router-link>
    </div>
    <div id="headerBtnGroup">
      <div class="header-btn">
        <div class="header-btn-text">
          login
        </div>
      </div>
      <div class="header-btn" style="background-color:#653FD3;">
        <div class="header-btn-text" style="color:white">
          sign up
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, reactive } from '@vue/runtime-core'
import { useStore } from 'vuex'
export default {
  name:'header',
  data(){
    return{
      imagesrc:require('../../assets/images/ssafy-logo.png'),
    }
  },
  setup(){
    const store = useStore();
    const state = reactive({
      headeritems: computed(()=>{
        const headeritems = store.getters['root/getMenus'];
        console.log(headeritems + '?');
        let keys = Object.keys(headeritems);
        console.log(keys + "!")
        let headmenu = [];
        for(var i =0;i<keys.length;i++){
          let menuobject = {};
          menuobject.path = headeritems[keys[i]].path
          menuobject.title = headeritems[keys[i]].name
          headmenu.push(menuobject);
        }
        return headmenu;
      })
    });
    return { state }
  }

}
</script>

<style>

</style>
