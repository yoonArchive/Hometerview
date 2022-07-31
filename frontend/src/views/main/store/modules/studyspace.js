import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'
import accounts from '@/views/main/store/modules/accounts'
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    studySpaceList : [],
    studySpaceDetail : {},
    token: localStorage.getItem('token') || '' ,
  },

  getters: {
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    studySpaceList : state => state.studySpaceList,
    studySpaceDetail : state => state.recruitDetail,
  },

  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_RECRUITMENT_LIST: (state,studySpaceList) => state.studySpaceList = studySpaceList,
    SET_RECRUIT_DETAIL : (state,recruitDetail) => state.recruitDetail = recruitDetail,
  },

  actions: {
    createStudySpace({commit, state},recruitNo){

      const recruitNoForURL = `?recruitNo=${recruitNo}`
      console.log(api_url.study.studyspace() + recruitNoForURL,)
      axios({
        url: api_url.study.studyspace() + recruitNoForURL,
        method: 'post'
      })
      .then(res => {
        console.log(res.data)
      })
      .catch(err => {
        console.log(err.response)
        alert('이미 스터디를 시작하셨습니다.')
      })
    },
    bringStudySpace({commit,getters}){
      axios({
        url: api_url.study.studyspace(),
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        
        commit('SET_RECRUITMENT_LIST',res.data.studies )
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    bringStudySpaceDetial({commit},stdNo){
      axios({
        url:api_url.study.studyspacedetail(stdNo),
        method : 'get'
      })
      .then(res=>{
        console.log(res.data)
        commit('SET_RECRUIT_DETAIL',res.data)
      })
      .catch(err=>{
        console.log(err.response)
      })
    }


  },
}
