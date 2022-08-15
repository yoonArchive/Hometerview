package com.ssafy.api.service;

import com.ssafy.api.request.RecordingPostReq;
import com.ssafy.db.entity.Recording;

import java.util.List;

public interface RecordingService {

    Recording writeRecord(Long stdNo, RecordingPostReq recordingPostReq);

    List<Recording> getRecordList(String userId);

    Recording getRecordByRecordNo(Long recordNo);

    int deleteRecording(Long recordNo);
}
