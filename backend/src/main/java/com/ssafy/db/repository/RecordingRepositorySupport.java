package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QRecording;
import com.ssafy.db.entity.QResumeDetail;
import com.ssafy.db.entity.Recording;
import com.ssafy.db.entity.ResumeDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordingRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    QRecording qRecording = QRecording.recording;

    public List<Recording> findAllRecordingByUserNo(String userId) {
        List<Recording> recordings = jpaQueryFactory.select(qRecording).from(qRecording)
                .where(qRecording.userId.eq(userId)).fetch();
        if (recordings == null) return null;
        return recordings;
    }
}
