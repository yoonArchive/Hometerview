import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import store from '@/common/lib/store.js'
import ConferencesDetail from '@/views/conferences/conference-detail'


import History from '@/views/history/history'
import Main from '@/views/main/main'

/* 로그인 회원가입 등 계정 관련 페이지 */
import LoginView from '@/views/accounts/LoginView'
import SignupView from '@/views/accounts/SignupView'
import LogoutView from '@/views/accounts/LogoutView'
import FindUseridView from '@/views/accounts/FindUseridView'
import FindUserPassword from '@/views/accounts/FindUserPassword'

/* 스터디 모집 페이지 */
import CreateMembersRecruitmentView from '@/views/membersrecruitment/CreateMembersRecruitmentView'
import MembersRecruitmentView from '@/views/membersrecruitment/MembersRecruitmentView'
import MembersRecruitmentDetailView from '@/views/membersrecruitment/MembersRecruitmentDetailView'
import MembersRecruitmentEditView from '@/views/membersrecruitment/MembersRecruitmentEditView'

import StudyRecruitment from '@/views/home/components/study-recruitment'

/* 스터디 스페이스 페이지ㅣ*/
import StudySpaceView from '@/views/studyspace/StudySpaceView'
import StudySpaceDetailView from '@/views/studyspace/StudySpaceDetailView'

/* 공지사항 */
import noticeList from '@/views/notice/noticeListView'
import noticeNewView from '@/views/notice/noticeNewView'
import noticeDetailView from '@/views/notice/noticeDetailView'
import noticeEditView from '@/views/notice/noticeEditView'

/* 마이 페이지 */
import MyPage from '@/views/mypage/MyPage'
import ChangePassword from '@/views/mypage/changepassword/ChangePassword'
import MyAccount from '@/views/mypage/myaccount/MyAccount'
import Withdrawal from '@/views/mypage/withdrawal/Withdrawal'
import PasswordConfirm from '@/views/mypage/passwordconfirm/PasswordConfirm'

/* 자소서 페이지 */
import CoverLetterView from '@/views/myinterview/coverletter/CoverLetterView'

/* 마이 인터뷰 */
import MyInterviewView from '@/views/myinterview/MyInterviewView'

/* 회고 페이지 */
import ReviewView from '@/views/myinterview/review/ReviewView'
import ReviewNew from '@/views/myinterview/review/ReviewNewView'
import ReviewEdit from '@/views/myinterview/review/ReviewEditView'

/* 마이스터디-공통질문 페이지 */
// import CommonQuestionDetailView from '@/views/studyspace/components/CommonQuestionDetailView'
// import CommonQuestionNewView from '@/views/studyspace/components/CommonQuestionNewView'
// import CommonQuestionEditView from '@/views/studyspace/components/CommonQuestionEditView'
import StudyQuestionListView from '@/views/studyspace/StudyQuestionListView'


const fullMenu = require('@/views/main/menu.json')
function makeRoutesFromMenu () {
  let routes = Object.keys(fullMenu).map((key) => {
    if (key === 'home') {
      return { path: fullMenu[key].path, name: key, component: Home  }
    } else if (key === 'history') {
      return { path: fullMenu[key].path, name: key, component: History }
    } else { // menu.json 에 들어있는 로그아웃 메뉴
      return null
    }
  })
  // 로그아웃 파싱한 부분 제거
  routes = routes.filter(item => item)
  // menu 자체에는 나오지 않는 페이지 라우터에 추가(방 상세보기)
  routes.push({
    path: '/conferences/:conferenceId',
    name: 'conference-detail',
    component: ConferencesDetail
  },{
    path: '/home',
    name: 'home',
    component: Home,
    redirect:'/home/studyrecruitment',
    children : [{
      path:'studyrecruitment',
      name:'studyrecruitment',
      component : StudyRecruitment,
    },{
      path: 'login',
      name: 'login',
      component: LoginView
      // component: () => import("@/views/account/login.vue")
    },{
      path: 'signup',
      name: 'signup',
      component: SignupView
    },{
      path: 'logout',
      name: 'logout',
      component: LogoutView
    },{
      path: 'findid',
      name: 'findid',
      component:FindUseridView
    },{
      path:'finduserpassword',
      name: 'finduserpassword',
      component :FindUserPassword
    },{
      path:'mypage',
      name: 'mypage',
      component :MyPage,
      redirect: '/home/mypage/myaccount',
      beforeEnter:((to, from, next)=>{
        const isPasswordConfirm = store.getters['isPasswordConfirm'];
        if(!isPasswordConfirm){
          next({name : 'passwordconfirm'});
        }else{
          next();
        }
      }),
      children : [
        {
          path: 'myaccount',
          name: 'myaccount',
          component :MyAccount,
        },{
          path: 'changepassword',
          name: 'changepassword',
          component :ChangePassword,
        },{
          path: 'withdrawal',
          name: 'withdrawal',
          component :Withdrawal,
        }
      ]
    },{
      path : 'passwordconfirm',
      name : 'passwordconfirm',
      component : PasswordConfirm,
    },{
      path : 'createmembersrecruitment',
      name : 'createmembersrecruitment',
      component : CreateMembersRecruitmentView,
    },{
      path : 'membersrecruitment',
      name : 'membersrecruitment',
      component : MembersRecruitmentView,
    },{
      path : 'membersrecruitment/:recruitNo',
      name : 'membersrecruitmentdetail',
      component : MembersRecruitmentDetailView,
    },{
      path : 'membersrecruitment/:recruitNo/update',
      name : 'membersrecruitmentedit',
      component : MembersRecruitmentEditView,
    },{
      path: 'coverletter/:resumeindex',
      name : 'coverletter',
      component : CoverLetterView,
    },{
      path: 'myinterview',
      name: 'myinterview',
      component : MyInterviewView,
    },{
      path: 'review/:reviewNo',
      name: 'review',
      component : ReviewView
    },{
      path: 'review/new',
      name: 'reviewNew',
      component : ReviewNew
    },{
      path: 'review/:reviewNo/edit',
      name: 'reviewEdit',
      component : ReviewEdit
    },{
      path: '/notice',
      name: 'notices',
      component: noticeList
    },{
      path: '/notice/new',
      name: 'noticeNew',
      component: noticeNewView
    },{
      path: '/notice/:noticeNo',
      name: 'notice',
      component: noticeDetailView
    },
    {
      path: '/notice/:noticeNo/edit',
      name: 'noticeEdit',
      component: noticeEditView
    }],

      path: '/study',
      name: 'study',
      component : StudySpaceView
    },{
      path: '/study/:stdNo',
      name: 'studydetail',
      component : StudySpaceDetailView
    },

 {
    path: '/',
    name: 'main',
    component:Main
  },
  {
    path: '/study/:stdNo/questionList',
    name: 'questionList',
    component: StudyQuestionListView
  },





  // {
  //   path: '/study/:studyNo/common/new',
  //   name: 'commonNew',
  //   component: CommonQuestionNewView
  // },
  // {
  //   path: '/study/:studyNo/common/edit',
  //   name: 'commonEdit',
  //   component: CommonQuestionEditView
  // },
  // {
  //   path: '/study/:studyNo/common/:qustionNo',
  //   name: 'commonDetail',
  //   component: CommonQuestionDetailView
  // },







  )
  return routes
}

const routes = makeRoutesFromMenu()

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.afterEach((to) => {
  console.log(to)
})

export default router
