<template>
  <div class="container">
    <header>
      <h3 style="text-align: center; ">
        <span
          v-if="recruitDetail.recruitStatus === '모집 중'"
          class="status ing"
          >{{ recruitDetail.recruitStatus }}</span
        >
        <span v-else class="status completed">{{
          recruitDetail.recruitStatus
        }}</span>
        {{ recruitDetail.recruitTitle }}
      </h3>
      <h5 v-if="recruitDetail.stdType == 'COM'" style="text-align: center; ">
        [ 기업 면접 스터디 ]
        <span class="comName"> {{ recruitDetail.comName }}</span>
      </h5>
      <h5 v-else style="text-align: center;">[ 자율 면접 스터디 ]</h5>
    </header>
    <!-- 여기부터 -->
    <section id="banner">
      <span class="image object">
        <img :src="image" alt="" class="stdImg" style="width:550px" />
      </span>

      <div class="content">
        <blockquote>
          <div class="info">
            <span class="title">스터디명 : </span
            ><span class="title">{{ recruitDetail.stdName }}</span>
          </div>
          <div class="info">
            <span class="detail">{{ recruitDetail.stdDetail }}</span>
          </div>
        </blockquote>

        <div class="box">
          <div class="info">
            <span class="detail">🗓️ &nbsp; 기간 : </span>
            <span
              >{{ recruitDetail.startDate }} ~ {{ recruitDetail.endDate }}</span
            >
          </div>
          <div class="info">
            <span class="detail">⏰ &nbsp; 진행 일자 : </span>
            <span>{{ recruitDetail.stdDay }}</span>
          </div>
          <div v-if="recruitDetail.recruitStatus === '모집 중'" class="info">
            <span class="detail">🤝 &nbsp; 모집인원 : </span>
            <span>{{ recruitDetail.count }}/{{ recruitDetail.stdLimit }}</span>
          </div>
        </div>
        <div class="applyBtn">
          <div v-if="applyType === 'LEADER'">
            <button @click="studyStart(recruitNo)">스터디 시작</button>
            <button @click="moveToUpdate">수정</button>
            <button @click="deleteRecruitmentDetail([recruitNo])">삭제</button>
          </div>
          <div
            v-else-if="
              applyType === 'NORMAL' &&
                recruitDetail.recruitStatus === '모집 중'
            "
          >
            <button @click="studyApplyCancel(recruitNo)">
              스터디 신청 취소
            </button>
          </div>
          <div
            v-else-if="
              applyType === null && recruitDetail.recruitStatus === '모집 중'
            "
          >
            <button @click="studyApply(recruitNo)">스터디 신청하기</button>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import router from "@/common/lib/vue-router";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "MembersRecruitmentDetailView",

  data() {
    return {
      recruitNo: this.$route.params.recruitNo,
      image: require("../../assets/images/fighting.jpeg")
    };
  },
  computed: {
    ...mapGetters([
      "recruitDetail",
      "currentUser",
      "isApplied",
      "applyType",
      "recruitCount"
    ])
  },
  methods: {
    ...mapActions([
      "bringRecruitmentDetail",
      "updateRecruitmentDetail",
      "deleteRecruitmentDetail",
      "createStudySpace",
      "studyApply",
      "studyApplyCancel"
    ]),
    interviewType() {
      if (this.recruitDetail.stdType === "COM") {
        this.studyType = "기업 면접";
      } else {
        this.studyType = "자율 면접";
      }
    },
    moveToUpdate() {
      router.push({
        name: "membersrecruitmentedit",
        params: { recruitNo: this.recruitNo }
      });
    },
    async studyStart() {
      await this.createStudySpace(this.recruitNo);
      await router.push({ name: "study" });
    }
  },
  async created() {
    this.bringRecruitmentDetail(this.recruitNo);
  },
  mounted() {
    window.scrollTo(0, 0);
  }
};
</script>

<style scoped>
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
.title {
  font-size: 15;
}
.info {
  margin-bottom: 12px;
}
.comName {
  color: #f56a6a;
}
.applyBtn {
  margin-top: 20px;
}
blockquote {
  quotes: none;
  border-left: solid 3px rgba(210, 215, 217, 0.75);
  font-style: italic;
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
