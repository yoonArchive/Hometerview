import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'


export default {

  state: {
    ovToken : '' //64 인코딩해야할지도
  },
  getters: {
    openViduHeader: state => ({ 
      Authorization: `Basic ${state.ovToken}`,
      'Content-Type': 'application/json'
    }),
  },
  mutations: {
    SET_OV_TOKEN : (state,ovToken) => state.ovToken = ovToken

  },
  actions: {
    createSession({getters}){
      axios({
        url: api_url.session.createSession(),
        method:'post',
        headers: getters.openViduHeader
      })
      .then((res)=>{
        console.log(res.data)
      })
      .catch((err)=>{
        console.log(err);
      })
    },

  },
}
