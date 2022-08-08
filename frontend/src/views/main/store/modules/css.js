import axios from 'axios'
import api_url from '@/api/api_url'
import router from '../../../../common/lib/vue-router'


export default {
  // namespaced: true,
  state: {
    maincontainer:'main-container',
    fullcontainer:'full-container'
  },

  getters: {
    fullcontainer: state=> state.fullcontainer,
    maincontainer:state => state.maincontainer,

  },

  mutations: {
    SET_MAIN_CONTAINER: (state,data)=>state.maincontainer = data,
    SET_FULL_CONTAINER: (state,data)=>state.fullcontainer = data,
  },

  actions: {

  },
}
