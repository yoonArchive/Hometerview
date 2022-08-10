<template>
  <div class="container">
    <header>
      <h3 style="text-align: center; ">
        [ {{ studySpaceDetail.stdName }} ] 스터디 스페이스
      </h3>

      <!-- <span class="title" v-if="studySpaceDetail.stdType == 'COM'" style="text-align: center; ">
        [ 기업 면접 스터디 ]
        <span class="comName"> {{ studySpaceDetail.comName }}</span>
      </span>
      <span class="title" v-else style="text-align: center;">[ 자율 면접 스터디 ]</span> -->
    </header>
    <section id="banner">
      <span class="image object">
        <img :src="imgsrc" alt="" class="stdImg" style="width:550px" />
      </span>
      <div class="content">
        <blockquote>
          <div class="info">
            <span
              class="title"
              v-if="studySpaceDetail.stdType == 'COM'"
              style="text-align: center; "
            >
              [ 기업 면접 스터디 ]
              <span class="comName"> {{ studySpaceDetail.comName }}</span>
            </span>
            <span class="title" v-else style="text-align: center;"
              >[ 자율 면접 스터디 ]</span
            >
          </div>
          <div class="info">
            <span class="detail">{{ studySpaceDetail.stdDetail }}</span>
          </div>
        </blockquote>

        <div class="box">
          <div class="info">
            <span class="detail">🗓️ &nbsp; 기간 : </span>
            <span
              >{{ studySpaceDetail.startDate }} ~
              {{ studySpaceDetail.endDate }}</span
            >
          </div>
          <div class="info">
            <span class="detail">⏰ &nbsp; 진행 일자 : </span>
            <span>{{ studySpaceDetail.stdDay }}</span>
          </div>
          <div v-if="studySpaceDetail.recruitStatus === '모집 중'" class="info">
            <span class="detail">🤝 &nbsp; 모집인원 : </span>
            <span
              >{{ studySpaceDetail.count }}/{{
                studySpaceDetail.stdLimit
              }}</span
            >
          </div>
        </div>
        <div>
          <div v-if="applyType === 'LEADER'">
            <button @click="studyStart()">스터디 시작</button>
            <button @click="moveToUpdate">수정</button>
            <button @click="deleteRecruitmentDetail([recruitNo])">
              삭제
            </button>
          </div>
          <div
            v-else-if="
              applyType === 'NORMAL' &&
                studySpaceDetail.recruitStatus === '모집 중'
            "
          >
            <button @click="studyApplyCancel(recruitNo)">
              스터디 신청 취소
            </button>
          </div>
          <div v-else-if="applyType === null">
            <div v-if="recruitDetail.recruitStatus === '모집 중'">
              <button @click="studyApply(recruitNo)">스터디 신청하기</button>
            </div>
            <div v-else>
              <button @click="goStudySpace()">
                스터디 스페이스 이동
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
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
      imgsrc: require("@/assets/images/fighting.jpeg")
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

