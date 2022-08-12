<template>
  <div class="full-con">
    <div class="main-con">
      <!-- <div id="session-title">{{ mySessionId }}</div><br> -->
      <div class="con d-flex justify-content-between row">
        <div class="side-left col-md-8">
          <div class="center">
            <!-- 중앙 -->
            <!-- 메인 화면, 사이드 -->

            <!-- session -->
            <div class="video-group">
              <!-- 자기 화면(큰) => mainStreamManager-->
              <div id="main-video" class="col">
                <user-video
                  class="large-video"
                  :stream-manager="mainStreamManager"
                  :mainStream="true"
                />
              </div>

              <!-- 비디오 그룹 -->
              <div
                class="video-container row d-flex justify-content-center  mb-2"
              >
                <!-- 자기화면 (작은) -->
                <user-video
                  class="small-video my-video col-md-3"
                  :stream-manager="publisher"
                  :mainStream="false"
                  @click="updateMainVideoStreamManager(publisher)"
                />
                <!-- native : 상위 컴포넌트(즉 여기 있는 이벤트)를 하위 컴포넌트에서 작동시키고 싶을 때 사용한다. -->
                <!-- vue3에서 native가 사라지고 그냥 click을 누르면 된다. -->
                <user-video
                  class="user-video col-md-3"
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  :mainStream="false"
                  @click="updateMainVideoStreamManager(sub)"
                />
              </div>
            </div>

            <div>자세 분석</div>
            <div hidden="ture" ref="webcam"></div>
            <button type="button" @click="init()">Start</button>
            <div
              v-for="prediction in predictions"
              :key="prediction.className"
              style="color:whitesmoke;"
            >
              {{ prediction.className }}:
              {{ prediction.probability.toFixed(2) }}
            </div>

            <div class="bottom d-flex justify-content-evenly">
              <!-- 하단 -->
              <!-- 비디오, 오디오, leave, 더보기 -->
              <!-- 장치 옵션 -->

              <!-- 마이크 ONOFF-->
              <div>
                <!-- <div class="video-button"></div> -->
                <div v-if="audioOnOff" class="mic-button-on">
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  />
                </div>
                <div v-else class="mic-button-off">
                  <img
                    @click="audioONOFF()"
                    :src="require(`@/assets/images/session/micOn.png`)"
                    style="height:4vh; margin-left:1.5vh; margin-top:1vh;"
                  />
                </div>
                <!-- <button v-if="audioOnOff" @click="audioONOFF()">오디오ON</button>
                <button v-else @click="audioONOFF()">오디오OFF</button> -->
              </div>

              <!-- 비디오 ONOFF -->
              <div>
                <div v-if="videoOnOff" class="video-button-on">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  />
                </div>
                <div v-else class="video-button-off">
                  <img
                    @click="videoONOFF()"
                    :src="require(`@/assets/images/session/video.png`)"
                    style="height:2.4vh; margin-left: 1.2vh; margin-top: 1.8vh;"
                  />
                </div>
                <!-- <button v-if="videoOnOff" @click="videoONOFF()">비디오ON</button>
                <button v-else @click="videoONOFF()">비디오OFF</button> -->
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
              <!-- 더보기 -->
              <div>
                <div class="add-button">
                  <img
                    :src="require(`@/assets/images/session/add.png`)"
                    style="height:3.8vh; margin-left: 1vh; margin-top: 1.1vh;"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="side-right col-md-4">
          <!-- 사이드 -->
          <!-- 메시지, 자소서, 참가자 지정 -->
          <div class="side-panel">
            <div class="select-side-bottons">
              <div class="d-flex justify-content-start">
                <div>
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
                <div>
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
                <div>
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
                <div class="row">
                  <div class="col">
                    <img
                      :src="require(`@/assets/images/session/videoOn.png`)"
                      style="height:3vh; margin-top:%;"
                      v-if="recordOnOff"
                      @click="recordONOFF()"
                    />
                    <img
                      :src="require(`@/assets/images/session/videoOff.png`)"
                      style="height:3vh; margin-top:%;"
                      @click="recordONOFF()"
                      v-else
                    />
                  </div>
                  <div class="col">
                    <img
                      :src="require(`@/assets/images/session/stop.png`)"
                      style="height:3vh; margin-top:;"
                      @click="stop"
                      v-if="true"
                    />
                  </div>
                </div>
              </div>
              <!-- 메시지 -->
              <div v-if="chatting">
                <message-list
                  :msgs="msgs"
                  :myId="publisher.stream.connection.connectionId"
                  :fromId="fromId"
                ></message-list>
                <message-form
                  @sendMsg="sendMsg"
                  :user-name="myUserName"
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
            </div>
          </div>
          <!-- 멤버 리스트 -->
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
.add-button {
  background-color: #d9d9d9;
  height: 6vh;
  width: 6vh;
  border-radius: 100% 100% 100% 100%;
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
    SelectInterviewee
  },

  data() {
    return {
      // 방,유저 정보
      sessionNo: this.$route.params.sessionNo,
      userType: "",
      mySessionId: ``,
      myUserName: "",
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

      // massege
      msgs: [],
      fromId: "",

      //show
      chatting: true,
      participant: false,
      selectinterviewee: false,

      // function
      videoOnOff: true,
      audioOnOff: true,
      mirrorOnOff: true,
      recordOnOff: false,

      //scree share
      screenOV: undefined,
      screenSession: undefined,
      screenMainStreamManager: undefined,
      screenPublisher: undefined,
      screenSubscribers: [],
      screenOvToken: null,
      isSharingMode: false
    };
  },
  computed: {
    // studySpaceDetail.joinTyped
    ...mapGetters(["currentUser", "studySpaceDetail"])
  },
  methods: {
    ...mapActions(["bringStudySpaceDetail", "changeToCoverLetter"]),
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
      console.log("hi");
      this.webcam.update();
      await this.predict();
      window.requestAnimationFrame(this.loop);
    },
    async predict() {
      const { posenetOutput } = await this.model.estimatePose(
        this.webcam.canvas
      );
      console.log("check!!");
      this.predictions = await this.model.predict(posenetOutput);
      console.log("prediction");
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
      const { clientId } = JSON.parse(this.publisher.stream.connection.data);
      if (clientId === interviewUserFixed) {
        await this.updateMainVideoStreamManager(this.publisher);
        const studentindex = await this.findIndex(interviewUserFixed);
        this.chatting = false;
        this.participant = true;
        this.selectinterviewee = false;
        console.log("확인");
        console.log(studentindex);
        this.changeToCoverLetter(["coverletter", studentindex]);
      } else if (this.subscribers === true) {
        this.subscribers.forEach(async sub => {
          const { clientId } = JSON.parse(sub.stream.connection.data);
          if (clientId === interviewUserFixed) {
            const studentindex = await this.findIndex(interviewUserFixed);
            await this.updateMainVideoStreamManager(sub);
            this.chatting = false;
            this.participant = true;
            this.selectinterviewee = false;
            console.log("확인");
            console.log(studentindex);
            this.changeToCoverLetter(["coverletter", studentindex]);
          }
        });
      }
    },
    recordONOFF() {
      this.recordOnOff = !this.recordOnOff;
    },
    videoONOFF() {
      this.publisher.publishVideo(!this.videoOnOff);
      this.videoOnOff = !this.videoOnOff;
    },
    audioONOFF() {
      this.publisher.publishAudio(!this.audioOnOff);
      this.audioOnOff = !this.audioOnOff;
    },
    // mirrorONOFF(){
    // 	this.publisher.publishVideo(!this.mirrorOnOff);
    // 	this.mirrorOnOff = !this.mirrorOnOff;
    // },

    // 기본 기능 (입장하기 퇴장하기)
    joinSession() {
      // --- OpenVidu Object 생성 ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // session.on은 언제 사용되는 것인지 확인 => 함수 실행 할때 쓰는 듯??
      // On every new Stream received ==> 새로운거 시작할 때 실행 됨(누군가가 새로 들어 올 경우)
      this.session.on("streamCreated", ({ stream }) => {
        //streamCreated 값 => user video에서 사용자의 닉네임을 자신의 비디오에 추가하는데 사용
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      //누군가 나갔을 때 실행됨
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
        this.fromId = event.from.connectionId;
        const tmp = this.msgs.slice();
        tmp.push(event.data);
        this.msgs = tmp;
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
            // updateMainVideoStreamManager(publisher)
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
      // updateMainVideoStreamManager(undefined)
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

    // 화면 공유
    async ShareScreen() {
      if (this.screenPublisher) {
        await alert("화면공유가 중지됩니다");
        this.stopShareScreen();
      } else {
        await alert("화면공유를 시작합니다");
        this.startShareScree();
      }
    },
    startShareScree() {
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
      } else if (content === "participant") {
        this.chatting = false;
        this.participant = true;
        this.selectinterviewee = false;
      } else if (content === "selectinterviewee") {
        this.chatting = false;
        this.participant = false;
        this.selectinterviewee = true;
      }
    },

    // 사이드 패널
    changeContent(content) {
      console.log(content);
      if (content === "chatting") {
        this.chatting = true;
        this.participant = false;
        this.selectinterviewee = false;
      } else if (content === "participant") {
        this.chatting = false;
        this.participant = true;
        this.selectinterviewee = false;
      } else if (content === "selectinterviewee") {
        this.chatting = false;
        this.participant = false;
        this.selectinterviewee = true;
      }
      console.log(this.selectinterviewee);
    },

    //메시지
    sendMsg(msg) {
      // Sender of the message (after 'session.connect')
      this.session
        .signal({
          data: msg, // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: "my-chat" // The type of message (optional)
        })
        .then(() => {
          console.log("Message successfully sent");
        })
        .catch(error => {
          console.error(error);
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
  created() {
    this.bringStudySpaceDetail(this.sessionNo);
  },
  async beforeMount() {
    this.myUserName = await this.currentUser.userName;
    this.myUserId = await this.currentUser.userId;
    this.mySessionId = await this.changeSessionId(this.sessionNo);
    console.log("확인!!!!");
    console.log(this.myUserId);
    this.joinSession();
  },
  mounted() {
    this.userType = this.studySpaceDetail.joinType;
  }
};
</script>
