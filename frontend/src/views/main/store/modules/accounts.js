import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'

export default {

  state: () => ({
    token: localStorage.getItem('token') || '' ,
    currentUser: {},
    profile: {},
    authError: null,
    isDuplicatedEmail: true,
    isDuplicatedId: true,

  }),

  mutations:{
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_CHECK_EMAIL: (state, isDuplicatedEmail) => state.isDuplicatedEmail = isDuplicatedEmail,
    SET_CHECK_ID: (state, isDuplicatedId) => state.isDuplicatedId = isDuplicatedId,
    


  },
  getters:{
    isValidedEmail : state => state.isValidedEmail,
    isDuplicatedEmail : state => state.isDuplicatedEmail,
    isDuplicatedId : state => state.isDuplicatedId,
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`})
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
    logout({ getters, dispatch }) {
      if(getters.isLoggedIn){
        dispatch('removeToken')
        alert('성공적으로 logout!')
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
    fetchCurrentUser({ commit, getters, dispatch }) {
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
        axios({
          url: api_url.accounts.currentUserInfo(),
          method: 'get',
          headers: getters.authHeader,
        })
          .then(res => commit('SET_CURRENT_USER', res.data))
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

