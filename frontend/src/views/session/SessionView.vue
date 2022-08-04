<template>
	<div id="main-container" class="container">
		<div id="join" v-if="!session">
			<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div>

		<!-- 세션을 들어 갔을 경우 -->
		<div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>
			<!-- 자기 화면(큰) => mainStreamManager-->
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div>

			<!-- 비디오 그룹 -->
			<!-- 비디오를 클릭할 경우 메인 비디오로 이동 : updateMainVideoStreamManager -->
			<div id="video-container" class="col-md-6">
				<!-- 자기화면 (작은) -->
				<user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
				<!-- native : 상위 컴포넌트(즉 여기 있는 이벤트)를 하위 컴포넌트에서 작동시키고 싶을 때 사용한다. -->
        <!-- vue3에서 native가 사라지고 그냥 click을 누르면 된다. -->
				<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser'; // 필수 객체
import UserVideo from './components/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';
// 글로벌 axios 기본(defaults) 설정 => application/json => 

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":8443";
const OPENVIDU_SERVER_SECRET = "admin";

// 8443
// admin

export default {
	name: 'SessionView',

	components: {
		UserVideo,
	},

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined, //local
			subscribers: [], // remotes

			//join form
			mySessionId: 'SessionA',

			//user name
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
		}
	},

	methods: {
		joinSession () {
			// --- OpenVidu Object 생성 ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// session.on은 언제 사용되는 것인지 확인 => 함수 실행 할때 쓰는 듯??
			// On every new Stream received ==> 새로운거 시작할 때 실행 됨(누군가가 새로 들어 올 경우)
			this.session.on('streamCreated', ({ stream }) => {
				//streamCreated 값 => user video에서 사용자의 닉네임을 자신의 비디오에 추가하는데 사용
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			//누군가 나갔을 때 실행됨
			this.session.on('streamDestroyed', ({ stream }) => {
				// 제일 앞에 있는 stream.streamManager의 인덱스 값을 저장
				const index = this.subscribers.indexOf(stream.streamManager, 0);

				if (index >= 0) {
					this.subscribers.splice(index, 1);
					// spice(index,1) => 인덱스에 있는거 제거
				}
			});

			// On every asynchronous exception...
			// exception 서버 측에서 비동기식 예기치 않은 오류가 발생할 때 Session 개체에 의해 트리거되는 이벤트
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				// 여기는 client에 대한 정보 보내기
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// 여기부터는 장치 정보
						// --- Get your own camera stream with the desired properties ---

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});

						// ??????
						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---
						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			// beforeunload 페이지를 떠날때 발생한다.새로고침 /뒤로 가기 /브라우저 닫기 /form submit
			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---

			// session을 떠날때는 disconnect 함수를 사용하면 됨
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			// 세션 만듦 ==> 성공? => craeteToken함수 실행
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
	}
}
</script>
