import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/home/home";
import store from "@/common/lib/store.js";
import Main from "@/views/main/main";

/* 로그인 회원가입 등 계정 관련 페이지 */
import LoginView from "@/views/accounts/LoginView";
import SignupView from "@/views/accounts/SignupView";
import LogoutView from "@/views/accounts/LogoutView";
import FindUseridView from "@/views/accounts/FindUseridView";
import FindUserPassword from "@/views/accounts/FindUserPassword";

/* 스터디 모집 페이지 */
import CreateMembersRecruitmentView from "@/views/membersrecruitment/CreateMembersRecruitmentView";
import MembersRecruitmentView from "@/views/membersrecruitment/MembersRecruitmentView";
import MembersRecruitmentDetailView from "@/views/membersrecruitment/MembersRecruitmentDetailView";
import MembersRecruitmentEditView from "@/views/membersrecruitment/MembersRecruitmentEditView";

/* 스터디 스페이스 페이지ㅣ*/
import StudySpaceDetailView from "@/views/studyspace/StudySpaceDetailView";
import MemberCoverLetter from "@/views/studyspace/MemberCoverLetter";

/* 공지사항 */
import noticeList from "@/views/notice/noticeListView";
import noticeNewView from "@/views/notice/noticeNewView";
import noticeDetailView from "@/views/notice/noticeDetailView";
import noticeEditView from "@/views/notice/noticeEditView";

/* 마이 페이지 */
import MyPage from "@/views/mypage/MyPage";
import ChangePassword from "@/views/mypage/changepassword/ChangePassword";
import MyAccount from "@/views/mypage/myaccount/MyAccount";
import Withdrawal from "@/views/mypage/withdrawal/Withdrawal";
import PasswordConfirm from "@/views/mypage/passwordconfirm/PasswordConfirm";

/* 자소서 페이지 */
import CoverLetterView from "@/views/myinterview/coverletter/CoverLetterView";

/* 마이 인터뷰 */
import MyInterviewView from "@/views/myinterview/MyInterviewView";

/* 회고 페이지 */
import ReviewView from "@/views/myinterview/review/ReviewView";
import ReviewNew from "@/views/myinterview/review/ReviewNewView";
import ReviewEdit from "@/views/myinterview/review/ReviewEditView";

/* 세션 페이지 */
import SessionView from "@/views/session/SessionView";

/* 내 면접관리 페이지 */
import diary from "@/views/myinterview/diary/diary";
import coverLetterPage from "@/views/myinterview/coverletter/CoverLetterPage";
import recordingPage from "@/views/myinterview/recording/recordingPage";
/* 마이스터디-공통질문 페이지 */
// import CommonQuestionDetailView from '@/views/studyspace/components/CommonQuestionDetailView'
// import CommonQuestionNewView from '@/views/studyspace/components/CommonQuestionNewView'
// import CommonQuestionEditView from '@/views/studyspace/components/CommonQuestionEditView'
// import StudyQuestionListView from "@/views/studyspace/StudyQuestionListView";

