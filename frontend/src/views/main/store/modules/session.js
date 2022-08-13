import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";
import { OpenVidu } from "openvidu-browser";

export default {
  state: {
    interviewUser: "",
    interviewUserFixed: undefined,
    posture: false
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
      commit("SET_FIX_POSTURE", false);
    }
  }
};
