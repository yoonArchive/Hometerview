import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router.js";

export default {
  state: {
    recruitmentList: [],
    applyingList: [],
    recruitDetail: {},
    token: localStorage.getItem("token") || "",
    isApplied: false,
    applyType: "",
    applyCounts: [],
    studySpaceNo: ""
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_RECRUITMENT_LIST: (state, recruitmentList) =>
      (state.recruitmentList = recruitmentList),
    SET_APPLYING_LIST: (state, applyingList) =>
      (state.applyingList = applyingList),
    SET_RECRUIT_DETAIL: (state, recruitDetail) =>
      (state.recruitDetail = recruitDetail),
    SET_APPLY_TYPE: (state, applyType) => (state.applyType = applyType),
    SET_APPLY_COUNT: (state, applyCounts) => (state.applyCounts = applyCounts),
    SET_STUDYSPACE_NO: (state, studySpaceNo) =>
      (state.studySpaceNo = studySpaceNo)
  },
  getters: {
    memberHeader: state => `Bearer ${state.token}`,
    isValidedEmail: state => state.isValidedEmail,
    recruitmentList: state => state.recruitmentList,
    recruitDetail: state => state.recruitDetail,
    applyType: state => state.applyType,
    recruitCount: state => state.recruitDetail.count,
    applyCounts: state => state.applyCounts,
    applyingList: state => state.applyingList
  },
  actions: {
    createRecruitment({ getters }, formData) {
      console.log("키 값 확인");
      for (let key of formData.keys()) {
        console.log(key);
      }

      console.log("벨류 값 확인");
      for (let value of formData.values()) {
        console.log(value);
      }
      axios({
        url: api_url.membersrecruitment.membersrecruitments(),
        method: "post",
        data: formData,
        headers: {
          Authorization: getters.memberHeader,
          "Context-Type": "multipart/form-data"
        }
      })
        .then(res => {
          console.log(res.data);
          alert("성공하셨습니다.");
          router.push({ name: "membersrecruitment" });
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    bringRecruitmentList({ commit }) {
      axios({
        url: api_url.membersrecruitment.membersrecruitments(),
        method: "get"
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUITMENT_LIST", res.data.recruits);
          console.log(res.data.applyCounts);
          commit("SET_APPLY_COUNT", res.data.applyCounts);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    bringApplyingRecruit({ commit, getters }) {
      console.log("신청 중 모집글 가져오기");
      axios({
        url: api_url.membersrecruitment.membersapplying(),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data.recruits);
          commit("SET_APPLYING_LIST", res.data.recruits);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    bringRecruitmentDetail({ commit, getters }, recruitNo) {
      axios({
        url: api_url.membersrecruitment.membersrecruitment(recruitNo),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          console.log(res.data.applyType);
          commit("SET_RECRUIT_DETAIL", res.data);
          commit("SET_APPLY_TYPE", res.data.applyType);
          commit("SET_STUDYSPACE_NO", res.data.stdNo);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    updateRecruitmentDetail({ commit, getters }, recruitInfo) {
      const recruitNo = recruitInfo[0];
      const recruitmentInfo = recruitInfo[1];
      axios({
        url: api_url.membersrecruitment.membersrecruitment(recruitNo),
        method: "put",
        data: recruitmentInfo,
        headers: {
          Authorization: getters.memberHeader,
          "Context-Type": "multipart/form-data"
        }
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUIT_DETAIL", res.data);
          router.push({
            name: "membersrecruitmentdetail",
            params: { recruitNo: recruitNo }
          });
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    deleteRecruitmentDetail({ commit }, recruitNo) {
      axios({
        url: api_url.membersrecruitment.membersrecruitment(recruitNo),
        method: "delete"
      })
        .then(res => {
          console.log(res.data);
          router.push({
            name: "membersrecruitment"
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    bringRecruitingList({ commit }, recruitType) {
      axios({
        url:
          api_url.membersrecruitment.membersrecruiting() +
          `?type=${recruitType}`,
        method: "get"
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUITMENT_LIST", res.data.recruits);
          commit("SET_APPLY_COUNT", res.data.applyCounts);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    bringRecruitSearchList({ commit }, recruitSearchKeyword) {
      console.log(recruitSearchKeyword);
      const encodeSearchKeyword = `?keyword=${encodeURIComponent(
        recruitSearchKeyword
      )}`;
      axios({
        url:
          api_url.membersrecruitment.membersrecruitsearch() +
          encodeSearchKeyword,
        method: "get"
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUITMENT_LIST", res.data.recruits);
          commit("SET_APPLY_COUNT", res.data.applyCounts);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    bringRecruitTypeList({ commit }, recruitType) {
      console.log(recruitType);
      axios({
        url:
          api_url.membersrecruitment.membersrecruittype() +
          `?type=${recruitType}`,
        method: "get"
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUITMENT_LIST", res.data.recruits);
          commit("SET_APPLY_COUNT", res.data.applyCounts);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    async studyApply({ commit, getters, dispatch }, recruitNo) {
      console.log(api_url.membersrecruitment.studyApply(recruitNo));
      axios({
        url: api_url.membersrecruitment.studyApply(recruitNo),
        method: "post",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          commit("SET_APPLY_TYPE", "NORMAL");
          dispatch("bringRecruitmentDetail", recruitNo);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    async studyApplyCancel({ commit, getters, dispatch }, recruitNo) {
      axios({
        url: api_url.membersrecruitment.studyApply(recruitNo),
        method: "delete",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          commit("SET_APPLY_TYPE", null);
          dispatch("bringRecruitmentDetail", recruitNo);
        })
        .catch(err => {
          console.log(err.response);
        });
    }
  }
};