const fullMenu = require("@/views/main/menu.json");
function makeRoutesFromMenu() {
  let routes = Object.keys(fullMenu).map(key => {
    if (key === "home") {
      return { path: fullMenu[key].path, name: key, component: Home };
    } else if (key === "history") {
      return { path: fullMenu[key].path, name: key, component: History };
    } else {
      // menu.json 에 들어있는 로그아웃 메뉴
      return null;
    }
  });
  // 로그아웃 파싱한 부분 제거
  routes = routes.filter(item => item);
  // menu 자체에는 나오지 않는 페이지 라우터에 추가(방 상세보기)
  routes.push(
    {
      path: "/home",
      name: "home",
      component: Home,
      redirect: "/home/membersrecruitment",
      children: [
        {
          path: "login",
          name: "login",
          component: LoginView
          // component: () => import("@/views/account/login.vue")
        },
        {
          path: "signup",
          name: "signup",
          component: SignupView
        },
        {
          path: "logout",
          name: "logout",
          component: LogoutView
        },
        {
          path: "findid",
          name: "findid",
          component: FindUseridView
        },
        {
          path: "finduserpassword",
          name: "finduserpassword",
          component: FindUserPassword
        },
        {
          path: "mypage",
          name: "mypage",
          component: MyPage,
          redirect: "/home/mypage/myaccount",
          meta: { IsLogin: true },
          beforeEnter: (to, from, next) => {
            const isPasswordConfirm = store.getters["isPasswordConfirm"];
            if (!isPasswordConfirm) {
              next({ name: "passwordconfirm" });
            } else {
              next();
            }
          },
          children: [
            {
              path: "myaccount",
              name: "myaccount",
              component: MyAccount
            },
            {
              path: "changepassword",
              name: "changepassword",
              component: ChangePassword
            },
            {
              path: "withdrawal",
              name: "withdrawal",
              component: Withdrawal
            }
          ]
        },
        {
          path: "passwordconfirm",
          name: "passwordconfirm",
          meta: { IsLogin: true },
          component: PasswordConfirm
        },
        {
          path: "createmembersrecruitment",
          name: "createmembersrecruitment",
          meta: { IsLogin: true },
          component: CreateMembersRecruitmentView
        },
        {
          path: "membersrecruitment",
          name: "membersrecruitment",
          component: MembersRecruitmentView
        },
        {
          path: "membersrecruitment/:recruitNo",
          name: "membersrecruitmentdetail",
          meta: { IsLogin: true },
          component: MembersRecruitmentDetailView
        },
        {
          path: "membersrecruitment/:recruitNo/update",
          name: "membersrecruitmentedit",
          meta: { IsLogin: true },
          component: MembersRecruitmentEditView
        },
        {
          path: "coverletter/:resumeindex",
          name: "coverletter",
          meta: { IsLogin: true },
          component: CoverLetterView
        },
        {
          path: "myinterview",
          name: "myinterview",
          component: MyInterviewView,
          meta: { IsLogin: true },
          redirect: "/home/myinterview/diary",
          children: [
            {
              path: "diary",
              name: "diary",
              component: diary
            },
            {
              path: "coverPage",
              name: "coverPage",
              component: coverLetterPage
            },
            {
              path: "recordingPage",
              name: "recordingPage",
              component: recordingPage
            }
          ]
        },
        {
          path: "review/:reviewNo",
          name: "review",
          meta: { IsLogin: true },
          component: ReviewView
        },
        {
          path: "review/new",
          name: "reviewNew",
          meta: { IsLogin: true },
          component: ReviewNew
        },
        {
          path: "review/:reviewNo/edit",
          name: "reviewEdit",
          meta: { IsLogin: true },
          component: ReviewEdit
        },
        {
          path: "/notice",
          name: "notices",
          component: noticeList
        },
        {
          path: "/notice/new",
          name: "noticeNew",
          component: noticeNewView
        },
        {
          path: "/notice/:noticeNo",
          name: "notice",
          component: noticeDetailView
        },
        {
          path: "/notice/:noticeNo/edit",
          name: "noticeEdit",
          component: noticeEditView
        },
        // {
        //   path: "study",
        //   name: "study",
        //   component: StudySpaceView
        // },
        {
          path: "/study/:stdNo",
          name: "studydetail",
          meta: { IsLogin: true },
          component: StudySpaceDetailView
        },
        {
          path: "membercoverletter/:studentindex",
          name: "membercoverletter",
          meta: { IsLogin: true },
          component: MemberCoverLetter
        }
      ]
    },
    {
      path: "/home/session/:sessionNo",
      name: "session",
      meta: { IsLogin: true },
      component: SessionView
    },
    {
      path: "/",
      name: "main",
      component: Main
    }
  );
  return routes;
}

const routes = makeRoutesFromMenu();

const router = createRouter({
  history: createWebHistory(),
  routes,
  linkActiveClass: "route-active",
  linkExactActiveClass: "route-exact-active"
});
router.beforeEach((to, from, next) => {
  console.log(store.getters.isLoggedIn);
  if (to.meta.IsLogin && !store.getters.isLoggedIn) {
    alert("로그인 후 이용할 수 있습니다.");
    next("/home/login");
    return;
  }
  next();
});
router.afterEach(to => {
  console.log(to);
});

export default router;
