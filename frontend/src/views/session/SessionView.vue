<template>
  <div id="session-header">
    <h1 id="session-title">{{ mySessionId }}</h1>
    <input
      class="btn btn-large btn-danger"
      type="button"
      id="buttonLeaveSession"
      @click="leaveSession"
      value="Leave session"
    />
  </div>
  <div class="container d-flex justify-content-between">
    <!-- session -->
    <div id="session">
      <!-- 자기 화면(큰) => mainStreamManager-->
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div>

      <!-- 장치 옵션 -->
      <div>
        <!-- 마이크 ONOFF-->
        <div>
          <button v-if="audioOnoff" @click="audioONOFF()">오디오ON</button>
          <button v-else @click="audioONOFF()">오디오OFF</button>
        </div>
        <!-- 비디오 ONOFF -->
        <div>
          <button v-if="videoOnoff" @click="videoONOFF()">
            비디오ON {{ videoOnoff }}
          </button>
          <button v-else @click="videoONOFF()">
            비디오OFF {{ videoOnoff }}
          </button>
        </div>
        <!-- 화면 공유 -->
        <div>
          <button @click="ShareScreen()">화면 공유</button>
        </div>
        <!-- 더보기 -->
        <div>
          <button></button>
        </div>
      </div>

      <!-- 비디오 그룹 -->
      <!-- 비디오를 클릭할 경우 메인 비디오로 이동 : updateMainVideoStreamManager -->
      <div id="video-container" class="col-md-6">
        <!-- 자기화면 (작은) -->
        <user-video
          :stream-manager="publisher"
          @click="updateMainVideoStreamManager(publisher)"
        />
        <!-- native : 상위 컴포넌트(즉 여기 있는 이벤트)를 하위 컴포넌트에서 작동시키고 싶을 때 사용한다. -->
        <!-- vue3에서 native가 사라지고 그냥 click을 누르면 된다. -->
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click="updateMainVideoStreamManager(sub)"
        />
      </div>
    </div>

    <!-- 사이드  -->
    <div></div>
    <div class="side-panel">
      <button @click="changeContent('chatting')">메시지</button>
      <button @click="changeContent('participant')">참가자</button>
      <button @click="changeContent('selectinterviewee')">면접자 지정</button>
      <!-- 메시지 -->
      <div v-if="chatting">
        <message-list
          :msgs="msgs"
          :myId="publisher.stream.connection.connectionId"
          :fromId="fromId"
        ></message-list>
        <message-form @sendMsg="sendMsg" :user-name="myUserName"></message-form>
      </div>
      <!-- 멤버 리스트 -->
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
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import router from "@/common/lib/vue-router";
import { mapActions, mapGetters } from "vuex";

import UserVideo from "./components/UserVideo";
import MessageForm from "./components/MessageForm.vue";
import MessageList from "./components/MessageList.vue";
import StudyMemberList from "./components/StudyMemberList.vue";
import SelectInterviewee from "./components/SelectInterviewee.vue";

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
    ...mapGetters(["currentUser", "studySpaceDetail", "interviewUserFixed"])
  },

  methods: {
    ...mapActions(["bringStudySpaceDetail"]),
    videoONOFF() {
      console.log(this.videoOnOff);
      this.publisher.publishVideo(!this.videoOnOff);
      this.videoOnOff = !this.videoOnOff;
    },
    audioONOFF() {
      console.log(this.audioOnOff);
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
<style scoped>
.side-panel {
  width: 400px;
}</style
>f
