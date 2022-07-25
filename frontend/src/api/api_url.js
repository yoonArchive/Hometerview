const HOST = 'http://localhost:8080/api/v1/'
const USERS = 'users/'
const AUTH = 'auth/'
const NOTICE = 'notice/'


export default {
  accounts: {
    login: () => HOST + AUTH + 'login/',
    logout: () => HOST + USERS + 'logout/',
    signup: () => HOST + USERS,
    findUserid: () => HOST + USERS + 'findid/',
    emailDuplicateCheck:() => HOST + USERS + 'checkemail/',
    idDuplicateCheck:() => HOST + USERS + 'checkid/',
    deleteUser: () => HOST + USERS,

    findMyAccount: () => HOST + USERS + 'findid/',
    findUserPassword: () => HOST + USERS + 'findpw/',
    changepassword: () => HOST + USERS + 'pw/',
    updateUser: () => HOST + USERS,
    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + USERS + 'me/', // 맞는지 확인

  },
  notice: {
    notices: () => HOST + NOTICE,
    notice: (noticeNo) => HOST + NOTICE + `${noticeNo}`,

  }
}
