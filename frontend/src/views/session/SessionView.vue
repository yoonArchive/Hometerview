<template>
  <div class="full-con">
    <div class="main-con">
      <div hidden="ture" ref="webcam"></div>
      <!-- teachable machine -->
      <!-- <button type="button" @click="init()">Start</button>
      <button type="button" @click="tmStop()">Stop</button> -->

      <div class="con d-flex justify-content-between row">
        <div class="side-left col-md-8">
          <!-- 면접자 모드!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
          <div
            class="center interview-mode"
            style="margin-top:3vh;"
            v-if="screenMode === 'interview'"
          >
            <!-- 중앙 -->
            <!-- 메인 화면, 사이드 -->

            <!-- session -->
            <div class="video-group">
              <!-- 자기 화면(큰) => mainStreamManager-->
              <div id="main-video" class="col">
                <user-video
                  class="large-video"
                  :stream-manager="mainStreamManager"
                  :interviewMode="true"
                  :mainStream="true"
                  style="height:65vh;"
                />
              </div>

              <!-- 비디오 그룹 -->
              <div
                class="video-container col d-flex justify-content-center"
                @load="showDivs(slideIndex)"
              >
                <img
                  :src="require(`@/assets/images/session/left.png`)"
                  class="slider-button-left"
                  @click="plusDivs(-1)"
                  style="height:4vh; margin-top:9vh;"
                />
                <!-- 자기화면 (작은) -->
                <user-video
                  class="small-video slider my-video row-md-3"
                  :stream-manager="publisher"
                  :mainStream="false"
                  :interviewMode="true"
                  @click="updateMainVideoStreamManager(publisher)"
                  style="height:15vh;"
                />
                <!-- native : 상위 컴포넌트(즉 여기 있는 이벤트)를 하위 컴포넌트에서 작동시키고 싶을 때 사용한다. -->
                <!-- vue3에서 native가 사라지고 그냥 click을 누르면 된다. -->
                <user-video
                  class="user-video slider row-md-3"
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  :interviewMode="true"
                  :mainStream="false"
                  @click="updateMainVideoStreamManager(sub)"
                  style="height:15vh;"
                />
                <img
                  :src="require(`@/assets/images/session/right.png`)"
                  class="slider-button-right"
                  @click="plusDivs(1)"
                  style="height:4vh; margin-top:9vh;"
                />
              </div>
            </div>
            <div
              class="bottom d-flex justify-content-evenly"
              style="margin-top: 9vh;"
            >
              <!-- 하단 -->
              <!-- 비디오, 오디오, leave, 더보기 -->
              <!-- 장치 옵션 -->

              <!-- 마이크 ONOFF-->
              <div>
                <!-- <div class="video-button"></div> -->
                <div
                  v-if="audioOnOff"
                  class="mic-button-on tooltip-image
