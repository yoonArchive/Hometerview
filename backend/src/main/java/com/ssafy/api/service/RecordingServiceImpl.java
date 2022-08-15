package com.ssafy.api.service;

import com.ssafy.api.request.RecordingPostReq;
import com.ssafy.db.entity.Recording;
import com.ssafy.db.entity.Study;
import com.ssafy.db.repository.RecordingRepository;
import com.ssafy.db.repository.RecordingRepositorySupport;
import com.ssafy.db.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordingServiceImpl implements RecordingService{

    private final RecordingRepository recordingRepository;

    private final RecordingRepositorySupport recordingRepositorySupport;

    private final StudyRepository studyRepository;

    @Override
    public Recording writeRecord(Long stdNo, RecordingPostReq recordingPostReq) {
        Study study = studyRepository.findByStdNo(stdNo).get();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Recording recording = Recording.builder()
                .study(study)
                .userId(recordingPostReq.getUserId())
                .saveDate(dataFormat.format(now))
                .videoUrl(recordingPostReq.getVideoUrl())
                .build();
        return recordingRepository.save(recording);
    }

    @Override
    public List<Recording> getRecordList(String userId) {
        return recordingRepositorySupport.findAllRecordingByUserNo(userId);
    }

    @Override
    public Recording getRecordByRecordNo(Long recordNo) {
        return recordingRepository.findByRecordNo(recordNo).orElse(null);
    }

    @Override
    public int deleteRecording(Long recordNo) {
        try {
            recordingRepository.findByRecordNo(recordNo).get();
        } catch (Exception e) {
            return 0;
        }
        recordingRepository.deleteByRecordNo(recordNo);
        return 1;
    }
}
