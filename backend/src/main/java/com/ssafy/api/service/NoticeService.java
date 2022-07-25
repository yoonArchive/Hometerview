package com.ssafy.api.service;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.db.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoticeService {

<<<<<<< HEAD
    Notice getByNoticeNo(long NoticeNo);
=======
    Notice getByNoticeNo(Long NoticeNo);
>>>>>>> ccfc7cc558e0327edda16edb4938549edb466966

    Notice writeNotice(NoticeWritePostReq noticeWritePostReq);

    List<Notice> listNotice();

<<<<<<< HEAD
   // Notice detailNotice(long noticeNo);
    void updateNotice(Notice notice, UpdateNoticePutReq updateNoticePutReq);

    int deleteNotice(long noticeNo);

=======
    void updateNotice(Notice notice, UpdateNoticePutReq updateNoticePutReq);

    int deleteNotice(Long noticeNo);
>>>>>>> ccfc7cc558e0327edda16edb4938549edb466966
}
