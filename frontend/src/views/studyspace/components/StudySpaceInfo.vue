<template>
  <div class="container">
    <header>
      <h3 style="text-align: center; ">
        [ {{ studySpaceDetail.stdName }} ] 스터디 스페이스
      </h3>
    </header>
    <section id="banner">
      <!-- <span class="image object"> -->
      <div class="row">
        <div class="image object col-4">
          <img :src="studySpaceDetail.stdImg" alt="" class="stdImg" />
        </div>
        <!-- </span> -->
        <div class="content col">
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
              ><i
                class="input-icon uil uil-edit"
                v-if="studySpaceDetail.joinType == 'LEADER'"
                data-bs-toggle="modal"
                data-bs-target="#studyUpdateModal"
                data-bs-whatever="@mdo"
                style="font-size:20px;"
              ></i>
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
          </div>
          <button @click="moveToSession()" class="study-space-btn">
            스터디룸 입장
          </button>
          <button
            v-if="studySpaceDetail.joinType == 'NORMAL'"
            @click="leaveStudy(stdNo)"
          >
            스터디 탈퇴
          </button>
          <div
            class="modal fade"
            id="studyUpdateModal"
            tabindex="-1"
            aria-labelledby="studyUpdateModalLabel"
            aria-hidden="true"
            data-bs-backdrop="static"
            data-bs-keyboard="false"
          >
            <div class="modal-dialog modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="studyUpdateModalLabel">
                    스터디 정보 수정
                  </h5>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="mb-3">
                      <div class="mb-3">
                        <label for="recipient-name" class="col-form-label"
                          >스터디명</label
                        >
                        <input
                          type="text"
                          class="form-control"
                          id="recipient-name"
                          v-model="this.studyUpdateInfo.studyName"
                        />
                      </div>
                      <label for="recipient-name" class="col-form-label"
                        >스터디 설명</label
                      >
                      <textarea
                        class="form-control autoTextarea"
                        id="floatingTextarea"
                        v-model="this.studyUpdateInfo.studyDetail"
                        @keyup="autoResizeTextarea"
                        @keydown="autoResizeTextarea"
                      >
                      </textarea>
                      <div class="mb-3">
                        <label for="recipient-name" class="col-form-label"
                          >기간</label
                        >
                        <div class="row">
                          <input
                            type="date"
                            class="form-control"
                            id="recipient-name"
                            v-model="this.studyUpdateInfo.studyStartDate"
                            style="width:40%; margin-left:15px"
                          />
                          &nbsp;~&nbsp;
                          <input
                            type="date"
                            class="form-control"
                            id="recipient-name"
                            v-model="this.studyUpdateInfo.studyEndDate"
                            style="width:40%"
                          />
                        </div>
                      </div>
                      <div class="mb-3">
                        <label for="recipient-name" class="col-form-label"
                          >진행 일자</label
                        >
                        <input
                          type="text"
                          class="form-control"
                          id="recipient-name"
                          v-model="this.studyUpdateInfo.studyDay"
                        />
                      </div>
                      <div class="mb-3">
                        <label for="recipient-name" class="col-form-label"
                          >대표 이미지</label
                        >
                        <input
                          type="file"
                          class="form-control"
                          id="inputImage"
                          ref="inputImage"
                          @change="imageSelect()"
                          multiple
                        />
                      </div>
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="small"
                    data-bs-dismiss="modal"
                    @click="initStudy()"
                  >
                    수정
                  </button>
                  <button
                    type="button"
                    class="small"
                    data-bs-dismiss="modal"
                    @click="bringStudyInfoBack()"
                  >
                    취소
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <div class="study-detail-information-wrapper">
      <div class="row">
        <h4 class="stdNotice col-md-3">
          스터디 공지사항 &nbsp;<i
            class="input-icon uil uil-edit"
            v-if="studySpaceDetail.joinType == 'LEADER'"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@mdo"
            style="font-size:20px;"
          ></i>
        </h4>
        <div
          class="modal fade"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
          data-bs-backdrop="static"
          data-bs-keyboard="false"
        >
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                  공지사항 수정
                </h5>
              </div>
              <div class="modal-body">
                <form>
                  <div class="mb-3">
                    <label for="floatingTextarea" class="col-form-label"
                      >공지사항을 입력해주세요</label
                    >
                    <textarea
                      class="form-control autoTextarea"
                      id="floatingTextarea"
                      v-model="noticeContent"
                      @keyup="autoResizeTextarea"
                      @keydown="autoResizeTextarea"
                    >
                    </textarea>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="small"
                  data-bs-dismiss="modal"
                  @click="initNotice()"
                >
                  수정
                </button>
                <button
                  type="button"
                  class="small"
                  data-bs-dismiss="modal"
                  @click="bringNoticeBack"
                >
                  취소
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="box">
        <div class="info noticebox">
          <span v-if="studySpaceDetail.stdNotice == null"
            >공지사항을 입력해주세요.</span
          >
          <span v-else>{{ studySpaceDetail.stdNotice }} </span>
        </div>
      </div>
      <div style="height:50px"></div>
      <h4 class="stdMembers">스터디원</h4>
      <div class="table-wrapper">
        <table>
          <tbody>
            <study-members
              v-for="(member, index) in studySpaceDetail.studyJoins"
              :key="member.joinNo"
              :member="member"
              :studentindex="index"
            ></study-members>
          </tbody>
        </table>
      </div>
    </div>
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
      studyUpdateInfo: {
        studyName: null,
        studyDetail: null,
        studyStartDate: null,
        studyEndDate: null,
        studyDay: null,
        studyImage: null
      },
      noticeContent: null
    };
  },
  computed: {
    ...mapGetters(["studySpaceDetail"]),
    findImage() {
      document.getElementById("inputImage").click();
    }
  },
  methods: {
    ...mapActions([
      "bringStudySpaceDetail",
      "leaveStudy",
      "updateStudyNoticeAction",
      "updateStudyAction"
    ]),
    ...mapMutations(["SET_STD_NO"]),
    moveToSession() {
      router.push({
        name: "session",
        params: { sessionNo: this.stdNo }
      });
    },
    handleNoticeModal() {
      this.is_show = !this.is_show;
    },
    autoResizeTextarea(e) {
      let textarea = document.querySelector(".autoTextarea");
      textarea = textarea.replaceAll(/(\n|\r\n)/g, "<br>");
      console.log(e + " : " + textarea);
      if (textarea) {
        textarea.style.height = "auto";
        let height = textarea.scrollHeight;
        textarea.style.height = `${height + 8}px`;
      }
    },
    bringStudyInfoBack() {
      this.studyUpdateInfo.studyName = this.studySpaceDetail.stdName;
      this.studyUpdateInfo.studyDetail = this.studySpaceDetail.stdDetail;
      this.studyUpdateInfo.studyStartDate = this.studySpaceDetail.startDate;
      this.studyUpdateInfo.studyEndDate = this.studySpaceDetail.endDate;
      this.studyUpdateInfo.studyDay = this.studySpaceDetail.stdDay;
      this.studyUpdateInfo.studyImage = this.studySpaceDetail.stdImg;
    },
    bringNoticeBack() {
      this.noticeContent = this.studySpaceDetail.stdNotice;
    },
    imageSelect() {
      this.studyUpdateInfo.studyImage = this.$refs.inputImage.files[0];
    },
    upload() {
      const formData = new FormData();
      formData.append("multipartFile", this.studyUpdateInfo.studyImage);
      formData.append("studyInfoPutReq.comName", this.studySpaceDetail.comName);
      formData.append(
        "studyInfoPutReq.endDate",
        this.studyUpdateInfo.studyEndDate
      );
      formData.append(
        "studyInfoPutReq.startDate",
        this.studyUpdateInfo.studyStartDate
      );
      formData.append("studyInfoPutReq.stdDay", this.studyUpdateInfo.studyDay);
      formData.append(
        "studyInfoPutReq.stdDetail",
        this.studyUpdateInfo.studyDetail
      );
      formData.append(
        "studyInfoPutReq.stdLimit",
        this.studySpaceDetail.stdLimit
      );
      formData.append(
        "studyInfoPutReq.stdName",
        this.studyUpdateInfo.studyName
      );
      formData.append("studyInfoPutReq.stdType", this.studySpaceDetail.stdType);
      console.log(formData);
      return formData;
    },
    async initStudy() {
      const formData = await this.upload();
      console.log(formData);
      this.updateStudyAction([this.studySpaceDetail.stdNo, formData]);
    },
    initNotice() {
      this.studySpaceDetail.stdNotice = this.noticeContent;
      this.updateStudyNoticeAction();
    }
  },
  created() {
    this.SET_STD_NO(this.stdNo);
    this.bringStudySpaceDetail(this.stdNo);
    this.bringStudyInfoBack();
    this.bringNoticeBack();
    // await this.deleteStudySpace([this.stdNo])
  }
};
</script>

