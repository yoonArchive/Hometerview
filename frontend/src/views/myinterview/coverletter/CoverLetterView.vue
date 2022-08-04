<template>
  <div v-if="!isloading">
    자소서 번호 : {{resumeContents.length}}
    <div v-if="!isChange">
      <div>
      자소서 제목 : {{resumeContents[resumeindex].resumeTitle}}
      </div>
      <div id="button-bundels">
      <button @click="changeIsChange" id="b-button">제목 수정</button>
      <button @click="removeResume(resumeindex)" id="b-button">자소서 삭제</button>
      <router-link :to="{name : 'myinterview'}">
      <button id="b-button">목록으로</button>
      </router-link>
      </div>
    </div>
    <div v-else>
      <input type="text" v-model="resumeContents[resumeindex].resumeTitle"/>
      <button @click="changeResumeTitle(resumeindex)">수정</button>
      <button @click="cancelChange">취소</button>
    </div>
    <div>

    </div>
    <cover-letter-detail :resumeindex="resumeindex"></cover-letter-detail>


  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import CoverLetterDetail from './components/CoverLetterDetail.vue'
export default {
  data(){
    return{
      isloading : true,
      resumeindex : this.$route.params.resumeindex,
      isChange:false,
      originaltitle : "",
    }
  },

  async mounted(){
    await this.getResumeInfo();
    await this.getCurrentResume(this.resumeindex);
    await this.settingResumeIndex(this.resumeindex);
    this.originaltitle = this.resumeContents[this.resumeindex].resumeTitle;
    this.isloading = false;
  },
  methods:{
    ...mapActions(['settingResumeIndex','getCurrentResume','getResumeInfo','changeNewResumeTitle', 'removeResumeAction']),
    removeResume(resumeindex){
      this.removeResumeAction(resumeindex);
    },
    changeIsChange(){
      this.originaltitle = this.resumeContents[this.resumeindex].resumeTitle;
      this.isChange = true;
    },
    cancelChange(){
      this.resumeContents[this.resumeindex].resumeTitle = this.originaltitle;
      this.isChange = false;
    },
    changeResumeTitle(resumeindex){
      this.changeNewResumeTitle(resumeindex);
      this.isChange = false;
    }
  },
  components:{
    CoverLetterDetail,
  },

  computed:{
    ...mapGetters(['resumeContents', 'currentResume']),
  },

}
</script>

<style>

#button-bundels{

}
#b-button{
  background-color: blueviolet;
  color: white;
  border-radius: 10%;
  outline: 0;
  border: 0ch;
}
#b-button:hover{

    background-color: #9779f0;

  }
#b-button:active{
    background-color: #7455d1;
  }
</style>
