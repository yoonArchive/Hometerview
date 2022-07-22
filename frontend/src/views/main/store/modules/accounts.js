import axios from 'axios'
import api_url from '@/api/api_url'


export default {

  state: () => ({
    token: localStorage.getItem('token') || '' ,
    currentUser: {},
    profile: {},
    authError: null,
  }),

  mutations:{
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error

  },
  getters:{
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
    authHeader: state => ({ Authorization: `Token ${state.token}`})
  },
  actions:{
    saveToken({ commit }, token) {
      /*
      state.token 추가
      localStorage에 token 추가
      */
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
    },

    removeToken({ commit }) {
      /*
      state.token 삭제
      localStorage에 token 추가
      */
      commit('SET_TOKEN', '')
      localStorage.setItem('token', '')
    },

    login({ commit, dispatch }, credentials) {
      /*
      POST: 사용자 입력정보를 login URL로 보내기
        성공하면
          응답 토큰 저장
          현재 사용자 정보 받기
          메인 페이지(ArticleListView)로 이동
        실패하면
          에러 메시지 표시
      */
      axios({
        url: api_url.accounts.login(),  // 수정 필요할지 확인
        method: 'post',
        data: credentials
      })
        .then(res => {
          const token = res.data.key
          console.log('로그인성공')
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          // router.push({ name: 'articles' })
        })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
          alert('로그인 실패')
        })
    },

    logout({ getters, dispatch }) {
      /*
      POST: token을 logout URL로 보내기
        성공하면
          토큰 삭제
          사용자 알람
          LoginView로 이동
        실패하면
          에러 메시지 표시
      */
      axios({
        url: api_url.accounts.logout(),
        method: 'post',
        headers: getters.authHeader,
      })
        .then(() => {
          dispatch('removeToken')
          alert('성공적으로 logout!')
          router.push({ name: 'login' })
        })
        .error(err => {
          console.error(err.response)
        })
    },
    signup({ commit, dispatch }, credentials) {
      /*
      POST: 사용자 입력정보를 signup URL로 보내기
        성공하면
          응답 토큰 저장
          현재 사용자 정보 받기
          메인 페이지(ArticleListView)로 이동
        실패하면
          에러 메시지 표시
      */
    delete credentials.userPw2
    const credentialsForLogin = {
      userId : credentials.userId,
      userPw : credentials.userPw
    }
      axios({
        url: api_url.accounts.signup(),
        method: 'post',
        data: credentials,
        // hearders: getters.authHeader,
      })
        .then(res => {
          // console.log(res.data)
          // const token = res.data.key
          // dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          // router.push({ name: 'login' })
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

    findUserid({},credentials){
      axios({
        url:api_url.accounts.findUserid(),
        method: 'get',
        data: credentials
      })
        .then(res=>{
          // 어떤 형태로 오는지 확인 후 작성
          const userId = res.data
          console.log(userId)

        })
        .catch(err=>{
          console.error(err.response.data)
          commit('SET_AUTH_ERROR',err.response.data)
        })

    },

  }

}
