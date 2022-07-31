package com.ssafy.api.service;

import com.ssafy.api.request.ConferencePostReq;

public interface SessionService {

    int enterSession(ConferencePostReq conferencePostReq, Long userNo);

}
