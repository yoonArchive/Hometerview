package com.ssafy.api.service;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.db.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    Notice writeNotice(NoticeWritePostReq noticeWritePostReq);
    List<Notice> listNotice();
//    Notice detailNotice(long noticeNo);
//    Notice updateNotice(Notice notice);
//    void deleteNotice(long noticeNo);
}
