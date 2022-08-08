import axios from "axios";
import api_url from "@/api/api_url";
import router from "@/common/lib/vue-router";
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    studySpaceList : [],
    studySpaceDetail : {},
    token: localStorage.getItem('token') || '' ,
    resumeQuestionList : [],
    studentIndex : null,
    coverLetter : false,
    memberList : true,
    selectedQuestionNum: 0,
    selStdNo: Number,
    studentIndex: null,
    coverLetter: false,
    memberList: true
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
    selectedQuestionNum: state => state.selectedQuestionNum
  },

  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
    SET_RECRUITMENT_LIST: (state, studySpaceList) =>
      (state.studySpaceList = studySpaceList),
    SET_RECRUIT_DETAIL: (state, studySpaceDetail) =>
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
      (state.selectedQuestionNum = data)
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
    createStudySpace({ commit, state, dispatch }, recruitNo) {
      const recruitNoForURL = `?recruitNo=${recruitNo}`;
      console.log(api_url.study.studyspace() + recruitNoForURL);
      axios({
        url: api_url.study.studyspace() + recruitNoForURL,
        method: "post"
      })
        .then(res => {
          console.log(res.data);
          dispatch("bringStudySpace");
        })
        .catch(err => {
          console.log(err.response);
          alert("이미 스터디를 시작하셨습니다.");
        })
        .then(res => {
          console.log(res.data);
        })
        .catch(err => {
          console.log(err.response);
          alert("이미 스터디를 시작하셨습니다.");
        });
    },
    bringStudySpace({ commit, getters }) {
      axios({
        url: api_url.study.studyspace(),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUITMENT_LIST", res.data.studies);
        })
        .catch(err => {
          console.log(err.response);
        });
    },
    async bringStudySpaceDetail({ commit, getters }, stdSpaceInfo) {
      await axios({
        url: api_url.study.studyspacedetail(stdSpaceInfo),
        method: "get",
        headers: getters.authHeader
      })
        .then(res => {
          console.log(res.data);
          commit("SET_RECRUIT_DETAIL", res.data);
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
    }
  }
};
