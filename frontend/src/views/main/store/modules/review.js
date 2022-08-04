import axios from 'axios'
import api_url from '@/api/api_url'
import http from '@/api/api_url'
import router from "@/common/lib/vue-router.js";
// import getter from "./accounts"
export default {
  // namespaced: true,
  state: {
    token: localStorage.getItem('token') || '' ,
    numberOfReview : 0,
    reviewContents : [],
    currentReview: {
    },


  },

  getters: {
    currentReview: state => state.currentReview,
    reviewContents: state => state.reviewContents,
    numberOfReview : state => state.numberOfReview,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),

  },

  mutations: {
    SET_REVIEW_CONTENTS(state, data){
      state.reviewContents = data;
    },
    SET_CURRENT_REVIEW(state, data){
      state.currentReview = data
    },
    SET_NUMBER_REVIEW(state, data){
      state.numberOfReview = data;
    }
  },

  actions: {
    //회고 리스트
    getReviewInfo({commit, rootGetters, state}){
      axios.get(api_url.review.reviews(), {
        headers : this.getters.authHeader,
      }).then((data)=>{
        console.log(data.data.reviews);
        console.log(data.data.reviews.length)
        // commit('SET_CURRENT_REVIEW', data.data)
        commit('SET_REVIEW_CONTENTS', data.data.reviews);
        commit('SET_NUMBER_REVIEW', data.data.reviews.length);
      }).catch((err)=>{
        console.log(err);
      })
    },
    //회고 상세가져오기
    getReviewDetail({commit, rootGetters,state}, reviewNo){
      axios.get(api_url.review.review(reviewNo), {
        headers : this.getters.authHeader,
      }).then((data)=>{
        console.log(data.data.reviews);
        commit('SET_CURRENT_REVIEW', data.data)
      }).catch((err)=>{
        console.log('회고 상세에러' + err);
      })
    },
    //회고 작성하기

    createReview({ commit, getters }, newreview) {


      console.log('김')
      axios({
        url: api_url.review.reviews(),
        method: 'post',
        data: newreview,
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_CURRENT_REVIEW', res.data)
          console.log('공지사항 작성 성공' + res)
          router.push({
            name: 'review',
            params: { reviewNo: getters.currentReview.reviewNo  }
          })
        })
    },
    //회고 수정하기
    updateReview({ commit, getters }, { reviewNo, reviewType, reviewTitle, reviewContents}) {
      console.log(reviewTitle)
      console.log(reviewContents)
      axios({
        url: api_url.review.review(reviewNo),
        method: 'put',
        data: { reviewTitle, reviewContents, reviewType },
        headers: this.getters.authHeader,
      })
        .then(res => {

          commit('SET_CURRENT_REVIEW', res.data)
          router.push({
            name: 'review',
            params: { reviewNo: getters.currentReview.reviewNo }
          })
        })
    },
    //회고 삭제하기
    deleteReview({ commit, getters }, reviewNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: api_url.review.review(reviewNo),
          method: 'delete',
          headers: this.getters.authHeader,
        })
          .then(() => {
            commit('SET_CURRENT_REVIEW', {})
            router.push({ name: 'myinterview' })
          })
          .catch(err => console.error(err.response))
      }
    },

  },
}
