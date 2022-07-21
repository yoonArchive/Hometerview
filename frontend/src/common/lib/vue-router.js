import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/home/home'
import ConferencesDetail from '@/views/conferences/conference-detail'

import History from '@/views/history/history'
import Main from '@/views/main/main'

import LoginView from '@/views/accounts/LoginView'
import SignupView from '@/views/accounts/SignupView'
import LogoutView from '@/views/accounts/LogoutView'


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
    component: Home
  },{
    path: '/',
    name: 'main',
    component:Main
  },{
    path: '/login',
    name: 'login',
    component: LoginView
    // component: () => import("@/views/account/login.vue")
  },{
    path: '/signup',
    name: 'signup',
    component: SignupView
  },{
    path: '/logout',
    name: 'logout',
    component: LogoutView
  }
  
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
