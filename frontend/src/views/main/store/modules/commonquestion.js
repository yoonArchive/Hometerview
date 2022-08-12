import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router.js";
// import getter from "./accounts"
export default {
  // namespaced: true,
  state: {
    token: localStorage.getItem("token") || "",

    commonQeustion: {},
    commonQuestions: []
  },

  getters: {
    commonQuestion: state => state.commonQuestion,
    commonQuestions: state => state.commonQuestions,
    authHeader: state => ({ Authorization: `Bearer ${state.token}` })
  },

  mutations: {
    SET_commonQuestion(state, data) {
      state.commonQuestion = data;
    },
    SET_commonQuestions(state, data) {
      state.commonQuestions = data;
    }
  },

  actions: {
    //스터디 조회하기
    getStd({ commit, getters }, stdNo) {
      axios({
        url: api_url.study.studys(stdNo),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          commit("SET_commonQuestions", res.data.studies);
          console.log("공통질문 조회 성공" + res);
          // router.push({
          //   name: 'commonQuestion',
          //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo  }
          // })
        })
        .catch(err => {
          console.log(err);
        });
    },

    //공통질문 리스트
    commonQuestions1({ commit, getters, state }, stdNo) {
      // axios.get(api_url.study.commonquestions(stdNo), {
      //   headers : getters.authHeader,
      // })
      axios({
        url: api_url.study.commonquestions(stdNo),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data.commonQuestions);
          console.log(res.data);
          // commit('SET__commonQuestion', data.data)
          commit("SET_commonQuestions", res.data);
        })
        .catch(err => {
          console.log("공통질문 리스트 가져오기 에러" + err);
        });
    },
    //공통질문 상세가져오기
    commonQuestion({ commit, rootGetters, state }, commonQuestionNo, stdNo) {
      axios
        .get(api_url.study.commonquestion(commonQuestionNo, stdNo), {
          headers: this.getters.authHeader
        })
        .then(res => {
          console.log(res.data);
          commit("SET_commonQuestion", res.data);
        })
        .catch(err => {
          console.log("공통질문 상세에러" + err);
        });
    },
    //공통질문 작성하기
    createcommonQuestion(
      { commit, getters, dispatch },
      { stdNo, content, questionType }
    ) {
      const newcommonQuestion = {
        contents: content,
        questionType: questionType
      };
      console.log(newcommonQuestion);
      axios({
        url: api_url.study.commonquestions(stdNo),
        method: "post",
        data: newcommonQuestion,
        headers: getters.authHeader
      })
        .then(res => {
          commit("SET_commonQuestion", res.data.commonQuestions);
          console.log("공통질문 작성 성공" + res);
          dispatch("commonQuestions1", stdNo);
          // router.push({
          //   name: 'commonQuestion',
          //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo  }
          // })
        })
        .catch(err => {
          console.log("공통질문 등록 실패" + err);
        });
    },
    //공통질문 수정하기
    updatecommonQuestion({ commit, getters, dispatch }, info) {
      const stdNo = info[0];
      const payload = info[1];
      const commonQuestionNo = info[2];
      console.log("info" + info);
      console.log("질문번호" + commonQuestionNo);
      console.log("수정 내용" + payload.contents);
      axios({
        url: api_url.study.commonquestion(stdNo, commonQuestionNo),
        method: "put",
        data: payload,
        headers: getters.authHeader
      }).then(res => {
        console.log("수정성공");
        commit("SET_commonQuestion", res.data);
        dispatch("commonQuestions1", stdNo);

        // router.push({
        //   name: 'commonQuestion',
        //   params: { commonQuestionNo: getters.commonQuestion.commonQuestionNo }
        // })
      });
    },
    //공통질문 삭제하기
    deletecommonQuestion({ commit, getters, dispatch }, info) {
      const stdNo = info[0];
      const questionNo = info[1];
      console.log("인포" + info);
      if (confirm("정말 삭제하시겠습니까?")) {
        console.log(stdNo, questionNo);
        axios({
          url: api_url.study.commonquestion(stdNo, questionNo),
          method: "delete",
          data: {},
          headers: getters.authHeader
        })
          .then(res => {
            commit("SET_commonQuestion", res.data);
            dispatch("commonQuestions1", stdNo);

            // router.push({ name: 'myinterview' }) 라우터위치 어디로안정함
          })
          .catch(err => console.error(err.response));
      }
    }
  }
};