"
                >
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  />
                  <span class="tooltip-text">
                    마이크 끄기
                  </span>
                </div>
                <div v-else class="mic-button-off tooltip-image">
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  />
                  <span class="tooltip-text">
                    마이크 켜기
                  </span>
                </div>
              </div>

              <!-- 비디오 ONOFF -->
              <div>
                <div v-if="videoOnOff" class="video-button-on tooltip-image">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  /><span class="tooltip-text">
                    카메라 끄기
                  </span>
                </div>
                <div v-else class="video-button-off tooltip-image">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  /><span class="tooltip-text">
                    카메라 켜기
                  </span>
                </div>
              </div>
              <!-- teachable -->
              <div>
                <div
                  v-if="teachOnOff"
                  class="teachable-button-on tooltip-image"
                >
                  <img
                    :src="require(`@/assets/images/session/teach.png`)"
                    @click="teachONOFF()"
                    style="height:3.8vh; margin-left: 1vh; margin-top: 1.1vh;"
                  /><span class="tooltip-text">
                    자세교정 끄기
                  </span>
                </div>
                <div v-else class="teachable-button-off tooltip-image">
                  <img
                    :src="require(`@/assets/images/session/teach.png`)"
                    @click="teachONOFF()"
                    style="height:3.8vh; margin-left:1vh; margin-top: 1.1vh;"
                  /><span class="tooltip-text">
                    자세교정 켜기
                  </span>
                </div>
              </div>
              <!-- 나가기 -->
              <div>
                <div class="leave-button">
                  <img
                    @click="leaveSession"
                    :src="require(`@/assets/images/session/leave.png`)"
                    style="height:3.5vh; margin-left: 1.3vh; margin-top: 1.2vh;"
                  />
                </div>
              </div>
              <!-- 화면 공유 -->
              <!-- <div>
                <button @click="ShareScreen()">화면 공유</button>
              </div> -->
            </div>
          </div>

          <!-- 노말 모드!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
          <div
            class="center not-interview-mode"
            style="margin-top:3vh;"
            v-else-if="screenMode === 'normal'"
          >
            <!-- session -->
            <div class="video-group">
              <!-- 비디오 그룹 -->
              <div
                class="video-container row d-flex justify-content-center"
                style="margin-top: 23vh; height:56vh;"
              >
                <!-- 자기화면 (작은) -->
                <user-video
                  class="small-video my-video col-3"
                  :stream-manager="publisher"
                  :mainStream="false"
                  :interviewMode="false"
                  :checkId="myUserId"
                  style="height:26vh; width:37vh; margin:0.5vh;"
                />
                <user-video
                  class="user-video col-3"
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  :mainStream="false"
                  :interviewMode="false"
                  style="height:26vh; width:37vh; margin:0.5vh;"
                />
              </div>
            </div>
            <!-- 하단 -->
            <!-- 비디오, 오디오, leave, 더보기 -->
            <!-- 장치 옵션 -->
            <div class="bottom-con">
              <div
                class="bottom d-flex justify-content-evenly"
                style="margin-top: 9vh;"
              >
                <!-- 마이크 ONOFF-->
                <div>
                  <!-- <div class="video-button"></div> -->
                  <div v-if="audioOnOff" class="mic-button-on tooltip-image">
                    <img
                      @click="audioONOFF()"
                      :src="require(`@/assets/images/session/micOn.png`)"
                      style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                    /><span class="tooltip-text">
                      마이크 끄기
                    </span>
                  </div>
                  <div v-else class="mic-button-off tooltip-image">
                    <img
                      @click="audioONOFF()"
                      :src="require(`@/assets/images/session/micOn.png`)"
                      style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                    /><span class="tooltip-text">
                      마이크 켜기
                    </span>
                  </div>
                </div>
                <!-- 비디오 ONOFF -->
                <div>
                  <div v-if="videoOnOff" class="video-button-on tooltip-image">
                    <img
                      @click="videoONOFF()"
                      :src="require(`@/assets/images/session/video.png`)"
                      style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                    /><span class="tooltip-text">
                      카메라 끄기
                    </span>
                  </div>
                  <div v-else class="video-button-off tooltip-image">
                    <img
                      @click="videoONOFF()"
                      :src="require(`@/assets/images/session/video.png`)"
                      style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                    /><span class="tooltip-text">
                      카메라 켜기
                    </span>
                  </div>
                </div>
                <!-- teachable button -->
                <div>
                  <div
                    v-if="teachOnOff"
                    class="teachable-button-on tooltip-image"
                  >
                    <img
                      :src="require(`@/assets/images/session/teach.png`)"
                      @click="teachONOFF()"
                      style="height:3.8vh; margin-left: 1vh; margin-top: 1.1vh;"
                    /><span class="tooltip-text">
                      자세교정 끄기
                    </span>
                  </div>
                  <div v-else class="teachable-button-off tooltip-image">
                    <img
                      :src="require(`@/assets/images/session/teach.png`)"
                      @click="teachONOFF()"
                      style="height:3.8vh; margin-left:1vh; margin-top: 1.1vh;"
                    /><span class="tooltip-text">
                      자세교정 켜기
                    </span>
                  </div>
                </div>
                <!-- 나가기 -->
                <div>
                  <div class="leave-button">
                    <img
                      @click="leaveSession"
                      :src="require(`@/assets/images/session/leave.png`)"
                      style="height:3.5vh; margin-left: 1.3vh; margin-top: 1.2vh;"
                    />
                  </div>
                </div>
                <!-- 화면 공유 -->
                <!-- <div>
                  <button @click="ShareScreen()">화면 공유</button>
                </div> -->
              </div>
            </div>
          </div>

          <!-- tts 모드 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -->
          <div
            class="center interview-mode"
            style="margin-top:3vh;"
            v-else-if="screenMode === 'tts'"
          >
            <!-- 중앙 -->
            <!-- 메인 화면, 사이드 -->

            <!-- session -->
            <!-- 면접관 사진 -->
            <div class="video-group">
              <div id="main-video" class="col">
                <img
                  :src="require('@/assets/images/session/interviewer.png')"
                  style="height:65vh;"
                />
              </div>

              <!-- 비디오 그룹 -->
              <div
                class="video-container col d-flex justify-content-center"
                @load="showDivs(slideIndex)"
              >
                <img
                  :src="require(`@/assets/images/session/left.png`)"
                  class="slider-button-left"
                  @click="plusDivs(-1)"
                  style="height:4vh; margin-top:9vh;"
                />
                <!-- 자기화면 (작은) -->
                <user-video
                  class="small-video slider my-video row-md-3"
                  :stream-manager="publisher"
                  :mainStream="false"
                  :interviewMode="true"
                  :checkId="myUserId"
                  @click="updateMainVideoStreamManager(publisher)"
                  style="height:15vh;"
                />
                <!-- native : 상위 컴포넌트(즉 여기 있는 이벤트)를 하위 컴포넌트에서 작동시키고 싶을 때 사용한다. -->
                <!-- vue3에서 native가 사라지고 그냥 click을 누르면 된다. -->
                <user-video
                  class="user-video slider row-md-3"
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  :interviewMode="true"
                  :mainStream="false"
                  @click="updateMainVideoStreamManager(sub)"
                  style="height:15vh;"
                />
                <img
                  :src="require(`@/assets/images/session/right.png`)"
                  class="slider-button-right"
                  @click="plusDivs(1)"
                  style="height:4vh; margin-top:9vh;"
                />
              </div>
            </div>
            <div
              class="bottom d-flex justify-content-evenly"
              style="margin-top: 9vh;"
            >
              <!-- 하단 -->
              <!-- 비디오, 오디오, leave, 더보기 -->
              <!-- 장치 옵션 -->

              <!-- 마이크 ONOFF-->
              <div>
                <!-- <div class="video-button"></div> -->
                <div v-if="audioOnOff" class="mic-button-on tooltip-image">
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  /><span class="tooltip-text">
                    마이크 끄기
                  </span>
                </div>
                <div v-else class="mic-button-off tooltip-image">
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  /><span class="tooltip-text">
                    마이크 켜기
                  </span>
                </div>
              </div>

              <!-- 비디오 ONOFF -->
              <div>
                <div v-if="videoOnOff" class="video-button-on tooltip-image">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  /><span class="tooltip-text">
                    카메라 끄기
                  </span>
                </div>
                <div v-else class="video-button-off tooltip-image">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  /><span class="tooltip-text">
                    카메라 켜기
                  </span>
                </div>
              </div>
              <div>
                <div
                  v-if="teachOnOff"
                  class="teachable-button-on tooltip-image"
                >
                  <img
                    :src="require(`@/assets/images/session/teach.png`)"
                    @click="teachONOFF()"
                    style="height:3.8vh; margin-left: 1vh; margin-top: 1.1vh;"
                  />
                  <span class="tooltip-text">
                    자세교정 끄기
                  </span>
                </div>
                <div v-else class="teachable-button-off tooltip-image">
                  <img
                    :src="require(`@/assets/images/session/teach.png`)"
                    @click="teachONOFF()"
                    style="height:3.8vh; margin-left:1vh; margin-top: 1.1vh;"
                  /><span class="tooltip-text">
                    자세교정 켜기
                  </span>
                </div>
              </div>
              <!-- 나가기 -->
              <div>
                <div class="leave-button">
                  <img
                    @click="leaveSession"
                    :src="require(`@/assets/images/session/leave.png`)"
                    style="height:3.5vh; margin-left: 1.3vh; margin-top: 1.2vh;"
                  />
                </div>
              </div>
              <!-- 화면 공유 -->
              <!-- <div>
                <button @click="ShareScreen()">화면 공유</button>
              </div> -->
            </div>
          </div>
        </div>
        <div class=" side-right col-md-4">
          <!-- 사이드 -->
          <!-- 메시지, 자소서, 참가자 지정 -->

          <div class="side-panel">
            <div class="select-side-bottons">
              <div class="d-flex justify-content-between">
                <div class="row" style="margin-left:1vh;">
                  <div class="col" style="margin:0; padding:0;">
                    <img
                      :src="require(`@/assets/images/session/chatOn.png`)"
                      @click="changeContent('chatting')"
                      style="height:6vh; margin-top:-5%;"
                      v-if="chatting"
                    />
                    <img
                      :src="require(`@/assets/images/session/chatOff.png`)"
                      @click="changeContent('chatting')"
                      style="height:6vh; margin-top:-5%;"
                      v-else
                    />
                  </div>
                  <div class="col" style="margin:0; padding:0;">
                    <img
                      :src="require(`@/assets/images/session/resumeOn.png`)"
                      @click="changeContent('participant')"
                      v-if="participant"
                      style="margin-top:5%; height:4.8vh"
                    />
                    <img
                      :src="require(`@/assets/images/session/resumeOff.png`)"
                      @click="changeContent('participant')"
                      v-else
                      style="margin-top:5%; height:4.8vh"
                    />
                  </div>
                  <div
                    class="col"
                    style="margin:0; padding:0;"
                    v-if="myJoinType === 'LEADER'"
                  >
                    <img
                      :src="require(`@/assets/images/session/memberOn.png`)"
                      @click="changeContent('selectinterviewee')"
                      style="height:5.7vh; margin-top:5%"
                      v-if="selectinterviewee"
                    />
                    <img
                      :src="require(`@/assets/images/session/memberOff.png`)"
                      @click="changeContent('selectinterviewee')"
                      style="height:5.7vh; margin-top:5%"
                      v-else
                    />
                  </div>
                  <div class="col" style="margin:0; padding:0;">
                    <img
                      :src="
                        require(`@/assets/images/session/questionAnswerOn.png`)
                      "
                      @click="changeContent('commonquestion')"
                      style="margin-top: 0.8vh; height:4.5vh;"
                      v-if="commonquestion"
                    />
                    <img
                      :src="
                        require(`@/assets/images/session/questionAnswerOff.png`)
                      "
                      @click="changeContent('commonquestion')"
                      style="margin-top: 0.8vh; margin-left:0.3vh; height:4.5vh;"
                      v-else
                    />
                  </div>
                </div>
                <div>
                  <div
                    class="row"
                    style="margin-right:3.5vh; margin-top:0.7vh;"
                  >
                    <div
                      class="col"
                      style="margin:0; padding:0; margin-right:0.5vh;"
                      v-if="myJoinType === 'LEADER'"
                    >
                      <img
                        :src="require(`@/assets/images/session/videoOn.png`)"
                        style="height:3vh;"
                        v-if="recordOnOff"
                        @click="recordONOFF()"
                      />
                      <img
                        :src="require(`@/assets/images/session/videoOff.png`)"
                        style="height:3vh;"
                        @click="recordONOFF()"
                        v-else
                      />
                    </div>
                  </div>
                </div>
              </div>

              <div></div>
              <!-- 메시지 -->
              <div v-if="chatting">
                <message-list
                  :msgs="msgs"
                  :myId="publisher.stream.connection.connectionId"
                ></message-list>
                <message-form
                  @sendMsg="sendMsg"
                  :user-name="myUserName"
                  :user-img="myUserImg"
                ></message-form>
              </div>
            </div>
            <div v-if="participant">
              <study-member-list></study-member-list>
            </div>
            <div v-if="selectinterviewee">
              <select-interviewee
                :interviewUserFixed="interviewUserFixed"
                @streamUpdate="streamUpdate"
              ></select-interviewee>
              <!-- usertype==='LEADERS' && 리더만 보이게 하기 =   -->
              <!-- v-if="myJoinType === 'LEADER'" -->
            </div>
            <div v-if="commonquestion">
              <common-qusetion-list
                @sendTTS="sendTTS"
                :myJoinType="myJoinType"
              ></common-qusetion-list>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.full-con {
  background-color: #272930;
  min-height: 100%;
}
.main-con {
  margin-right: 2%;
  margin-left: 2%;
  padding-bottom: 3%;
}
.side-right {
  display: inline-block;
  height: 100%;
}
.side-left {
  display: inline-block;
  height: 100%;
}
.user-video {
  display: inline-block;
  margin: 1px;
}
.my-video {
  display: inline-block;
  margin: 1px;
}
.main-video {
  margin-top: 20px;
}
.select-side-bottons {
  margin-top: 20px;
}
.side-panel {
  background-color: whitesmoke;
  border-radius: 10px 10px 10px 10px;
}
.video-button-on {
  background-color: #baaaea;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.video-button-off {
  background-color: #d9d9d9;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.mic-button-on {
  background-color: #baaaea;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.mic-button-off {
  background-color: #d9d9d9;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.leave-button {
  background-color: #ec5c5c;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.teachable-button-on {
  background-color: #baaaea;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.teachable-button-off {
  background-color: #d9d9d9;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
}
.tooltip-text {
  display: none;
  position: absolute;
  bottom: 120%;
  left: 50%;
  margin-left: -60px;
  width: 120px;
  border: 1px solid;
  border-radius: 5px;
  padding: 5px;
  font-size: 16px;
  color: white;
  background: black;
  z-index: 10;
}
.tooltip-image {
  position: relative;
  display: block;
}
.tooltip-image:hover .tooltip-text {
  display: block;
}
</style>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import router from "@/common/lib/vue-router";
import { mapActions, mapGetters } from "vuex";

