import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'
import { OpenVidu } from 'openvidu-browser'; 


export default {
  state: {
    interviewUser : ''
  },
  getters: {
    interviewUser : state => state.interviewUser
  },
  mutations: {
    SET_INTERVIEW_USER : (state, interviewUser)=>
      (state.interviewUser = interviewUser)
  },
  actions: {
    chageInterviewUser({ commit },interviewUser){
      commit('SET_INTERVIEW_USER', interviewUser)
    }


	}
}
