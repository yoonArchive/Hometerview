import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'
import accounts from '@/views/main/store/modules/accounts'
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    studySpaceList : [],
    studySpaceDetail : {},
    token: localStorage.getItem('token') || '' ,
    resumeQuestionList : [],
  },

  getters: {
    authHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    studySpaceList : state => state.studySpaceList,
    studySpaceDetail : state => state.studySpaceDetail,
    resumeQuestionList : state => state.resumeQuestionList,
    
  },

  mutations: {
    SET_TOKEN: (state, token) => state.token = token,
    SET_RECRUITMENT_LIST: (state,studySpaceList) => state.studySpaceList = studySpaceList,
    SET_RECRUIT_DETAIL : (state,studySpaceDetail) => state.studySpaceDetail = studySpaceDetail,
    RESET_RESUME_QUESTION_LIST: (state) =>state.resumeQuestionList = [],
    ADD_RESUME_QUESTION_LIST : (state, data) => state.resumeQuestionList.push(data),
  },

  actions: {
    saveStudyCoverLetter({getters}, data){
      console.log(getters.studySpaceList);
      const studentindex = data.studentindex;
      const resumeNo = data.resumeNo;
      const stdNo = getters.studySpaceList[studentindex].stdNo;
      axios.put(api_url.study.studyCoverLetter(stdNo,resumeNo),
      {},{
        headers : getters.authHeader,
      }).then(()=>{
        console.log(`자소서 변경을 성공했습니다.`);
      }).catch((err)=>{
        console.log(err);
      })
    },

    async getStudyResume({getters, commit},studentindex){
      commit('RESET_RESUME_QUESTION_LIST');
      console.log(getters.studySpaceDetail);

      const detailCount = getters.studySpaceDetail.detailCounts[studentindex];
      const resumeNo = getters.studySpaceDetail.studyJoins[studentindex].resumeNo;
      console.log(detailCount + " : " + resumeNo);
      for(var i = 0, j = 0; i < detailCount; j++){
        const data = {
          itemNo : j,
          resumeNo : resumeNo,
        }
        await axios.get(api_url.resumes.getResumeDetail(),{
            params: data,
        }).then((data)=>{
          console.log(data);
          const res = {
            question : data.data.item,
            answer : data.data.answer,
            itemNo : data.data.itemNo,
            detailNo : data.data.detailNo,
          }
          console.log(res);
          i++;
          commit('ADD_RESUME_QUESTION_LIST',res);
        }).catch((err)=>{
          console.log(err);
        })
      }
    },
    createStudySpace({commit, state},recruitNo){

      const recruitNoForURL = `?recruitNo=${recruitNo}`
      console.log(api_url.study.studyspace() + recruitNoForURL,)
      axios({
        url: api_url.study.studyspace() + recruitNoForURL,
        method: 'post'
      })
      .then(res => {
        console.log(res.data)
      })
      .catch(err => {
        console.log(err.response)
        alert('이미 스터디를 시작하셨습니다.')
      })
    },
    bringStudySpace({commit,getters}){
      axios({
        url: api_url.study.studyspace(),
        method: 'get',
        headers: getters.authHeader,
      })
      .then(res => {
        console.log(res.data)
        commit('SET_RECRUITMENT_LIST',res.data.studies )
      })
      .catch(err => {
        console.log(err.response)
      })
    },
<<<<<<< HEAD
    bringStudySpaceDetail({commit, getters},stdNo){
=======
    bringStudySpaceDetial({commit, getters},stdNo){
>>>>>>> 962b8b4603acd6fdf25f4436e6332b0c25470298

      axios({
        url:api_url.study.studyspacedetail(stdNo),
        method : 'get',
<<<<<<< HEAD
        headers: getters.authHeader,
=======
        headers:getters.authHeader
>>>>>>> 962b8b4603acd6fdf25f4436e6332b0c25470298
      })
      .then(res=>{
        console.log(res.data)
        commit('SET_RECRUIT_DETAIL',res.data)
      })
      .catch(err=>{
        console.log(err.response)
      })
    },
    kickMember({getters}, stdMemberInfo){
      const forURL = `?stdNo=${stdMemberInfo.stdNo}&userNo=${stdMemberInfo.userNo}`
      axios({
        url:api_url.study.studyspace()+forURL,
        method:'delete',
        headers:getters.authHeader
      })
      .then(res=>{
        console.log(res.data)
      })
      .catch(err=>{
        console.log(err.response)
      })
    },
    leaveStudy({getters}, stdNo){
      console.log(api_url.study.studyspacedetail(stdNo))
      axios({
        url:api_url.study.studyspacedetail(stdNo),
        method:'delete',
        headers:getters.authHeader
      })
      .then(res=>{
        console.log(res.data)
        router.push({name:'studyrecruitment'})
      })
      .catch(err=>{
        console.log(err.response)
      })
    },
    deleteStudySpace({dispatch},deleteInfo){
      const stdNo = deleteInfo[0]
      const memberNo = deleteInfo[1]
      if(memberNo<1){
        axios({
          url:api_url.study.deleteStudySpace(stdNo),
          method:'delete'
        })
        .then(res=>{
          console.log(res.data)
          dispatch('bringStudySpace')
        })
        .catch(err=>{
          console.log(err.response)
        })
      }
    }


  },
}
