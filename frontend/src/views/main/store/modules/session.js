import axios from 'axios'
import api_url from '@/api/api_url'
import router from '@/common/lib/vue-router'


export default {

  state: {
    ovToken : '', //64 인코딩해야할지도
    OPENVIDU_SERVER_SECRET : "admin"
  },
  getters: {
    openViduHeader: state => ({ 
      Authorization: `Basic ${state.ovToken}`,
      'Content-Type': 'application/json'
    }),
  },
  mutations: {
    SET_OV_TOKEN : (state,ovToken) => state.ovToken = ovToken

  },
  actions: {
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(
						`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, 
						JSON.stringify({
						customSessionId: sessionId,
						}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
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
						
						console.log(err.response)
					});
			});
		},



  },
}
