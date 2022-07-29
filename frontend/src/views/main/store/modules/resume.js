import axios from 'axios'
import api_url from '@/api/api_url'
import http from '@/api/api_url'
import router from "@/common/lib/vue-router.js";
import { toRaw } from 'vue';



export default {
  // namespaced: true,
  state: {
    token: localStorage.getItem('token') || '' ,
    // 자소서 갯수
    numberOfResume : 0,
    // 자소서 정보 저장( 자소서 번호, 타이틀)
    resumeContents : [],
    // 자소서 질문 정보
    currentResume: [],
    // 자소서 질문 갯수(마지막 인덱스)
    numberOfQuestion : [],
    // 선택된 자소서
    resumeIndex : 0,
    // 원래 자소서 질문 갯수(실제)
    originalNumberQuestion : 0,

    isLodding:false,
  },

  getters: {
    currentResume: state => state.currentResume,
    resumeContents: state => state.resumeContents,
    numberOfResume : state => state.numberOfResume,
    numberOfQuestion : state => state.numberOfQuestion,
    resumeIndex : state=> state.resumeIndex,
    originalNumberQuestion : state=> state.originalNumberQuestion,
    resumeHeader: state => ({ Authorization: `Bearer ${state.token}`}),
    isLodding : state => state.isLodding,
  },

  mutations: {
    SET_TOKEN(state,data){
      state.token = data;
    },
    SET_IS_LODDING(state,data){
      state.isLodding = data;
    },
    RESET_CURRENT_RESUME(state){
      state.currentResume = [];
    },
    RESET_ORIGINAL_NUMBER_QUESTION(state){
      state.originalNumberQuestion = 0;
    },
    PLUS_ORIGINAL_NUMBER_QUESTION(state){
      state.originalNumberQuestion++;
    },
    PLUS_NUMBER_OF_QUESTION(state, index){
      state.numberOfQuestion[index] ++;
    },
    SET_ORIGINAL_NUMBER_QUESTION(state, data){
      state.originalNumberQuestion = data;
    },
    SET_RESUME_INDEX(state, data){
      state.resumeIndex = data;
    },
    SET_RESUME_CONTENTS(state, data){
      state.resumeContents = data;
    },
    ADD_CURRENT_RESUME(state, data){
      state.currentResume.push(data);
    },
    SET_NUMBER_RESUME(state, data){
      state.numberOfResume = data;
    },
    SET_NUMBER_QUESTION(state, data){
      state.numberOfQuestion = data;
    },
    ADD_ITEM_CURRENT_RESUME(state){
      state.currentResume.push({question : "", answer : "" , detailNo : Number});
    }
  },

  actions: {
    async addNewResume({dispatch, getters}){
      const titleOfResume = "새 자기소개서";
      const data = {
        resumeTitle : titleOfResume,
      }
      await axios.post(api_url.resumes.getResumeInfo(),{},{
        params: data,
        headers : getters.resumeHeader,
      })
      dispatch('getResumeInfo');
    },
    removeResumeAction({getters}, resumeindex){
      const resumeNo =  getters.resumeContents[resumeindex].resumeNo;
      const data = {
        resumeNo : resumeNo,
      }
      axios.delete(api_url.resumes.getResumeInfo(),{
        params: data,
        headers : getters.resumeHeader,
      }).then(()=>{
        console.log("자소서 삭제 성공")
      }).catch((error)=>{
        console.log(error);
      })
      router.push({name : 'myinterview'});
    },
    changeNewResumeTitle({dispatch, getters}, resumeindex){
      const resumeNo = getters.resumeContents[resumeindex].resumeNo;
      const resumeTitle = getters.resumeContents[resumeindex].resumeTitle;
      const data = {
        resumeNo : resumeNo,
        resumeTitle : resumeTitle,
      }
      axios.put(api_url.resumes.getResumeInfo(), {} , {
        headers : getters.resumeHeader,
        params : data,
      }).then(()=>{
        console.log("자소서 제목 수정 성공");
        // dispatch('getResumeInfo');
      }).catch((err)=>{
        console.log(err);
      })

    },
    addItemCurrentResume({commit}){
      commit('ADD_ITEM_CURRENT_RESUME');
    },
    async removeItemCueentResume({ getters, dispatch }, index){
      const currentResume = getters.currentResume;
      const originalNumberQuestion = getters.originalNumberQuestion;
      var detailNo = 0;
      for(var i=0;i<originalNumberQuestion;i++){
        if(i == index){
          detailNo = currentResume[i].detailNo;
        }
      }
      await axios.delete(api_url.resumes.getResumeInfo() + detailNo,{
        headers : getters.resumeHeader,
      }).then(()=>{
        console.log("데이터 삭제 완료");
      }).catch((err)=>{
        console.log(err);
      })
      await dispatch('getCurrentResume',getters.resumeIndex);
    },
    async saveResumeChange({dispatch, getters, commit}){
      const originalNumberQuestion = getters.originalNumberQuestion;
      const currentResume = getters.currentResume;
      console.log(originalNumberQuestion);
      for(var i = 0; i < originalNumberQuestion; i++){
        const detailNo = currentResume[i].detailNo
        const data = {
          answer : currentResume[i].answer,
          item : currentResume[i].question,
        }
        await axios.put(api_url.resumes.getResumeInfo() + detailNo, data, {
          headers : getters.resumeHeader,
        }).then(()=>{
          console.log("데이터 수정 성공");
        }).catch((err)=>{
          console.log(err);
        })
      }
      for(var i = originalNumberQuestion; i < currentResume.length; i++){
        var resumeIndex = getters.resumeIndex;
        var numberOfQuestion = getters.numberOfQuestion[resumeIndex] + 1;
        var resumeNo = getters.resumeContents[resumeIndex].resumeNo;
        const data = {
          answer : currentResume[i].answer,
          item : currentResume[i].question,
          itemNo : numberOfQuestion,
          resumeNo : resumeNo,
        }
        await axios.post(api_url.resumes.getResumeDetail(), data, {
          headers : getters.resumeHeader,
        }).then(()=>{
          console.log("데이터 추가 성공")
        }).catch((err)=>{
          console.log(err);
        })
        commit('PLUS_NUMBER_OF_QUESTION',resumeIndex);
        commit('PLUS_ORIGINAL_NUMBER_QUESTION')
      }
      await dispatch('getCurrentResume',getters.resumeIndex);
    },
    settingResumeIndex({commit}, data){
      commit('SET_RESUME_INDEX',data);
    },
    async getResumeInfo({commit, getters}){
      await axios.get(api_url.resumes.getResumeInfo(), {
        headers : getters.resumeHeader,
      }).then((data)=>{
        var length = data.data.resumes.length;
        console.log(data.data.resumes);

        commit('SET_RESUME_CONTENTS', data.data.resumes);
        commit('SET_NUMBER_RESUME', length);
        commit('SET_NUMBER_QUESTION',data.data.detailCounts);
      }).catch((err)=>{
        console.log(err);
      })
    },
    async getCurrentResume({commit , getters}, index){
      console.log(getters.resumeContents);
      var length  = getters.numberOfQuestion[index];
      var resumeNo = getters.resumeContents[index].resumeNo;
      console.log(index);

      commit('RESET_CURRENT_RESUME');
      commit('RESET_ORIGINAL_NUMBER_QUESTION');
      for(var i = 1, j = 1;i<=length;j++){
        const data = {
          resumeNo : resumeNo,
          itemNo : j,
        }
        await axios.get(api_url.resumes.getResumeDetail(),{
          params : data,
          headers : getters.resumeHeader,
        }).then((result)=>{
          console.log(result);
          const res = {
            detailNo : result.data.detailNo,
            question : result.data.item,
            answer : result.data.answer,
          }
          i++;
          commit('ADD_CURRENT_RESUME',res);
          commit('PLUS_ORIGINAL_NUMBER_QUESTION');
          console.log(getters.currentResume);
        }).catch((err)=>{
          // console.log(data);
          console.log(err);
        })
      }
      commit('SET_IS_LODDING',true);
      console.log(getters.currentResume[0]);
    }

  },
}
