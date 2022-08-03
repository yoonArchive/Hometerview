// import _Space from "element-plus/lib/el-space"

const HOST = 'http://localhost:8080/api/v1/'
const USERS = 'users/'
const AUTH = 'auth/'
const NOTICE = 'notice/'
const RECRUITS = 'recruits/'
const RESUME = 'resume/'
const REVIEW = 'review/'
const STUDY = 'study/'
const APPLY = 'apply/'


export default {
  accounts: {
    login: () => HOST + AUTH + 'login/',
    logout: () => HOST + USERS + 'logout/',
    signup: () => HOST + USERS,
    deleteUser: () => HOST + USERS,
    updateUser: () => HOST + USERS,

    // 중복검사
    emailDuplicateCheck:() => HOST + USERS + 'checkEmail/',
    idDuplicateCheck:() => HOST + USERS + 'checkId/',

    // 찾기
    findUserid: () => HOST + USERS + 'findId/',
    findUserPassword: () => HOST + USERS + 'findPw/',

    // 인증
    passwordConfirm: () => HOST + USERS + 'pw/',
    changepassword: () => HOST + USERS + 'pw/',
    authEmail : () => HOST + USERS + 'authEmail',
    checkAuthKey : () => HOST + USERS + 'checkAuthKey',


    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + USERS + 'me/', // 맞는지 확인

  },
  resumes : {
    //자소서
    getResumeInfo: () => HOST + RESUME,
    getResumeDetail : () =>HOST + RESUME + 'detail/',
  },
  notice: {
    //공지사항
    notices: () => HOST + NOTICE,
    notice: (noticeNo) => HOST + NOTICE + `${noticeNo}`,
  },

  membersrecruitment:{
    membersrecruitments: () => HOST + RECRUITS,
    membersrecruitment: (recruitNo) => HOST + RECRUITS +`${recruitNo}`,
    membersrecruiting: () => HOST + RECRUITS + 'recruiting/',
    membersrecruitsearch: () => HOST + RECRUITS + 'search/',
    membersrecruittype: () => HOST + RECRUITS + 'type/',
    studyApply: (recruitNo) => HOST + APPLY + `${recruitNo}`
  },
  review:{
    //회고
    reviews: () => HOST + USERS + REVIEW,
    review: (reviewNo) => HOST + USERS + REVIEW + `${reviewNo}`
  },
  study:{
    studyspace : () => HOST +  STUDY,


    studys: (stdNo) => HOST + STUDY,
    commonquestions: (stdNo) => HOST + STUDY + `${stdNo}/`+ 'common',
    commonquestion: (stdNo,questionNo) => HOST + STUDY + `${stdNo}/`+ 'common/' +`${questionNo}`,
    commonquestiontype: (stdNo,questionNo) => HOST + STUDY + `${stdNo}/`+ 'common/' +`${questionNo}/`+'type',
    studyspacedetail : (stdNo) => HOST +  STUDY + `${stdNo}/`,
    studyCoverLetter : (stdNo , resumeNo) => HOST + STUDY + RESUME + `${stdNo}/` + `${resumeNo}`,
  }
}
