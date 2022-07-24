package com.ssafy.api.service;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.db.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    Notice getByNoticeNo(Long NoticeNo);

    Notice writeNotice(NoticeWritePostReq noticeWritePostReq);

    List<Notice> listNotice();

    void updateNotice(Notice notice, UpdateNoticePutReq updateNoticePutReq);

    int deleteNotice(Long noticeNo);
}
