package com.ssafy.api.service;

import com.ssafy.api.request.ConferencePostReq;
import com.ssafy.db.repository.StudyJoinRepositorySupport;
import com.ssafy.db.repository.StudyRepository;
import io.openvidu.java.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    private OpenVidu openVidu;

    // Collection to pair session names and OpenVidu Session objects
    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    // Collection to pair session names and tokens (the inner Map pairs tokens and role associated)
    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();

    private String OPENVIDU_URL;

    private String SECRET;

    @Autowired
    StudyRepository studyRepository;

    private final StudyJoinRepositorySupport studyJoinRepositorySupport;


    public SessionServiceImpl(@Value("${openvidu.secret}") String secret,
                              @Value("${openvidu.url}") String openviduUrl,
                              StudyJoinRepositorySupport studyJoinRepositorySupport) {
        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
        this.studyJoinRepositorySupport = studyJoinRepositorySupport;
    }

    @Override
    public int enterSession(ConferencePostReq conferencePostReq, Long userNo) {
        String conferenceNo = Long.toString(conferencePostReq.getStdNo());
        OpenViduRole role = OpenViduRole.PUBLISHER;
        System.out.println("Getting a token from OpenVidu Server | {conferenceNo}=" + conferenceNo);
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .data(conferencePostReq.getStdName())
                .role(OpenViduRole.PUBLISHER)
                .build();
        if (this.mapSessions.get(conferenceNo) != null) { // 세션이 이미 존재하면 -> 입장
            System.out.println("Existing session " + conferenceNo);
            try {
                String token = this.mapSessions.get(conferenceNo).createConnection(connectionProperties).getToken();
                System.out.println("token : " + token);
                this.mapSessionNamesTokens.get(conferenceNo).put(token, role);
            } catch (OpenViduJavaClientException e1) {
                return 0;
            } catch (OpenViduHttpException e2) {
                if (404 == e2.getStatus()) {
                    this.mapSessions.remove(conferenceNo);
                    this.mapSessionNamesTokens.remove(conferenceNo);
                }
            }
        } else { // 세션이 존재하지 않으면 새로운 화상회의 방 생성
            System.out.println("New session " + conferenceNo);
            try {
                Session session = this.openVidu.createSession();
                String token = session.createConnection(connectionProperties).getToken();
                System.out.println("token : " + token);
                this.mapSessions.put(conferenceNo, session);
                this.mapSessionNamesTokens.put(conferenceNo, new ConcurrentHashMap<>());
                this.mapSessionNamesTokens.get(conferenceNo).put(token, role);
            } catch (Exception e) {
                return 0;
            }
        }
        return 1;
    }

}