// components
import UserVideo from "./components/UserVideo";
import MessageForm from "./components/MessageForm.vue";
import MessageList from "./components/MessageList.vue";
import StudyMemberList from "./components/StudyMemberList.vue";
import SelectInterviewee from "./components/SelectInterviewee.vue";
import CommonQusetionList from "./components/CommonQusetionList.vue";

// teacherable
import "@tensorflow/tfjs";
import * as tmPose from "@teachablemachine/pose";

//default
axios.defaults.headers.post["Content-Type"] = "application/json"; // 글로벌 axios 기본(defaults) 설정 => application/json
const s = "i7b105.p.ssafy.io";
const OPENVIDU_SERVER_URL = "https://" + s + ":8443";
const OPENVIDU_SERVER_SECRET = "admin";

export default {
  name: "SessionView",

  components: {
    UserVideo,
    MessageForm,
    MessageList,
    StudyMemberList,
    SelectInterviewee,
    CommonQusetionList
  },

  data() {
    return {
      // 방,유저 정보
      sessionNo: this.$route.params.sessionNo,
      userType: "",
      mySessionId: ``,
      myUserName: "",
      myUserImg: "",
      myUserId: "",
      myJoinType: "",
      clientType: "",
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined, //local
      subscribers: [], // remotes

      //teacherable
      model: null,
      webcam: null,
      labelContainer: null,
      maxPredictions: null,
      url: "https://teachablemachine.withgoogle.com/models/s2xpz62oC/",
      predictions: [],
      pose: null,
      posenetOutput: null,

      // record
      recording: {},
      recordingSessionId: "",
      recordingToSend: {
        userId: "",
        videoUrl: ""
      },

      // massege
      msgs: [],
      fromId: "",

      // update
      updateMain: "",
      fromMainId: "",
      forUpdataList: [],

      //show
      chatting: true,
      participant: false,
      selectinterviewee: false,
      commonquestion: false,

      // function
      videoOnOff: true,
      audioOnOff: true,
      mirrorOnOff: true,
      recordOnOff: false,
      teachOnOff: false,

      //scree share
      screenOV: undefined,
      screenSession: undefined,
      screenMainStreamManager: undefined,
      screenPublisher: undefined,
      screenSubscribers: [],
      screenOvToken: null,
      isSharingMode: false,

      // slider
      slideIndex: 1

      // interview mode
      // interviewMode: false
    };
  },
  computed: {
    ...mapGetters([
      "currentUser",
      "studySpaceDetail",
      "interviewUserFixed",
      "interviewUser",
      "ttsrequestcontext",
      "interviewMode",
      "isTTSMode",
      "screenMode"
    ])
  },
  methods: {
    ...mapActions([
      "bringStudySpaceDetail",
      "changeToCoverLetter",
      "needToFixPosture",
      "stopToFixPosture",
      "saveRecordedFile",
      "changettsrequest",
      "changeInterviewMode",
      "changeInterviewUser",
      "changeScreenMode"
    ]),
    plusDivs(n) {
      this.showDivs((this.slideIndex += n));
    },

    async showDivs(n) {
      let i;
      let x = await document.getElementsByClassName("slider");
      console.log(x);
      if (n > x.length) {
        this.slideIndex = 1;
      }
      if (n < 1) {
        this.slideIndex = x.length;
      }
      for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
      }
      x[this.slideIndex - 1].style.display = await "block";
      x[this.slideIndex].style.display = await "block";
      x[this.slideIndex + 1].style.display = await "block";
    },

    async recordingStartButton() {
      const date = await new Date();
      const [month, day, year, hour, minutes, seconds] = await [
        date.getMonth().toString(),
        date.getDate().toString(),
        date.getFullYear().toString(),
        date.getHours().toString(),
        date.getMinutes().toString(),
        date.getSeconds().toString()
      ];
      this.recordingSessionId = await `${this.mySessionId}-${year}-${month}-${day}-${hour}-${minutes}-${seconds}-${this.currentUser.userId}`;
      return this.recordingStart(this.mySessionId);
    },
    recordingStopButton() {
      return this.recordingStop(this.recording.id);
    },
    recordingStart(sessionId) {
      return new Promise(async (resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/recordings/start`,
            JSON.stringify({
              name: this.recordingSessionId,
              session: sessionId
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(response => {
            this.recording = response.data;
            console.log(this.recording);
          })
          .then(data => resolve(data.token))
          .catch(error => reject(error.response));
      });
    },

    recordingStop(sessionId) {
      return new Promise(async (resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/recordings/stop/${sessionId}`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(async response => {
            this.recording = await response.data;
            const memberlist = this.studySpaceDetail.studyJoins;
            this.recordingToSend.videoUrl = await this.recording.url;
            for (const member of memberlist) {
              this.recordingToSend.userId = await member.user.userId;
              // console.log(this.recordingToSend.userId);
              await this.saveRecordedFile([
                this.recordingToSend,
                this.sessionNo
              ]);
            }

            // this.recordingToSend.userId = await this.myUserId;
          })
          .then(data => resolve(data.token))
          .catch(error => reject(error.response));
      });
    },
    async init() {
      const modelURL = `${this.url}model.json`;
      const metadataURL = `${this.url}metadata.json`;
      const webcamContainer = this.$refs.webcam;
      const flip = false; // whether to flip the webcam
      // load the model and metadata
      // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
      // or files from your local hard drive
      // Note: the pose library adds "tmImage" object to your window (window.tmImage)
      // this.model = await tmPose.load(modelURL, metadataURL);
      this.model = Object.freeze(await tmPose.load(modelURL, metadataURL));
      // Convenience function to setup a webcam
      this.webcam = new tmPose.Webcam(
        webcamContainer.width,
        webcamContainer.height,
        flip
      ); // width, height, flip
      await this.webcam.setup(); // request access to the webcam
      await this.webcam.play();
      webcamContainer.appendChild(this.webcam.canvas);

      window.requestAnimationFrame(this.loop);
    },
    async loop() {
      if (this.webcam != null) {
        console.log("hi");
        this.webcam.update();
        await this.predict();
        await this.inference();
        window.requestAnimationFrame(this.loop);
      }
    },
    async predict() {
      const { posenetOutput } = await this.model.estimatePose(
        this.webcam.canvas
      );
      this.predictions = await this.model.predict(posenetOutput);
    },
    async inference() {
      for (const prediction of this.predictions) {
        const className = await prediction.className;
        const pred = await prediction.probability.toFixed(2);

        if (className === "center") {
          const postureColor = "";
          await this.needToFixPosture(postureColor);
        } else if (className === "left" && pred > 0.9) {
          const postureColor = "#b93131";
          console.log("left");
          await this.needToFixPosture(postureColor);
        } else if (className === "right" && pred > 0.9) {
          const postureColor = "#b93131";
          console.log("right");
          await this.needToFixPosture(postureColor);
        }
      }
    },
    async tmStop() {
      this.webcam = await null;
      setTimeout(() => {
        this.stopToFixPosture();
      }, 500);
    },
    async findIndex(userId) {
      const members = this.studySpaceDetail.studyJoins;
      let studentindex;
      members.forEach(function(member, index) {
        const checkId = member.user.userId;
        if (userId === checkId) {
          console.log("check!!!!!!!!!!");
          console.log(index);
          studentindex = index;
          return;
        }
      });
      if (studentindex) {
        return studentindex;
      } else {
        return 0;
      }
    },
    async streamUpdate(interviewUserFixed) {
      // 자기 자신, 상대방 아이디들필요 ==> update
      await this.sendUpdate(interviewUserFixed);
    },

    async recordONOFF() {
      if (this.recordOnOff) {
        this.recordingStopButton();
        this.recordOnOff = !this.recordOnOff;
        console.log(this.recordOnOff);
      } else {
        await alert("녹화를 시작하겠습니다.");
        this.recordingStartButton();
        this.recordOnOff = !this.recordOnOff;
        console.log(this.recordOnOff);
      }
    },
    videoONOFF() {
      this.publisher.publishVideo(!this.videoOnOff);
      this.videoOnOff = !this.videoOnOff;
    },
    audioONOFF() {
      this.publisher.publishAudio(!this.audioOnOff);
      this.audioOnOff = !this.audioOnOff;
    },
    async teachONOFF() {
      if (this.teachOnOff) {
        //stop
        this.tmStop();
        this.teachOnOff = !this.teachOnOff;
        console.log(this.teachOnOff);
      } else {
        //start
        await alert("자세교정 모드를 실행하겠습니다.");
        this.init();
        this.teachOnOff = !this.teachOnOff;
        console.log(this.teachOnOff);
      }
    },
    // mirrorONOFF(){
    // 	this.publisher.publishVideo(!this.mirrorOnOff);
    // 	this.mirrorOnOff = !this.mirrorOnOff;
    // },

    // 기본 기능 (입장하기 퇴장하기)
    async joinSession() {
      this.OV = new OpenVidu();
      this.session = this.OV.initSession();
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        this.plusDivs(0);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        // 제일 앞에 있는 stream.streamManager의 인덱스 값을 저장
        const index = this.subscribers.indexOf(stream.streamManager, 0);

        if (index >= 0) {
          this.subscribers.splice(index, 1);
          // spice(index,1) => 인덱스에 있는거 제거
        }
      });

      // On every asynchronous exception...
      // exception 서버 측에서 비동기식 예기치 않은 오류가 발생할 때 Session 개체에 의해 트리거되는 이벤트
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.session.on("signal:my-chat", event => {
        const tmp = this.msgs.slice();
        tmp.push({
          fromId: event.from.connectionId,
          fromMessage: event.data
        });
        this.msgs = tmp;
      });
      this.session.on("signal:ttsshare", ttsdata => {
        this.playtts(ttsdata.data);
      });

      // update
      await this.session.on("signal:main-update", async event => {
        this.updateMain = await event.data;
        const { clientId } = await JSON.parse(
          this.publisher.stream.connection.data
        );

        console.log("확인해보자", this.updateMain, "이거랑", clientId);
        if (!this.updateMain) {
          await this.changeScreenMode("normal");
          // await this.changeInterviewMode(false);
          await this.changeInterviewUser("");
          await this.changeContent("chatting");
          // await this.tmStop()
        } else {
          await this.changeScreenMode("interview");
          // this.changeInterviewMode(true);
          if (clientId === this.updateMain) {
            await this.updateMainVideoStreamManager(this.publisher);
            const studentindex = await this.findIndex(this.updateMain);
            await this.changeContent("participant");
            await this.changeToCoverLetter(["coverletter", studentindex]);
            await this.plusDivs(0);
            // await this.init()

            // this.chatting = false;
            // this.participant = true;
            // this.selectinterviewee = false;
            // this.commonquestion = false;
          } else if (this.subscribers) {
            this.subscribers.forEach(async sub => {
              const { clientId } = JSON.parse(sub.stream.connection.data);
              if (clientId === this.updateMain) {
                await this.updateMainVideoStreamManager(sub);
                const studentindex = await this.findIndex(this.updateMain);
                await this.changeContent("participant");
                await this.changeToCoverLetter(["coverletter", studentindex]);
                await this.plusDivs(0);
                // send함수를 하나 더 만들어서 그 사람한테만 signal이 갈 수 있게끔 만들어주면 될듯?
                // 즉 다른 on 함수를 만드는게 좋을듯 => 이때 apply버튼을 누르면 실행되게끔 select view에서 로직 구현
              }
            });
          }
        }

        console.log("업데이트 확인입니다");
        console.log(event);
        console.log(this.updateMain);
      });

      // change TTS mode

      this.session.on("signal:tts-mode", async event => {
        console.log("tts-mode 확인!!!!!!");
        console.log(event.data);
        const isTTSMode = await event.data;
        if (isTTSMode) {
          await this.changeScreenMode("tts");
          await this.plusDivs(0);
        } else {
          await this.changeScreenMode("normal");
          await this.plusDivs(0);
        }
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend

      this.getToken(this.mySessionId).then(token => {
        // 여기는 client에 대한 정보 보내기
        this.session
          .connect(token, {
            clientData: this.myUserName,
            clientId: this.myUserId

            // // 면접자 지정된 것을 여기에다가 넣어주고 그것이 맞는지 본인과 일치하는지 판단을 해주면 됨
            // // 그때 update부분을 수정하면 가능 할듯
            // clientType: this.clientType
          })
          .then(() => {
            // 여기부터는 장치 정보
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: true // Whether to mirror your local video or not
            });
            // updateMainVideoStreamManager(publisher);
            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---
            this.session.publish(this.publisher);
          })
          .catch(error => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      // beforeunload 페이지를 떠날때 발생한다.새로고침 /뒤로 가기 /브라우저 닫기 /form submit
      window.addEventListener("beforeunload", this.leaveSession);
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---

      // session을 떠날때는 disconnect 함수를 사용하면 됨
      if (this.session) this.session.disconnect();

      this.session = undefined;
      // updateMainVideoStreamManager({});
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
      this.moveToStudy();
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    // 토큰
    getToken(mySessionId) {
      // 세션 만듦 ==> 성공? => craeteToken함수 실행
      return this.createSession(mySessionId).then(sessionId =>
        this.createToken(sessionId)
      );
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(response => response.data)
          .then(data => resolve(data.id))
          .catch(err => {
            if (err.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(err.response);
            }

            console.log(err.response);
          });
      });
    },
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET
              }
            }
          )
          .then(response => response.data)
          .then(data => resolve(data.token))
          .catch(error => reject(error.response));
      });
    },
    // tts

    async playtts(context) {
      const url = "https://i7b105.p.ssafy.io/tts/ttsrequest";
      const ttsdata = await axios.post(
        url,
        { text: context },
        {
          responseType: "arraybuffer"
        }
      );
      console.log("playtts");
      console.log(context);
      AudioContext = window.AudioContext || window.webkitAudioContext;
      const audioContext = new AudioContext();
      const audioBuffer = await audioContext.decodeAudioData(ttsdata.data);
      const audioSource = audioContext.createBufferSource();
      audioSource.buffer = audioBuffer;
      audioSource.connect(audioContext.destination);
      audioSource.start();
      this.changettsrequest("");
    },

    //tts 기능 추가
    async ttspublish(context) {
      console.log(context);
      this.session.signal({
        data: context,
        to: [],
        type: "ttsshare"
      });
    },
    // 화면 공유
    async ShareScreen() {
      if (this.screenPublisher) {
        await alert("화면공유가 중지됩니다");
        this.stopShareScreen();
      } else {
        await alert("화면공유를 시작합니다");
        this.startShareScreen();
      }
    },
    startShareScreen() {
      if (this.isSharingMode) {
        return;
      }
      const screenOV = new OpenVidu();
      const screenSession = screenOV.initSession();
      const screenSubscribers = [];

      screenSession.on("streamCreated", ({ stream }) => {
        const screenSubscriber = screenSession.subscribe(stream);
        screenSubscribers.push(screenSubscriber);
      });

      screenSession.on("streamDestroyed", ({ stream }) => {
        const index2 = screenSubscribers.indexOf(stream.streamManager, 0);
        if (index2 >= 0) {
          screenSubscribers.splice(index2, 1);
        }
      });

      this.getToken(this.mySessionId)
        .then(token => {
          let screenPublisher = screenOV.initPublisher(undefined, {
            audioSource: false, // The source of audio. If undefined default microphone
            videoSource: "screen", // The source of video. If undefined default webcam
            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
            publishVideo: true, // Whether you want to start publishing with your video enabled or not
            resolution: "640x480", // The resolution of your video
            frameRate: 30, // The frame rate of your video
            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
            mirror: true // Whether to mirror your local video or not
          });
          screenSession
            .connect(token, { clientData: this.myUserName + "화면" })
            .then(() => {
              screenPublisher.once("accessAllowed", event => {
                screenPublisher.stream
                  .getMediaStream()
                  .getVideoTracks()[0]
                  .addEventListener("ended", () => {
                    this.stopShareScreen();
                  });
                screenSession.publish(screenPublisher);
                this.screenOV = screenOV;
                this.screenSession = screenSession;
                this.screenMainStreamManager = screenMainStreamManager;
                this.screenPublisher = screenPublisher;
                this.screenOvToken = token;
                this.session.signal({
                  data: "T",
                  to: [],
                  type: "share"
                });
              });
            });
          screenPublisher.once("accessDenied", event => {
            console.warn("ScreenShare: Access Denied");
          });
        })
        .catch(err => {
          console.log(
            "There was an error connecting to the session:",
            err.code,
            err.message
          );
        });
    },
    stopShareScreen() {
      if (this.screeSession) {
        state.screeSession.disconnect();
      }
      (this.screenOV = undefined),
        (this.screenSession = undefined),
        (this.screenMainStreamManager = undefined),
        (this.screenPublisher = undefined),
        (this.screenSubscribers = []),
        (this.screenOvToken = null);

      this.session.signal({
        data: "F",
        to: [],
        type: "share"
      });
    },

    // 사이드 패널
    changeContent(content) {
      if (content === "chatting") {
        this.chatting = true;
        this.participant = false;
        this.selectinterviewee = false;
        this.commonquestion = false;
      } else if (content === "participant") {
        this.chatting = false;
        this.participant = true;
        this.selectinterviewee = false;
        this.commonquestion = false;
      } else if (content === "selectinterviewee") {
        this.chatting = false;
        this.participant = false;
        this.selectinterviewee = true;
        this.commonquestion = false;
      } else if (content === "commonquestion") {
        this.chatting = false;
        this.participant = false;
        this.selectinterviewee = false;
        this.commonquestion = true;
      }
    },

    //메시지
    sendMsg(msg) {
      // Sender of the message (after 'session.connect')
      this.session
        .signal({
          data: msg,
          to: [],
          type: "my-chat"
        })
        .then(() => {
          console.log("Message successfully sent");
        })
        .catch(error => {
          console.error(error);
        });
    },
    // update 함수
    async sendUpdate(interviewUserFixed) {
      await this.session
        .signal({
          data: interviewUserFixed,
          to: [],
          type: "main-update"
        })
        .then(() => {
          console.log("update제대로 움직임");
        })
        .catch(err => {
          console.log(err.response);
        });
    },

    async sendTTS(isTTSMode) {
      console.log("sendTTS", isTTSMode);
      await this.session.signal({
        data: isTTSMode,
        to: [],
        type: "tts-mode"
      });
    },

    changeSessionId(sessionNo) {
      return `Session${sessionNo}`;
    },
    moveToStudy() {
      router.push({
        name: "studydetail",
        params: { stdNo: this.sessionNo }
      });
    }
  },
  watch: {
    ttsrequestcontext() {
      console.log("이게 많이 실행되나?");
      this.ttspublish(this.ttsrequestcontext);
    }
  },
  async created() {
    await this.bringStudySpaceDetail(this.sessionNo);
    this.userType = this.studySpaceDetail.joinType;
    console.log(this.studySpaceDetail);
  },
  async beforeMount() {
    this.myUserName = await this.currentUser.userName;
    this.myUserId = await this.currentUser.userId;
    this.myUserImg = await this.currentUser.userImg;
    this.myJoinType = await this.studySpaceDetail.joinType;
    console.log("this.myUserId : ", this.myUserId);
    this.mySessionId = await this.changeSessionId(this.sessionNo);
    await this.joinSession();
    // await this.showDivs(1);
  }
  // async mounted() {
  //   console.log("걸리나?");
  //   await this.showDivs(1);
  //   console.log("걸렸다");
  // }
};
</script>
