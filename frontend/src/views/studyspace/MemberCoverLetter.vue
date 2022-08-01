<template>
  <div v-if="!isloading">

    <div v-if="isMyInfo">
      <div v-if="studySpaceDetail.detailCounts[studentindex]==0">
        자소서를 등록하여 스터디원들과 공유해보세요!
      </div>
      <button data-bs-toggle="modal" data-bs-target="#exampleModal">자소서 바꾸기!</button>
    </div>
    <div v-if="studySpaceDetail.detailCounts[studentindex]==0">
      아직 공유된 자소서가 없습니다!!
    </div>
    <member-cover-letter-detail :studentindex=studentindex></member-cover-letter-detail>
    {{isMyInfo}}

  </div>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">공유할 자소서</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div v-if="resumeContents.length == 0">
            자소서 페이지에서 자소서를 작성해보세요!
          </div>
          <select class="form-select" aria-label="Default select example" v-model="selresume" v-else>
            <option :value="item.resumeNo" v-for="(item, index) in resumeContents" :key="index">
              {{item.resumeTitle}}
            </option>
          </select>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
          <button type="button" class="btn btn-primary" @click="saveStudyCoverLetter(selresume,studentindex)">저장</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import MemberCoverLetterDetail from './components/MemberCoverLetterDetail.vue'
export default {
  data(){
    return{
      studentindex : this.$route.params.studentindex,
      isloading : true,
      originaltitle : "",
      selresume : Number,
    }
  },

  async mounted(){
    await this.getResumeInfo();
    await this.getStudyResume(this.studentindex);
    this.isloading = false;
  },
  methods:{
    ...mapActions(['getStudyResume','getResumeInfo','saveStudyCoverLetter']),

  },
  components:{
    MemberCoverLetterDetail,
  },

  computed:{
    ...mapGetters(['resumeQuestionList','currentUser','studySpaceDetail','resumeContents']),
    isMyInfo(){
      return this.currentUser.userId == this.studySpaceDetail.studyJoins[this.studentindex].user.userId;
    },
  },

}
</script>

<style>

</style>
