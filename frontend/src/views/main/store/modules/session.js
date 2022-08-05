import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'
import { OpenVidu } from 'openvidu-browser'; 


export default {

  state: {
    OPENVIDU_SERVER_SECRET : "admin",
		OV: undefined,
		session: undefined,
		mainStreamManager: undefined,
		publisher: undefined, //local
		subscribers: [], // remotes

		//join form
		mySessionId: 'SessionA',

		//user name
		myUserName: 'Participant' + Math.floor(Math.random() * 100),
  },
  getters: {
		OPENVIDU_SERVER_SECRET : state => state.OPENVIDU_SERVER_SECRET,
		mySessionId : state => state.mySessionId,
		myUserName : state => state.myUserName,
		OV : state => state.OV,
		session : state=> state.session,
		mainStreamManager : state => state.mainStreamManager,
		publisher : state => state.publisher,
		subscribers : state => state.subscribers

  },
  mutations: {
    SET_OV : (state, OV) => state.OV = OV,
		SET_SESSION :(state, session) => state.session = session,
		SET_MAIN_STREAM_MANAGER : (state,mainStreamManager) => state.mainStreamManager = mainStreamManager,
		SET_PUBLISHER : (state,publisher) => state.publisher = publisher,
		SET_SUBSCRIBERS : (state,subscribers) => state.subscribers = subscribers,
		SET_MYSESSION_ID : (state, mySessionId) => state.mySessionId = mySessionId,
		SET_MY_USER_NAME : (state, myUserName) => state.myUserName = myUserName,

  },
  actions: {
		getToken ({dispatch},sessionInfo) {
			// 세션 만듦 ==> 성공? => craeteToken함수 실행
			// return dispatch.createSession(mySessionId).then(sessionId => dispatch.createToken(sessionId));
			return dispatch.createSession(sessionInfo).then(sessionId => dispatch.createToken(sessionId));
		},
		createSession ({getters},sessionInfo) {
			const username = sessionInfo[0]
			const sessionId = sessionInfo[1]
			return new Promise((resolve, reject) => {
				axios
					.post(
						// `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, 
						api_url.session.createSession(),
						JSON.stringify({
						customSessionId: sessionId,
						}), {
						auth: {
							username: 'OPENVIDUAPP',
							// username: username,
							password: getters.OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(err => {
						if (err.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(err.response);
						}
					});
			});
		},
		createToken ({getters},sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(api_url.session.createToken(sessionId), {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: getters.OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
		joinSession () {

			this.OV = new OpenVidu();
			this.session = this.OV.initSession();
			this.session.on('streamCreated', ({ stream }) => {
				
				//streamCreated 값 => user video에서 사용자의 닉네임을 자신의 비디오에 추가하는데 사용
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});


			this.session.on('streamDestroyed', ({ stream }) => {
				// 제일 앞에 있는 stream.streamManager의 인덱스 값을 저장
				const index = this.subscribers.indexOf(stream.streamManager, 0);

				if (index >= 0) {
					this.subscribers.splice(index, 1);
					// spice(index,1) => 인덱스에 있는거 제거
				}
			});

			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});


			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// 여기부터는 장치 정보
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

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},
  },
}
