<template>
  <div class="row">
    <div class="col-5">
      <img :src="imgsrc" alt="" class="study-image" />
    </div>
    <div class="col-7">
      <div class="study-information-wrapper information-box">
        <div class="study-title-wrapper">
          <span v-if="studySpaceDetail.comName != null"
            >[{{ studySpaceDetail.comName }}]</span
          >{{ studySpaceDetail.stdName }}
        </div>
        <div class="study-space-content-wrapper">
          <div>
            타입 : <span v-if="studySpaceDetail.stdType == 'COM'">기업면접</span
            ><span v-else>자율면접</span>
          </div>
          <div>
            기간 : {{ studySpaceDetail.startDate }} ~
            {{ studySpaceDetail.endDate }}
          </div>
          <div>진행 일자 : {{ studySpaceDetail.stdDay }}</div>
          <div>모집 인원 : {{ studySpaceDetail.stdLimit }}</div>
          <div class="d-flex flex-row-reverse">
            <button
              @click="moveToSession()"
              class="study-space-btn"
              style="background-color:#653FD3;"
            >
              스터디 입장하기
            </button>
            <div v-if="studySpaceDetail.joinType == 'LEADER'">
              <button
                @click="changeStudyInfomation()"
                class="study-space-btn"
                style="background-color:#A6A6A6"
              >
                수정하기
              </button>
            </div>
            <div v-else>
              <button
                @click="leaveStudy(stdNo)"
                class="study-space-btn bg-danger"
              >
                스터디 탈퇴
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="study-detail-information-wrapper">
    <div class="introduce-study-wrapper">
      <div
        class="study-title-wrapper"
        style="border-radius:15px 15px 0 0; border-bottom: 1px black solid;"
      >
        스터디 소개
      </div>
      <div class="study-space-content-wrapper">
        {{ studySpaceDetail.stdDetail }}
      </div>
    </div>
  </div>
  <div class="study-detail-information-wrapper">
    <h2 style="color:#653FD3">공지사항</h2>
    <div class="form-floating">
      <textarea
        class="form-control autoTextarea"
        placeholder="Leave a comment here"
        id="floatingTextarea"
        v-model="studySpaceDetail.stdNotice"
        @keyup="autoResizeTextarea"
        @keydown="autoResizeTextarea"
        @focusout="updateStudyNotice"
      >
      </textarea>
    </div>
  </div>
  <div class="study-detail-information-wrapper">
    <h2 style="color:#653FD3">스터디 멤버</h2>
    <hr style="color:#653FD3" />
    <study-members
      v-for="(member, index) in studySpaceDetail.studyJoins"
      :key="member.joinNo"
      :member="member"
      :studentindex="index"
    ></study-members>
  </div>
</template>

<script>
import router from "@/common/lib/vue-router";
import { mapActions, mapGetters, mapMutations } from "vuex";
import StudyMembers from "./StudyMembers.vue";
export default {
  name: "StudySpaceInfo",

  components: {
    StudyMembers
  },
  data() {
    return {
      stdNo: this.$route.params.stdNo,
      imgsrc: require("@/assets/images/ssafy-logo.png")
    };
  },
  computed: {
    ...mapGetters(["studySpaceDetail"])
  },
  methods: {
    ...mapActions([
      "bringStudySpaceDetail",
      "leaveStudy",
      "updateStudyNoticeAction"
    ]),
    ...mapMutations(["SET_STD_NO"]),
    moveToSession() {
      router.push({
        name: "session",
        params: { sessionNo: this.stdNo }
      });
    },
    autoResizeTextarea(e) {
      let textarea = document.querySelector(".autoTextarea");
      console.log(e + " : " + textarea);
      if (textarea) {
        textarea.style.height = "auto";
        let height = textarea.scrollHeight; // 높이
        textarea.style.height = `${height + 8}px`;
      }
    },
    updateStudyNotice() {
      this.updateStudyNoticeAction();
    }
  },
  created() {
    this.SET_STD_NO(this.stdNo);
    this.bringStudySpaceDetail(this.stdNo);
    // await this.deleteStudySpace([this.stdNo])
  }
};
</script>

<style>
.study-detail-information-wrapper {
  margin-top: 50px;
}
.introduce-study-wrapper {
  border: 1px black solid;
  border-radius: 15px;
}
.study-space-btn {
  border-radius: 30px;
  color: #ffffff;
  font-size: 20px;
  font-weight: 400;
  margin-left: 3%;
  border: none;
}
.study-space-content-wrapper {
  padding: 5%;
}
.study-title-wrapper {
  background-color: #f3f4ff;
  padding: 5%;
  border-radius: 30px 30px 0 0;
  width: 100%;
}

.study-image {
  width: 100%;
}
.study-information-wrapper {
  border: 1px black solid;
}
.information-box {
  width: 100%;
  background: #ffffff;
  box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.box1 {
  width: 400px;
  height: 50px;
  background: #f3f4ff;
  border-radius: 10px;
}
</style>