<style scoped>
.study-detail-information-wrapper {
  margin-left: 3.5em;
}
h3,
h5 {
  font-family: "티머니 둥근바람 볼드";
}
h3 {
  margin-bottom: 25px;
}
.stdNotice,
.stdMembers {
  font-family: "티머니 둥근바람";
  font-weight: bold;
  margin-bottom: 20px;
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
  margin: 0 0 1.7em 0;
  padding: 0.5em 0 0.5em 2em;
}
#banner {
  padding: 3em 0 2em 0;
  display: -moz-flex;
  display: -webkit-flex;
  display: -ms-flex;
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
  /* width: 30%; */
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
  /* color: #653fd3 !important; */
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
  font-size: 0.63em;
  width: 7em;
  text-align: center;
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

.table-wrapper {
  -webkit-overflow-scrolling: touch;
  overflow-x: auto;
}
table {
  margin: 0 0 2em 0;
  width: 100%;
}
table tbody tr {
  border: solid 1px rgba(210, 215, 217, 0.75);
  border-left: 0;
  border-right: 0;
  height: 60px;
}
table tbody tr:nth-child(2n + 1) {
  background-color: rgba(230, 235, 237, 0.25);
}
table td {
  padding: 0.75em 0.75em;
}
table th {
  color: #3d4449;
  font-size: 0.9em;
  font-weight: 600;
  padding: 0 0.75em 0.75em 0.75em;
  text-align: left;
}
table thead {
  border-bottom: solid 2px rgba(210, 215, 217, 0.75);
}
table tfoot {
  border-top: solid 2px rgba(210, 215, 217, 0.75);
}
table.alt {
  border-collapse: separate;
}
table.alt tbody tr td {
  border: solid 1px rgba(210, 215, 217, 0.75);
  border-left-width: 0;
  border-top-width: 0;
}
table.alt tbody tr td:first-child {
  border-left-width: 1px;
}
table.alt tbody tr:first-child td {
  border-top-width: 1px;
}
table.alt thead {
  border-bottom: 0;
}
table.alt tfoot {
  border-top: 0;
}
i:hover {
  color: #653fd3;
}
</style>
