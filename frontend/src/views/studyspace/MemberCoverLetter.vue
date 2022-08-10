<template>
  <div v-if="!isloading" style="background-color: #F3F4FF;">
    <div v-if="isMyInfo">
      <div v-if="studySpaceDetail.detailCounts[studentindex] == 0">
        <div class="no-cover-letter-me">
          <div
            class="d-flex justify-content-between"
            style="align-items: center;"
          >
            <div style="font-size:20px">
              자소서를 등록하여 스터디원들과 공유해보세요!
            </div>
            <button
              type="button"
              class="btn"
              data-bs-toggle="modal"
              data-bs-target="#exampleModal"
              style="background-color:#653FD3; color: white;"
            >
              자소서 바꾸기
            </button>
          </div>
        </div>
      </div>
    </div>

    <div v-if="studySpaceDetail.detailCounts[studentindex] == 0 && !isMyInfo">
      아직 공유된 자소서가 없습니다!!
    </div>
    <div
      v-else-if="studySpaceDetail.detailCounts[studentindex] == 0 && isMyInfo"
    ></div>
    <div v-else>
      <div class="member-cover-letter-title">
        <div class="row">
          <div class="col-8">
            <div
              class="member-cover-letter-comname"
              v-if="studySpaceDetail.comName != null"
            >
              [{{ studySpaceDetail.comName }}]
            </div>
            <div class="member-cover-letter-stdname">
              {{ studySpaceDetail.stdName }} 자소서
            </div>
          </div>
          <div class="col" v-if="isMyInfo">
            <div class="d-flex flex-row-reverse">
              <button
                type="button"
                class="btn"
                data-bs-toggle="modal"
                data-bs-target="#exampleModal"
                style="background-color:#653FD3; color: white;"
              >
                자소서 바꾸기
              </button>
            </div>
          </div>
        </div>
      </div>
      <member-cover-letter-detail
        :studentindex="studentindex"
      ></member-cover-letter-detail>
    </div>
  </div>

  <!-- Modal -->
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">공유할 자소서</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <div v-if="resumeContents.length == 0">
            자소서 페이지에서 자소서를 작성해보세요!
          </div>
          <select
            class="form-select"
            aria-label="Default select example"
            v-model="selresume"
            v-else
          >
            <option value="0">자소서 없음!</option>
            <option
              :value="item.resumeNo"
              v-for="(item, index) in resumeContents"
              :key="index"
            >
              {{ item.resumeTitle }}
            </option>
          </select>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            취소
          </button>
          <button
            type="button"
            class="btn btn-primary"
            @click="saveStudyCoverLetterV(selresume)"
            data-bs-dismiss="modal"
          >
            저장
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import MemberCoverLetterDetail from "./components/MemberCoverLetterDetail.vue";
export default {
  data() {
    return {
      studentindex: this.$route.params.studentindex,
      isloading: true,
      originaltitle: "",
      selresume: Number
    };
  },
  beforeUnmount() {
    this.SET_MAIN_CONTAINER("main-container");
    this.SET_FULL_CONTAINER("full-container");
  },
  async mounted() {
    this.SET_MAIN_CONTAINER("main-container-coverletter");
    this.SET_FULL_CONTAINER("full-container-coverletter");

    console.log(this.studentindex);
    await this.getResumeInfo();
    const realindex = parseInt(this.studentindex);
    console.log(this.studySpaceDetail);
    if (this.studySpaceDetail.detailCounts[realindex] !== 0) {
      await this.getStudyResume(realindex);
    }
    await this.fetchCurrentUser();
    this.isloading = false;
    console.log(this.currentUser);
    console.log(
      this.currentUser.userId +
        " : " +
        this.studySpaceDetail.studyJoins[this.studentindex].user.userId
    );
  },
  methods: {
    ...mapActions([
      "getStudyResume",
      "getResumeInfo",
      "saveStudyCoverLetter",
      "fetchCurrentUser",
      "bringStudySpaceDetail"
    ]),
    ...mapMutations([
      "SET_MAIN_CONTAINER",
      "SET_FULL_CONTAINER",
      "SET_SELECTED_QUESTION_NUM"
    ]),
    async saveStudyCoverLetterV(selresume) {
      this.SET_SELECTED_QUESTION_NUM(0);
      const realindex = parseInt(this.studentindex);
      if (selresume != 0) {
        const data = {
          resumeNo: selresume,
          studentindex: this.studentindex
        };
        await this.saveStudyCoverLetter(data);
        await this.bringStudySpaceDetail();
        if (this.studySpaceDetail.detailCounts[realindex] !== 0) {
          await this.getStudyResume(realindex);
        }
      }
    }
  },
  components: {
    MemberCoverLetterDetail
  },

  computed: {
    ...mapGetters([
      "resumeQuestionList",
      "currentUser",
      "studySpaceDetail",
      "resumeContents"
    ]),
    isMyInfo() {
      return (
        this.currentUser.userId ==
        this.studySpaceDetail.studyJoins[this.studentindex].user.userId
      );
    }
  }
};
</script>

<style>
.no-cover-letter-me {
  padding: 5%;
  background-color: white;
  border: 1px solid black;
}
.member-cover-letter-comname {
  font-size: 20px;
}
.member-cover-letter-stdname {
  font-size: 30px;
}
</style>
<style scoped>
.main-container {
  background-color: #f3f4ff;
}
</style>
