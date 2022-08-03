import axios from 'axios'
import api_url from '@/api/api_url'
import router from "@/common/lib/vue-router.js";

export default {

  state: {
    recruitmentList : [],
    recruitDetail : {},
    token: localStorage.getItem('token') || '' ,
    isApplied : false,
    applyType : ''
    

  },
  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_RECRUITMENT_LIST: (state,recruitmentList) => state.recruitmentList = recruitmentList,
    SET_RECRUIT_DETAIL : (state,recruitDetail) => state.recruitDetail = recruitDetail,
    SET_APPLY_TYPE : (state, applyType) => state.applyType = applyType,
    
  },
  getters:{
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    isValidedEmail : state => state.isValidedEmail,
    recruitmentList : state => state.recruitmentList,
    recruitDetail : state => state.recruitDetail,
    applyType : state => state.applyType,
    recruitCount : state => state.recruitDetail.count
  },
  actions:{
    createRecruitment({getters},recruitmentInfo){
      console.log(recruitmentInfo)
      console.log(getters.authHeader)
      axios({
        url : api_url.membersrecruitment.membersrecruitments(),
        method : 'post',
        data : recruitmentInfo,
        headers : getters.authHeader
      })
      .then(res => {
        console.log(res.data)
        alert('성공하셨습니다.')
        router.push({name:'membersrecruitment'})
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
    bringRecruitmentDetail({ commit, getters }, recruitNo){
      axios({
        url : api_url.membersrecruitment.membersrecruitment(recruitNo),
        method : 'get',
        headers : getters.authHeader
      })
      .then(res => {
        console.log(res.data)
        console.log(res.data.applyType)
        commit('SET_RECRUIT_DETAIL',res.data)
        commit('SET_APPLY_TYPE',res.data.applyType)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    updateRecruitmentDetail({ commit }, recruitInfo){
      const recruitNo = recruitInfo[0]
      const recruitmentInfo = recruitInfo[1]
      axios({
        url : api_url.membersrecruitment.membersrecruitment(recruitNo),
        method : 'put',
        data : recruitmentInfo

      })
      .then(res => {
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
    deleteRecruitmentDetail({commit},recruitNo){
      
      axios({
        url : api_url.membersrecruitment.membersrecruitment(recruitNo),
        method : 'delete',
      })
      .then(res => {
        console.log(res.data)
        router.push({ 
          name: 'membersrecruitment',
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    bringRecruitingList({ commit }){
      axios({
        url : api_url.membersrecruitment.membersrecruiting(),
        method : 'get'
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUITMENT_LIST',res.data.recruits)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    bringRecruitSearchList({ commit },recruitSearchKeyword){
      console.log(recruitSearchKeyword)
      const encodeSearchKeyword = `?keyword=${encodeURIComponent(recruitSearchKeyword)}`

      axios({
        url : api_url.membersrecruitment.membersrecruitsearch()+encodeSearchKeyword,
        method : 'get'
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUITMENT_LIST',res.data.recruits)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    bringRecruitTypeList({ commit },recruitType){
      console.log(recruitType)

      axios({
        url : api_url.membersrecruitment.membersrecruittype() + `?type=${recruitType}`,
        method : 'get'
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUITMENT_LIST',res.data.recruits)
      })
      .catch(err => {
        console.log(err.response)
      })
    },
    studyApply({commit,getters},recruitNo){
      console.log(api_url.membersrecruitment.studyApply(recruitNo),)
      axios({
        url : api_url.membersrecruitment.studyApply(recruitNo),
        method :'post',
        headers: getters.authHeader
      })
      .then(res=>{
        console.log(res.data)
        commit('SET_APPLY_TYPE','NORMAL')
      })
      .catch(err=>{
        console.log(err.response)
      })
    },
    studyApplyCancel({commit,getters},recruitNo){
      axios({
        url : api_url.membersrecruitment.studyApply(recruitNo),
        method :'delete',
        headers: getters.authHeader
      })
      .then(res=>{
        console.log(res.data)
        commit('SET_APPLY_TYPE',null)
      })
      .catch(err=>{
        console.log(err.response)
      })
    },
  }
}
