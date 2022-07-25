package com.ssafy.api.service;

import com.ssafy.api.request.NoticeWritePostReq;
import com.ssafy.api.request.UpdateNoticePutReq;
import com.ssafy.db.entity.Notice;
import com.ssafy.db.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice getByNoticeNo(Long noticeNo) {
        return noticeRepository.findByNoticeNo(noticeNo).orElse(null);
    }

    @Override
    public Notice writeNotice(NoticeWritePostReq noticeWritePostReq) {
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeWritePostReq.getNoticeTitle());
        notice.setNoticeContents(noticeWritePostReq.getNoticeContents());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        notice.setWriteDate(dataFormat.format(now));
        noticeRepository.save(notice);
        return notice;
    }

    @Override
    public List<Notice> listNotice() {
        return noticeRepository.findAll();
    }

    @Override
    @Transactional
    public void updateNotice(Notice notice, UpdateNoticePutReq updateNoticePutReq) {
        notice.setNoticeTitle(updateNoticePutReq.getNoticeTitle());
        notice.setNoticeContents(updateNoticePutReq.getNoticeContents());
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        notice.setWriteDate(dataFormat.format(now));
    }

    @Override
    @Transactional
    public int deleteNotice(Long noticeNo) {
        try {
            noticeRepository.findByNoticeNo(noticeNo).get();
        } catch (Exception e) {
            return 0;
        }
        noticeRepository.deleteByNoticeNo(noticeNo);
        return 1;
    }

}
