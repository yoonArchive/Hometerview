import axios from 'axios'
import api_url from '@/api/api_url'
import router from "@/common/lib/vue-router.js";

export default {

  state: {
    recruitmentList : [],
    recruitDetail : {}

  },
  mutations: {
    SET_RECRUITMENT_LIST: (state,recruitmentList) => state.recruitmentList = recruitmentList,
    SET_RECRUIT_DETAIL : (state,recruitDetail) => state.recruitDetail = recruitDetail
  },
  getters:{
    isValidedEmail : state => state.isValidedEmail,
    recruitmentList : state => state.recruitmentList,
    recruitDetail : state => state.recruitDetail
  },
  actions:{
    createRecruitment({},recruitmentInfo){
      console.log(recruitmentInfo.stdType)
      axios({
        url : api_url.membersrecruitment.membersrecruitments(),
        method : 'post',
        data : recruitmentInfo,
      })
      .then(res => {
        console.log(res.data)
        alert('성공하셨습니다.')
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    bringRecruitmentList({ commit }){
      axios({
        url : api_url.membersrecruitment.membersrecruitments(),
        method : 'get',
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUITMENT_LIST',res.data.recruits)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    bringRecruitmentDetail({ commit }, recruitNo){
      axios({
        url : api_url.membersrecruitment.membersrecruitment(recruitNo),
        method : 'get',
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUIT_DETAIL',res.data)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    updateRecruitmentDetail({ commit }, recruitInfo){
      const recruitNo = recruitInfo[0]
      const recruitmentInfo = recruitInfo[1]
      console.log(1)
      console.log(recruitmentInfo)
      axios({
        url : api_url.membersrecruitment.membersrecruitment(recruitNo),
        method : 'put',
        data : recruitmentInfo

      })
      .then(res => {
        console.log(2)
        console.log(res.data)
        commit('SET_RECRUIT_DETAIL',res.data)
        
        router.push({ 
          name: 'membersrecruitmentdetail',
          params : {recruitNo:recruitNo}
        })
      })
      .catch(err => {
        console.log(err.response)
      })
    },
  }
}
