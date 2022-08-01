import axios from 'axios'
import api_url from '@/api/api_url'
import router from "@/common/lib/vue-router.js";
// import getter from "./accounts"
export default {
  // namespaced: true,
  state: {
    token: localStorage.getItem('token') || '' ,

    commonQeustion:{},
    commonQuestions:[],



  },

  getters: {
    commonQuestion: state => state.commonQuestion,
    commonQuestions: state => state.commonQuestions,
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),

  },

  mutations: {
    SET_commonQuestion(state, data){
      state.commonQuestion = data;
    },
    SET_commonQuestions(state, data){
      state.commonQuestions = data
    }

  },

  actions: {
    //스터디 조회하기
    getStd({commit, getters},stdNo){
      axios({
        url: api_url.study.studys(stdNo),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_commonQuestions', res.data.studies)
          console.log('공통질문 조회 성공' + res)
          // router.push({
          //   name: 'commonQuestion',
          //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo  }
          // })
        }).catch((err)=>{
          console.log(err);
        })
    },


    //공통질문 리스트
    commonQuestions1({commit, getters, state},stdNo){
      // axios.get(api_url.study.commonquestions(stdNo), {
      //   headers : getters.authHeader,
      // })
      axios({
        url: api_url.study.commonquestions(stdNo),
        method: 'get',
        headers: getters.authHeader,
      })

      .then((res)=>{
        console.log(res.data.commonQuestions);
        console.log(res.data)
        // commit('SET__commonQuestion', data.data)
        commit('SET_commonQuestions', res.data);

      }).catch((err)=>{
        console.log('공통질문 리스트 가져오기 에러'+err);
      })
    },
    //공통질문 상세가져오기
    commonQuestion({commit, rootGetters,state}, commonQuestionNo, stdNo){
      axios.get(api_url.study.commonquestion(commonQuestionNo, stdNo), {
        headers : this.getters.authHeader,
      }).then((res)=>{
        console.log(res.data);
        commit('SET_commonQuestion', res.data)
      }).catch((err)=>{
        console.log('공통질문 상세에러' + err);
      })
    },
    //공통질문 작성하기
    createcommonQuestion({ commit, getters }, {stdNo,content,questionType} ) {
      const newcommonQuestion = {
        contents : content,
        questionType : questionType
       }
       console.log(newcommonQuestion)
      axios({
        url: api_url.study.commonquestions(stdNo),
        method: 'post',
        data: newcommonQuestion,
        headers: getters.authHeader
      })
        .then(res => {
          commit('SET_commonQuestion', res.data.commonQuestions)
          console.log('공통질문 작성 성공' + res)
          // router.push({
          //   name: 'commonQuestion',
          //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo  }
          // })
        }).catch((err)=>{
          console.log('공통질문 등록 실패'+err)
        })
    },
    //공통질문 수정하기
    updatecommonQuestion({ commit, getters }, {  commonQuestionNo, commonQuestionType, commonQuestionTitle, commonQuestion}, stdNo) {
      console.log(commonQuestionTitle)
      console.log(commonQuestion)
      const commonQuestionNo1 = commonQuestionNo
      axios({
        url: api_url.study.commonquestion(commonQuestionNo, stdNo),
        method: 'put',
        data: { commonQuestionTitle, commonQuestion, commonQuestionType },
        headers: this.getters.authHeader,
      })
        .then(res => {

          commit('SET__commonQuestion', res.data)
          // router.push({
          //   name: 'commonQuestion',
          //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo }
          // })
        })
    },
    //공통질문 삭제하기
    deletecommonQuestion({ commit, getters }, commonQuestionNo, stdNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          url: api_url.commonQuestion.commonQuestion(commonQuestionNo, stdNo),
          method: 'delete',
          headers: this.getters.authHeader,
        })
          .then(() => {
            commit('SET_commonQuestion', {})
            // router.push({ name: 'myinterview' }) 라우터위치 어디로안정함
          })
          .catch(err => console.error(err.response))
      }
    },

  },
}
