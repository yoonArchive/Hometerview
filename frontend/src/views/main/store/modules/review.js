import axios from "axios";
import api_url from "@/api/api_url";
import http from "@/api/api_url";
import router from "@/common/lib/vue-router.js";
// import getter from "./accounts"
export default {
  // namespaced: true,
  state: {
    token: localStorage.getItem("token") || "",
    numberOfReview: 0,
    reviewContents: [],
    currentReview: {},

    ddays: [],
    restday: [],
    ddaylen: "",

    dday: []
  },

  getters: {
    currentReview: state => state.currentReview,
    reviewContents: state => state.reviewContents,
    numberOfReview: state => state.numberOfReview,
    authHeader: stata => ({ Authorization: `Bearer ${state.token}` }),
    // resumeHeader: state => ({ Authorization: `Bearer ${state.token}`})
    restday: state => state.restday,
    currentDdays: state => state.ddays,
    ddaylen: state => state.ddaylen,
    dday: state => state.dday
  },

  mutations: {
    SET_REVIEW_CONTENTS(state, data) {
      state.reviewContents = data;
    },
    SET_CURRENT_REVIEW(state, data) {
      state.currentReview = data;
    },
    SET_NUMBER_REVIEW(state, data) {
      state.numberOfReview = data;
    },
    SET_DDAYS(state, data) {
      state.ddays = data;
    },
    SET_RESTDAYS(state, data) {
      state.restday = data;
    },
    SET_DDAYLEN(state, data) {
      state.ddaylen = data;
    },
    SET_DDAY(state, data) {
      state.dday = data;
    }
  },

  actions: {
    //회고 리스트
    getReviewInfo({ commit, getters, state }) {
      axios
        .get(api_url.review.reviews(), {
          headers: getters.authHeader
        })
        .then(data => {
          console.log(data.data.reviews);
          console.log(data.data.reviews.length);
          // commit('SET_CURRENT_REVIEW', data.data)
          commit("SET_REVIEW_CONTENTS", data.data.reviews);
          commit("SET_NUMBER_REVIEW", data.data.reviews.length);
          console.log("회고리스트 가져오기");
        })
        .catch(err => {
          console.log(err);
        });
    },
    //회고 상세가져오기
    async getReviewDetail({ commit, getters, dispatch }, reviewNo) {
      await axios
        .get(api_url.review.review(reviewNo), {
          headers: getters.authHeader
        })
        .then(data => {
          console.log(data.data.reviews);
          commit("SET_CURRENT_REVIEW", data.data);
          dispatch("getReviewInfo");
          console.log("상세가져오기 성공");
        })
        .catch(err => {
          console.log("회고 상세에러" + err);
        });
    },
    //회고 작성하기

    createReview({ commit, getters, dispatch }, newreview) {
      console.log("김");
      axios({
        url: api_url.review.reviews(),
        method: "post",
        data: newreview,
        headers: getters.authHeader
      }).then(res => {
        commit("SET_CURRENT_REVIEW", res.data);
        console.log("회고 작성 성공" + res);
        dispatch("getReviewInfo");
        // router.push({ name: "diary" });
        // router.push({
        //   name: 'myinterview',
        //   // params: { reviewNo: getters.currentReview.reviewNo  }
        // })
        router.push({
          name: "diary"
        });
        window.location.reload();
        window.location.reload();
      });
    },
    //달력에서 리뷰 생성하기
    createReview1({ commit, getters, dispatch }, newreview) {
      console.log("김");
      axios({
        url: api_url.review.reviews(),
        method: "post",
        data: newreview,
        headers: getters.authHeader
      }).then(res => {
        commit("SET_CURRENT_REVIEW", res.data);
        console.log("공지사항 작성 성공" + res);
        dispatch("getReviewInfo");
        router.push({
          name: "diary"
        });
        window.location.reload();
        window.location.reload();
        window.location.reload();
        window.location.reload();
      });
    },
    //회고 수정하기
    updateReview(
      { commit, getters, dispatch },
      { reviewNo, reviewType, reviewDate, reviewTitle, reviewContents }
    ) {
      console.log(reviewTitle);
      console.log(reviewContents);
      axios({
        url: api_url.review.review(reviewNo),
        method: "put",
        data: { reviewTitle, reviewContents, reviewType, reviewDate },
        headers: getters.authHeader
      }).then(res => {
        commit("SET_CURRENT_REVIEW", res.data);
        dispatch("getReviewInfo");
        router.push({
          name: "review",
          params: { reviewNo: getters.currentReview.reviewNo }
        });
      });
    },
    //회고 삭제하기
    deleteReview({ commit, getters, dispatch }, reviewNo) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: api_url.review.review(reviewNo),
          method: "delete",
          headers: getters.authHeader
        })
          .then(() => {
            commit("SET_CURRENT_REVIEW", {});
            dispatch("getReviewInfo");
            router.push({
              path: "/home/myinterview/diary"
            });
          })
          .catch(err => console.error(err.response));
      }
    },
    //달력에서 회고 삭제하기
    deleteReview1({ commit, getters, dispatch }, reviewNo) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: api_url.review.review(reviewNo),
          method: "delete",
          headers: getters.authHeader
        })
          .then(() => {
            commit("SET_CURRENT_REVIEW", {});
            dispatch("getReviewInfo");
            router.push({
              name: "diary"
            });
            window.location.reload();
            window.location.reload();
          })

          .catch(err => console.error(err.response));
      }
    },

    //달력에서 지우기
    deleteReview1({ commit, getters, dispatch }, reviewNo) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: api_url.review.review(reviewNo),
          method: "delete",
          headers: getters.authHeader
        })
          .then(() => {
            commit("SET_CURRENT_REVIEW", {});
            dispatch("getReviewInfo");
            router.push({
              name: "diary"
            });
            window.location.reload();
            window.location.reload();
          })
          .catch(err => console.error(err.response));
      }
    },

    //dday 리스트 가져오기
    getDdayInfo({ commit, getters, state }) {
      axios
        .get(api_url.accounts.dday(), {
          headers: getters.authHeader
        })
        .then(res => {
          console.log(res.data);
          // commit('SET_CURRENT_REVIEW', data.data)
          commit("SET_DDAYS", res.data.ddays);
          commit("SET_RESTDAYS", res.data.results);
          commit("SET_DDAYLEN", res.data.ddays.length);
          console.log("디데이 리스트 가져오기 성공");
        })
        .catch(err => {
          console.log("dday 리스트 가져오기 에러" + err);
        });
    },

    //dday 상세 가져오기
    getDdayDetail({ commit, getters, state }, ddayNo) {
      axios
        .get(api_url.accounts.ddayDetail(ddayNo), {
          headers: getters.authHeader
        })
        .then(res => {
          console.log(res.data);
          // commit('SET_CURRENT_REVIEW', data.data)
          commit("SET_DDAY", res.data);
          console.log("디데이 상세가져오기 성공");
        })
        .catch(err => {
          console.log("dday 상세 가져오기 에러" + err);
        });
    },

    //디데이 작성하기

    createDday({ commit, getters, dispatch }, dday) {
      console.log("김");
      axios({
        url: api_url.accounts.dday(),
        method: "post",
        data: dday,
        headers: getters.authHeader
      }).then(res => {
        commit("SET_DDAYS", res.data.ddays);
        dispatch("getDdayInfo");
        // console.log("디데이 작성 성공" + res.data);
        // this.$router.go();
        router.push({
          name: "diary"
        });
        window.location.reload();
        window.location.reload();
      });
    },
    //디데이 수정하기
    updateDday({ commit, getters, dispatch }, ddays) {
      const ddayNo = ddays[0];
      console.log("ddayNo" + ddayNo);
      const ddayData = ddays[1];

      axios({
        url: api_url.accounts.ddayDetail(ddayNo),
        method: "put",
        data: ddayData,
        headers: getters.authHeader
      }).then(res => {
        commit("SET_DDAYS", res.data.ddays);
        dispatch("getDdayInfo");
        // router.push()
        console.log("디데이 수정 성공" + res.data);
        router.push({
          name: "diary"
        });
        window.location.reload();
        window.location.reload();
      });
    },
    //디데이 삭제하기
    deleteDDAY({ commit, getters, dispatch }, ddayNo) {
      if (confirm("정말 삭제하시겠습니까?")) {
        axios({
          url: api_url.accounts.ddayDetail(ddayNo),
          method: "delete",
          headers: getters.authHeader
        })
          .then(() => {
            commit("SET_DDAYS", {});
            dispatch("getDdayInfo");
            console.log("디데이 삭제 성공" + res.data);
            router.push({
              name: "diary"
            });
            window.location.reload();
            window.location.reload();
          })

          .catch(err => console.error(err.response));
      }
    }
  }
};
