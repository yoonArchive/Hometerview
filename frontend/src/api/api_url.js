const HOST = 'http://localhost:8081/api/v1/'
const USERS = 'users/'
const AUTH = 'auth/'
const NOTICE = 'notice'


export default {
  accounts: {
    login: () => HOST + AUTH + 'login/',
    logout: () => HOST + USERS + 'logout/',
    signup: () => HOST + USERS,
    findMyAccount: () => HOST + USERS + 'findid/',


    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + USERS + 'me/', // 맞는지 확인

  },
  notice: {
    notices: () => HOST + NOTICE,
    notice: () => HOST + NOTICE+ `${{noticeno}}`,
  }
}
