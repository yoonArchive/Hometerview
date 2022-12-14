import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";

export default {
  state: {
    interviewUser: "",
    interviewUserFixed: "",
    posture: "",
    interviewMode: false,
    screenMode: "normal"
  },
  getters: {
    interviewUser: state => state.interviewUser,
    interviewUserFixed: state => state.interviewUserFixed,
    posture: state => state.posture,
    interviewMode: state => state.interviewMode,
    screenMode: state => state.screenMode
  },
  mutations: {
    SET_INTERVIEW_USER: (state, interviewUser) =>
      (state.interviewUser = interviewUser),
    SET_INTERVIEW_USER_FIXED: (state, interviewUserFixed) =>
      (state.interviewUserFixed = interviewUserFixed),
    SET_FIX_POSTURE: (state, posture) => (state.posture = posture),
    SET_INTERVIEW_MODE: (state, interviewMode) =>
      (state.interviewMode = interviewMode),
    SET_SCREEN_MODE: (state, screenMode) => (state.screenMode = screenMode)
  },
  actions: {
    changeInterviewUser({ commit }, interviewUser) {
      commit("SET_INTERVIEW_USER", interviewUser);
    },
    changeInterviewUserFixed({ commit }, interviewUserFixed) {
      commit("SET_INTERVIEW_USER_FIXED", interviewUserFixed);
    },
    needToFixPosture({ commit }, needInfo) {
      commit("SET_FIX_POSTURE", needInfo);
    },
    stopToFixPosture({ commit }) {
      commit("SET_FIX_POSTURE", "");
    },
    saveRecordedFile({ commit }, recordingInfo) {
      const recordingPostReq = recordingInfo[0];
      const stdNo = recordingInfo[1];
      console.log(recordingPostReq);
      console.log(stdNo);
      axios({
        url: api_url.recording.saveRecorded() + `?stdNo=${stdNo}`,
        method: "post",
        data: recordingPostReq
      })
        .then(res => {
          console.log(res.data);
          console.log("정상적으로 작동을 합니다.");
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    changeInterviewMode({ commit }, interviewMode) {
      commit("SET_INTERVIEW_MODE", interviewMode);
    },
    changeScreenMode({ commit }, screenMode) {
      commit("SET_SCREEN_MODE", screenMode);
    }
  }
};
