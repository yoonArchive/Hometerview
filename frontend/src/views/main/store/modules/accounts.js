import axios from 'axios'
import api_url from '@/api/api_url'
import http from '@/api/api_url'
import router from "@/common/lib/vue-router.js";

export default {

  state: () => ({
    token: localStorage.getItem('token') || '' ,
    currentUser: {},
    profile: {},
    authError: null,
    isDuplicatedEmail: true,
    isDuplicatedId: true,
    isPasswordConfirm: false,
  }),

  mutations:{
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_CHECK_EMAIL: (state, isDuplicatedEmail) => state.isDuplicatedEmail = isDuplicatedEmail,
    SET_CHECK_ID: (state, isDuplicatedId) => state.isDuplicatedId = isDuplicatedId,
    SET_PASSWORD_CONFIRM: (state, ispasswordconfirm) => state.isPasswordConfirm = ispasswordconfirm,


    CLEER_CURRENT_USER : (state) => state.currentUser = {},
  },
  getters:{
    isValidedEmail : state => state.isValidedEmail,
    isDuplicatedEmail : state => state.isDuplicatedEmail,
    isDuplicatedId : state => state.isDuplicatedId,
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    isPasswordConfirm: state=> state.isPasswordConfirm,
  },
  actions:{


    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
    },

    removeToken({ commit }) {
      commit('SET_TOKEN', '')
      localStorage.setItem('token', '')
    },
    passwordConfirm({commit, getters}, credentials){
      axios
      .get(api_url.accounts.passwordConfirm(),{
        params : credentials,
        headers : getters.authHeader,
      }).then(()=>{
        commit('SET_PASSWORD_CONFIRM',true);
        router.push({name:'mypage'})
      }).catch(()=>{
        alert('인증에 실패했습니다.')
      })
    },

    deleteUser({dispatch, getters}){
      axios.delete(api_url.accounts.deleteUser(), {
        headers: getters.authHeader,
      }).then(()=>{
        dispatch('logout');
        router.push({name:'main'});
      }).catch(err=>{
        console.log(err);
      })
    },

    updateUser({dispatch, getters}, credentials){
      console.log(credentials);
      const updateUserPutReq = {
        userEmail:credentials.userEmail,
        userImg:credentials.userImg,
        userName:credentials.userName,
      }
      axios.put(api_url.accounts.updateUser(), updateUserPutReq ,{
        headers: getters.authHeader,
      }).then(data=>{
        console.log(data);
        dispatch('logout');
      }).catch(err=>{
        console.log(err)
      })
    },


    login({ commit, dispatch }, credentials) {

      axios({
        url: api_url.accounts.login(),
        method: 'post',
        data: credentials
      })
        .then(res => {
          console.log(res);
          const token = res.data.accessToken;
          console.log('로그인성공')
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'home' })
        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
          alert('로그인 실패')
        })
    },
    logout({ getters, dispatch, commit }) {
      if(getters.isLoggedIn){
        dispatch('removeToken')
        alert('성공적으로 logout!')
        commit('CLEER_CURRENT_USER');
        commit('SET_PASSWORD_CONFIRM',false);
        router.push({ name: 'login' })
      }
      else{
        alert('로그인을 진행해 주세요')
      }
    },
    signup({ commit }, credentials) {
    delete credentials.userPw2
    // const credentialsForLogin = {
    //   userId : credentials.userId,
    //   userPw : credentials.userPw
    // }
      axios({
        url: api_url.accounts.signup(),
        method: 'post',
        data: credentials,
      })
        .then(res => {
          console.log(res.data)
          // const token = res.data.key
          // dispatch('saveToken', token)
          // dispatch('fetchCurrentUser')
          alert('회원가입 성공')

          // 중복검사 초기화 : 뒤로가기 하면 false가 그대로이기 떄문
          commit('SET_CHECK_EMAIL', true)
          commit('SET_CHECK_ID', true)
          router.push({ name: 'login' })

        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
          alert('회원가입 실패')
        })
        // dispatch('login', credentialsForLogin)
    },


    async fetchCurrentUser({ commit, getters, dispatch }) {
      /*
      GET: 사용자가 로그인 했다면(토큰이 있다면)
        currentUserInfo URL로 요청보내기
          성공하면
            state.cuurentUser에 저장
          실패하면(토큰이 잘못되었다면)
            기존 토큰 삭제
            LoginView로 이동
      */
      if (getters.isLoggedIn) {
        await axios({
          url: api_url.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader,
        }).then(res =>{
          console.log(res);
          const tempuser = {
            userEmail : res.data.userEmail,
            userImg : res.data.userImg,
            userId : res.data.userId,
            userName : res.data.userName,
          }
          console.log(tempuser);
          commit('SET_CURRENT_USER', tempuser);

        })
          .catch(err => {
            if (err.response.status === 401) {
              dispatch('removeToken')
              router.push({ name: 'login' })
            }
          })
      }
    },
    findUserid({ commit },credentials){

      const splitedEmail = credentials.userEmail.split('@')
      const emailId = splitedEmail[0]
      const emailaddress = splitedEmail[1]
      const emailAndUserNameForSubmit = `?userEmail=${emailId}%40${emailaddress}&userName=${credentials.userName}`
      axios({
        url:api_url.accounts.findUserid() + emailAndUserNameForSubmit,
        method: 'get',
      })
        .then(res=>{
          const userId = res.data.userId
          console.log(userId)
          alert(`당신의 ID는 ${userId} 입니다`)
        })
        .catch(err=>{
          console.error(err.response.data)
          alert(err.response.data.message)
          commit('SET_AUTH_ERROR',err.response.data)
        })
    },
    emailDuplicateCheck({commit}, email){
      // 요청 => 성공 => true를 보냄
      // 요청 => 실패 => defalt false값이 될 것임
      const splitedEmail = email.split('@')
      const emailId = splitedEmail[0]
      const emailaddress = splitedEmail[1]
      const emailForSubmit = `?email=${emailId}%40${emailaddress}`

      axios({
        url: api_url.accounts.emailDuplicateCheck() + emailForSubmit,
        method : 'get',
      })
        .then(res => {
          // 응답에 성공을 하면
          console.log(res.data)
          commit('SET_CHECK_EMAIL', false)
          alert('사용가능한 이메일 입니다')
        })
        .catch(err => {
          console.log(err.response)
          commit('SET_CHECK_EMAIL', true)
          alert('사용중인 이메일 입니다')
        })
    },
    idDuplicateCheck({commit, getters}, id){

      const idForSubmit = `?id=${id}`
      axios({
        url: api_url.accounts.idDuplicateCheck() + idForSubmit,
        method : 'get',
      })
        .then(res => {
          // 응답에 성공을 하면
          console.log(res.data)
          commit('SET_CHECK_ID', false)
          console.log(getters.isDuplicatedId)
          alert('사용가능한 아이디입니다')
        })
        .catch(err => {
          console.log(err.response)
          commit('SET_CHECK_ID', true)
          console.log(getters.isDuplicatedId)
          alert('사용중인 아이디 입니다')
        })
    },
    },

    changePassword({getters}, credentials){
      axios({
        url:api_url.accounts.changepassword(),
        method:'put',
        data: {
          userPw:credentials.userPw,
          newPw : credentials.newPw,
        },
        headers : getters.authHeader
      }).then(data =>{
        console.log(data);
      }).catch(err=>{
        console.log(credentials);
        console.log(err);
      })
    }
  }

