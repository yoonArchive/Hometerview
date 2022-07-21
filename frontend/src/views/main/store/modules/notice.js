import axios from 'axios'
import drf from '@/api/drf'
import router from '@/router'

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
    SET_noticeS: (state, notices) => state.notices = notices,
    SET_notice: (state, notice) => state.notice = notice,
    SET_notice_COMMENTS: (state, comments) => (state.notice.comments = comments),
  },

  actions: {
    fetchnotices({ commit, getters }) {
      axios({
        url: 'http://localhost:8000/api/v1/',
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => commit('SET_noticeS', res.data))
        .catch(err => console.error(err.response))
    },

    fetchnotice({ commit, getters }, noticePk) {
      axios({
        url: 'http://localhost:8000/api/v1/'drf.notices.notice(noticePk),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => commit('SET_notice', res.data))
        .catch(err => {
          console.error(err.response)
          if (err.response.status === 404) {
            router.push({ name: 'NotFound404' })
          }
        })
    },

    createnotice({ commit, getters }, notice) {
      axios({
        url: drf.notices.notices(),
        method: 'post',
        data: notice,
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_notice', res.data)
          router.push({
            name: 'notice',
            params: { noticePk: getters.notice.pk }
          })
        })
    },

    updatenotice({ commit, getters }, { pk, title, content}) {
      console.log(title)
      console.log(content)
      axios({
        url: drf.notices.notice(pk),
        method: 'put',
        data: { title, content },
        headers: getters.authHeader,
      })
        .then(res => {

          commit('SET_notice', res.data)
          router.push({
            name: 'notice',
            params: { noticePk: getters.notice.pk }
          })
        })
    },

    deletenotice({ commit, getters }, noticePk) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: drf.notices.notice(noticePk),
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_notice', {})
            router.push({ name: 'notices' })
          })
          .catch(err => console.error(err.response))
      }
    },






  },
}
