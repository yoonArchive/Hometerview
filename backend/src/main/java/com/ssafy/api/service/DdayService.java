package com.ssafy.api.service;

import com.ssafy.api.request.DdayReq;
import com.ssafy.db.entity.Dday;

import java.text.ParseException;
import java.util.List;

public interface DdayService {

    void writeDday(Long userNo, DdayReq ddayReq);

    List<Dday> getList(Long userNo);

    long[] getResults(List<Dday> ddays) throws ParseException;

    Dday getDday(Long ddayNo, Long userNo);

    Dday getByDdayNo(Long ddayNo);

    void updateDday(Dday dday, DdayReq ddayReq);

    void deleteDday(Long ddayNo);

}
