import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";
import { OpenVidu } from "openvidu-browser";

export default {
  state: {
    interviewUser: "",
    interviewUserFixed: undefined,
    posture: ""
  },
  getters: {
    interviewUser: state => state.interviewUser,
    interviewUserFixed: state => state.interviewUserFixed,
    posture: state => state.posture
  },
  mutations: {
    SET_INTERVIEW_USER: (state, interviewUser) =>
      (state.interviewUser = interviewUser),
    SET_INTERVIEW_USER_FIXED: (state, interviewUserFixed) =>
      (state.interviewUserFixed = interviewUserFixed),
    SET_FIX_POSTURE: (state, posture) => (state.posture = posture)
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
      console.log("정상적으로 작동을 합니다.");
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
    }
  }
};