<style scoped>
/* .study-detail-information-wrapper {
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
} */
h3,
h5 {
  font-family: "티머니 둥근바람 볼드";
}
h3 {
  margin-bottom: 25px;
}
.status {
  font-size: 25px;
  font-weight: 600;
  font-family: "티머니 둥근바람";
  color: #ffffff;
  border-radius: 35px;
  padding: 10px;
}
.ing {
  background-color: #5fc971;
}
.completed {
  background-color: #4b4b4b;
}
.title {
  font-weight: 600;
  font-size: 25px;
}
.info {
  margin-bottom: 12px;
}
.comName {
  color: #f56a6a;
}
blockquote {
  quotes: none;
  border-left: solid 3px rgba(210, 215, 217, 0.75);
  /* font-style: italic; */
  margin: 0 0 1.7em 0;
  padding: 0.5em 0 0.5em 2em;
}
#banner {
  padding: 3em 0 2em 0;
  display: -moz-flex;
  display: -webkit-flex;
  display: -ms-flex;
  display: flex;
}
#banner h1 {
  margin-top: -0.125em;
}
#banner .content {
  -moz-flex-grow: 1;
  -webkit-flex-grow: 1;
  -ms-flex-grow: 1;
  flex-grow: 1;
  -moz-flex-shrink: 1;
  -webkit-flex-shrink: 1;
  -ms-flex-shrink: 1;
  flex-shrink: 1;
  width: 50%;
}
#banner .image {
  -moz-flex-grow: 0;
  -webkit-flex-grow: 0;
  -ms-flex-grow: 0;
  flex-grow: 0;
  -moz-flex-shrink: 0;
  -webkit-flex-shrink: 0;
  -ms-flex-shrink: 0;
  flex-shrink: 0;
  display: block;
  margin: 0 0 2em 4em;
  width: 50%;
}
#banner .image img {
  height: 100%;
  -moz-object-fit: cover;
  -webkit-object-fit: cover;
  -ms-object-fit: cover;
  object-fit: cover;
  -moz-object-position: center;
  -webkit-object-position: center;
  -ms-object-position: center;
  object-position: center;
  width: 100%;
}
@media screen and (orientation: portrait) {
  #banner {
    -moz-flex-direction: column-reverse;
    -webkit-flex-direction: column-reverse;
    -ms-flex-direction: column-reverse;
    flex-direction: column-reverse;
  }
  #banner h1 br {
    display: none;
  }
  #banner .content {
    -moz-flex-grow: 0;
    -webkit-flex-grow: 0;
    -ms-flex-grow: 0;
    flex-grow: 0;
    -moz-flex-shrink: 0;
    -webkit-flex-shrink: 0;
    -ms-flex-shrink: 0;
    flex-shrink: 0;
    width: 100%;
  }
  #banner .image {
    -moz-flex-grow: 0;
    -webkit-flex-grow: 0;
    -ms-flex-grow: 0;
    flex-grow: 0;
    -moz-flex-shrink: 0;
    -webkit-flex-shrink: 0;
    -ms-flex-shrink: 0;
    flex-shrink: 0;
    margin: 0 0 0 4em;
    height: 25em;
    max-height: 50vh;
    min-height: 18em;
    width: 100%;
  }
}
@media screen and (orientation: portrait) and (max-width: 480px) {
  #banner .image {
    max-height: 35vh;
  }
}
section.special,
article.special {
  text-align: center;
}

header p {
  font-family: "Roboto Slab", serif;
  font-size: 1em;
  font-weight: 400;
  letter-spacing: 0.075em;
  margin-top: -0.5em;
  text-transform: uppercase;
}

header.major > :last-child {
  border-bottom: solid 3px #653fd3;
  display: inline-block;
  margin: 0 0 2em 0;
  padding: 0 0.75em 0.5em 0;
}

header.main > :last-child {
  margin: 0 0 1em 0;
}
.image {
  border-radius: 0.375em;
  border: 0;
  display: inline-block;
  position: relative;
}
.image img {
  border-radius: 0.375em;
  display: block;
}
.image.fit {
  display: block;
  margin: 0 0 2em 0;
  width: 100%;
}
.image.fit img {
  width: 100%;
}
.image.main {
  display: block;
  margin: 0 0 3em 0;
  width: 100%;
}
.image.main img {
  width: 100%;
}
.image :hover {
  transform: scale(1.03);
}
.box {
  border-radius: 0.375em;
  border: solid 2px rgba(210, 215, 217, 0.75);
  padding: 0.8em;
  margin: 0 0 1.7em 0;
  font-size: 20px;
}
.box > :last-child,
.box > :last-child > :last-child,
.box > :last-child > :last-child > :last-child {
  margin-bottom: 0;
}
input[type="button"],
button,
.button {
  -moz-appearance: none;
  -webkit-appearance: none;
  -ms-appearance: none;
  appearance: none;
  -moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  -ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
  background-color: transparent;
  border-radius: 0.375em;
  border: 0;
  box-shadow: inset 0 0 0 2px #653fd3;
  color: #653fd3 !important;
  cursor: pointer;
  display: inline-block;
  font-size: 0.8em;
  font-weight: 700;
  height: 3.5em;
  letter-spacing: 0.075em;
  line-height: 3.5em;
  padding: 0 2.25em;
  text-align: center;
  text-decoration: none;
  text-transform: uppercase;
  white-space: nowrap;
  margin: 0 0.5em 0 0;
}
input[type="button"]:hover,
button:hover,
.button:hover {
  background-color: rgba(161, 104, 253, 0.05);
}
input[type="button"]:active,
button:active,
.button:active {
  background-color: rgba(161, 104, 253, 0.15);
}
input[type="button"].icon:before,
button.icon:before,
.button.icon:before {
  margin-right: 0.5em;
}
input[type="button"].fit,
button.fit,
.button.fit {
  width: 100%;
}
input[type="button"].small,
button.small,
.button.small {
  font-size: 0.6em;
}
input[type="button"].large,
button.large,
.button.large {
  font-size: 1em;
  height: 3.65em;
  line-height: 3.65em;
}
input[type="button"].disabled,
input[type="button"]:disabled,
button.disabled,
button:disabled,
.button.disabled,
.button:disabled {
  pointer-events: none;
  opacity: 0.25;
}
</style>
