import axios from 'axios'
import api_url from '@/api/api_url'
import http from '@/api/api_url'
import router from "@/common/lib/vue-router.js";

import {state} from './accounts'

export default {
  // namespaced: true,
  state: {
    numberOfResume : 0,
    resumeContents : [],
    currentResume: {
    },

  },

  getters: {
    currentResume: state => state.currentResume,
    resumeContents: state => state.resumeContents,
    numberOfResume : state => state.numberOfResume,

  },

  mutations: {
    SET_RESUME_CONTENTS(state, data){
      state.resumeContents = data;
    },
    SET_CURRENT_RESUME(state, data){
      state.currentResume = data
    },
    SET_NUMBER_RESUME(state, data){
      state.numberOfResume = data;
    }
  },

  actions: {
    getResumeInfo({commit, rootGetters, state}){
      axios.get(api_url.resumes.getResumeInfo(), {
        headers : rootGetters.authHeader,
      }).then((data)=>{
        console.log(data.data.resumes);

        commit('SET_RESUME_CONTENTS', data.data.resumes);
        commit('SET_NUMBER_RESUME', data.data.resumes.length);
      }).catch((err)=>{
        console.log(err);
      })
    },
    getCurrentResume({commit , rootGetters}, credentials){
      axios.get(api_url.resume.getResumeDetail(),{
        params : credentials,
        headers : rootGetters.authHeader,
      }).then((data)=>{
        const res = {
          question : data.item,
          answer : data.answer,
        }
        commit('SET_CURRENT_RESUME',res);
      }).catch((err)=>{
        console.log(err);
      })
    }

  },
}
