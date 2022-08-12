import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    studySpaceList: [],
    studySpaceDetail: {},
    token: localStorage.getItem("token") || "",
    resumeQuestionList: [],
    studentIndex: null,
    coverLetter: false,
    memberList: true,
    selectedQuestionNum: 0,
    selStdNo: Number,

    /**예상 질문 리스트 */
    expectedQuestionList: []
  },

  getters: {
    authHeader: state => ({ Authorization: `Bearer ${state.token}` }),
    studySpaceList: state => state.studySpaceList,
    studySpaceDetail: state => state.studySpaceDetail,
    resumeQuestionList: state => state.resumeQuestionList,
    studentIndex: state => state.studentIndex,
    coverLetter: state => state.coverLetter,
    memberList: state => state.memberList,
    selStdNo: state => state.selStdNo,
    selectedQuestionNum: state => state.selectedQuestionNum,
    expectedQuestionList: state => state.expectedQuestionList
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_STUDYSPACE_LIST: (state, studySpaceList) =>
      (state.studySpaceList = studySpaceList),
    SET_STUDYSPACE_DETAIL: (state, studySpaceDetail) =>
      (state.studySpaceDetail = studySpaceDetail),
    RESET_RESUME_QUESTION_LIST: state => (state.resumeQuestionList = []),
    ADD_RESUME_QUESTION_LIST: (state, data) =>
      state.resumeQuestionList.push(data),
    SET_STUDENT_INDEX: (state, studentIndex) =>
      (state.studentIndex = studentIndex),
    SET_LETTER_STATE: (state, coverLetter) => (state.coverLetter = coverLetter),
    SET_MEMBER_LSIT_STATE: (state, memberList) =>
      (state.memberList = memberList),
    SET_STD_NO: (state, data) => (state.selStdNo = data),
    SET_SELECTED_QUESTION_NUM: (state, data) =>
      (state.selectedQuestionNum = data),
    SET_EXPECTED_QUESTION_LIST: (state, data) =>
      (state.expectedQuestionList = data)
  },
  actions: {
    async changeToCoverLetter({ commit, dispatch }, changeInfo) {
      const content = changeInfo[0];
      const studentindex = changeInfo[1];

      if (content === "coverletter") {
        await dispatch("getStudyResume", studentindex);
        commit("SET_LETTER_STATE", true);
        commit("SET_MEMBER_LSIT_STATE", false);
      } else if (content === "memberlist") {
        commit("SET_LETTER_STATE", false);
        commit("SET_MEMBER_LSIT_STATE", true);
      }
    },
    async updateStudyNoticeAction({ getters }) {
      console.log(getters.studySpaceDetail);
      const data = {
        newStdNotice: getters.studySpaceDetail.stdNotice
      };
      axios
        .put(api_url.study.studyNotice(getters.selStdNo), data)
        .then(() => {
          console.log("공지사항 변경에 성공했습니다.");
        })
        .catch(() => {
          console.log("공지사항 변경에 실패했습니다.");
        });
    },
    updateStudyAction({ commit, getters }, studyUpdateInfo) {
      console.log(studyUpdateInfo);
      const stdNo = studyUpdateInfo[0];
      const stdInfo = studyUpdateInfo[1];

      console.log("키 값 확인");
      for (let key of stdInfo.keys()) {
        console.log(key);
      }

      console.log("벨류 값 확인");
      for (let value of stdInfo.values()) {
        console.log(value);
      }
      axios({
        url: api_url.study.studyspacedetail(stdNo),
        method: "put",
        data: stdInfo,
        headers: {
          Authorization: getters.memberHeader,
          "Context-Type": "multipart/form-data"
        }
      })
        .then(res => {
          console.log(res.data);
          commit("SET_STUDYSPACE_DETAIL", res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    async saveStudyCoverLetter({ getters }, data) {
      console.log(getters.studySpaceList);
      const studentindex = data.studentindex;
      const resumeNo = data.resumeNo;
      const stdNo = getters.studySpaceList[studentindex].stdNo;
      console.log(stdNo + " : " + resumeNo);
      await axios
        .put(
          api_url.study.studyCoverLetter(stdNo, resumeNo),
          {},
          {
            headers: getters.authHeader
          }
        )
        .then(() => {
          console.log(`자소서 변경을 성공했습니다.`);
        })
        .catch(err => {
          console.log(err);
        });
    },
    async getStudyResume({ getters, commit }, studentindex) {
      commit("RESET_RESUME_QUESTION_LIST");
      commit("SET_STUDENT_INDEX", studentindex);
      console.log(getters.studySpaceDetail);
      const detailCount = getters.studySpaceDetail.detailCounts[studentindex];
      const resumeNo =
        getters.studySpaceDetail.studyJoins[studentindex].resumeNo;
      console.log(detailCount + " : " + resumeNo);
      for (var i = 0, j = 0; i < detailCount; j++) {
        const data = {
          itemNo: j,
          resumeNo: resumeNo
        };
        await axios
          .get(api_url.resumes.getResumeDetail(), {
            params: data,
            headers: getters.authHeader
          })
          .then(data => {
            console.log(data);
            const res = {
              question: data.data.item,
              answer: data.data.answer,
              itemNo: data.data.itemNo,
              detailNo: data.data.detailNo
            };
            console.log(res);
            i++;
            commit("ADD_RESUME_QUESTION_LIST", res);
          })
          .catch(err => {
            console.log(err);
          });
      }
      console.log(getters.resumeQuestionList);
    },
    async createStudySpace({ commit, state, getters }, recruitNo) {
      console.log(recruitNo);
      const recruitNoForURL = `?recruitNo=${recruitNo}`;
      console.log(api_url.study.studyspace() + recruitNoForURL);
      await axios({
        url: api_url.study.studyspace() + recruitNoForURL,
        method: "post",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          console.log(res.data.stdNo);
          commit("SET_STD_NO", res.data.stdNo);
          // dispatch("bringStudySpace");
        })
        .catch(err => {
          console.log(err.response);
          alert("이미 스터디를 시작하셨습니다.");
        });
    },
    bringStudySpace({ commit, getters }) {
      console.log("확인");
      axios({
        url: api_url.study.studyspace(),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log("스터디 스페이스 리스트!!");
          console.log(res.data);
          commit("SET_STUDYSPACE_LIST", res.data.studies);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    async bringStudySpaceDetail({ commit, getters }, stdNo) {
      await axios({
        url: api_url.study.studyspacedetail(stdNo),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          commit("SET_STUDYSPACE_DETAIL", res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    kickMember({ getters }, stdMemberInfo) {
      const forURL = `?stdNo=${stdMemberInfo.stdNo}&userNo=${stdMemberInfo.userNo}`;
      axios({
        url: api_url.study.studyspace() + forURL,
        method: "delete",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    leaveStudy({ getters }, stdNo) {
      console.log(api_url.study.studyspacedetail(stdNo));
      axios({
        url: api_url.study.studyspacedetail(stdNo),
        method: "delete",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          router.push({ name: "studyrecruitment" });
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    deleteStudySpace({ dispatch }, deleteInfo) {
      const stdNo = deleteInfo[0];
      const memberNo = deleteInfo[1];
      if (memberNo < 1) {
        axios({
          url: api_url.study.deleteStudySpace(stdNo),
          method: "delete"
        })
          .then(res => {
            console.log(res.data);
            dispatch("bringStudySpace");
          })
          .catch(err => {
            console.log(err.response);
          });
      }
    },
    setStudyNoAction({ commit }, data) {
      commit("SET_STD_NO", data);
    },
    getQuestionList({ commit, getters }) {
      const params = {
        detailNo:
          getters.resumeQuestionList[getters.selectedQuestionNum].detailNo,
        stdNo: getters.selStdNo
      };
      axios
        .get(api_url.study.expectedQuestion(), {
          params: params
        })
        .then(data => {
          commit("SET_EXPECTED_QUESTION_LIST", data);
        })
        .catch(() => {
          console.log("예상 질문을 가져오는데 실패했습니다.");
        });
    }
  }
};
