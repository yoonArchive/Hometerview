import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";
import { OpenVidu } from "openvidu-browser";

export default {
  state: {
    interviewUser: "",
    interviewUserFixed: undefined,
    mainStreamManager: undefined
  },
  getters: {
    interviewUser: state => state.interviewUser,
    interviewUserFixed: state => state.interviewUserFixed,
    mainStreamManager: state => state.mainStreamManager
  },
  mutations: {
    SET_INTERVIEW_USER: (state, interviewUser) =>
      (state.interviewUser = interviewUser),
    SET_INTERVIEW_USER_FIXED: (state, interviewUserFixed) =>
      (state.interviewUserFixed = interviewUserFixed),
    SET_MAIN_STREAM_MANAGER: (state, mainStreamManager) => {
      state.mainStreamManager = mainStreamManager;
    }
  },
  actions: {
    changeInterviewUser({ commit }, interviewUser) {
      commit("SET_INTERVIEW_USER", interviewUser);
    },
    changeInterviewUserFixed({ commit }, interviewUserFixed) {
      commit("SET_INTERVIEW_USER_FIXED", interviewUserFixed);
    },
    updateMainVideoStreamManager({ commit, getters }, stream) {
      if (getters.mainStreamManager === stream) return;
      commit("SET_MAIN_STREAM_MANAGER", stream);
    }
  }
};
