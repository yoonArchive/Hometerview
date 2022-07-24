import axios from 'axios'
import api_url from '@/api/api_url'
import router from '../../../../common/lib/vue-router'

import _ from 'lodash'
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    notices: [],
    notice: {},
  },

  getters: {
    notices: state => state.notices,
    notice: state => state.notice,
    isAuthor: (state, getters) => {
      return state.notice.user?.username === getters.currentUser.username
    },
    isnotice: state => !_.isEmpty(state.notice),
  },

  mutations: {
    SET_NOTICES: (state, notices) => state.notices = notices,
    SET_NOTICE: (state, notice) => state.notice = notice,

  },

  actions: {
    //공지사항 목록 가져오기
    fetchNotices({ commit, getters }) {
      axios({
        url: api_url.notice.notices(),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res => commit('SET_NOTICES', res.data),
        )
        .catch(err => console.error(err.response))
    },
    //공지사항 상세 가져오기
    fetchNotice({ commit, getters }, noticeNo) {
      axios({
        url: api_url.notice.notice(noticeNo),
        method: 'get',
        // headers: getters.authHeader,
      })
        .then(res => commit('SET_NOTICE', res.data))
        .catch(err => {
          console.error('에러'+err.response)
          if (err.response.status === 404) {
            router.push({ name: 'NotFound404' })
          }
        })
    },
    //공지사항 작성하기
    createNotice({ commit, getters }, notice) {
      axios({
        url: api_url.notice.notices(),
        method: 'post',
        data: notice,
        // headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_NOTICE', res.data)
          console.log('공지사항 작성 성공' + res)
          router.push({
            name: 'notice',
            params: { noticeNo: getters.notice.noticeNo }
          })
        })
    },
    //공지사항 수정하기
    updateNotice({ commit, getters }, { noticeNo, noticeTitle, noticeContents}) {
      console.log(noticeTitle)
      console.log(noticeContents)
      axios({
        url: api_url.notice.notice(noticeNo),
        method: 'put',
        data: { noticeTitle, noticeContents },
        // headers: getters.authHeader,
      })
        .then(res => {

          commit('SET_NOTICE', res.data)
          router.push({
            name: 'notice',
            params: { noticeno: getters.notice.noticeNo }
          })
        })
    },
    //공지사항 삭제하기
    deleteNotice({ commit, getters }, noticeNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: api_url.notice.notice(noticeNo),
          method: 'delete',
          // headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_NOTICE', {})
            router.push({ name: 'notices' })
          })
          .catch(err => console.error(err.response))
      }
    },






  },
}
