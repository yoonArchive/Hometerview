package com.ssafy.api.service;

import com.ssafy.api.request.DdayReq;
import com.ssafy.db.entity.Dday;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.DdayRepository;
import com.ssafy.db.repository.DdayRepositorySupport;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class DdayServiceImpl implements DdayService {

    private final DdayRepository ddayRepository;

    private final DdayRepositorySupport ddayRepositorySupport;

    private final UserRepository userRepository;

    @Override
    public void writeDday(Long userNo, DdayReq ddayReq) {
        User user = userRepository.findByUserNo(userNo).get();
        Dday dday = Dday.builder()
                .user(user)
                .ddayTitle(ddayReq.getDdayTitle())
                .ddayDate(ddayReq.getDdayDate())
                .build();
        ddayRepository.save(dday);
    }

    @Override
    public List<Dday> getList(Long userNo) {
        return ddayRepositorySupport.findAllDdayByUserNo(userNo);
    }

    @Override
    public long[] getResults(List<Dday> ddays) throws ParseException {
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate todayDate = LocalDate.now();
        long[] results = new long[ddays.size()];
        int idx = 0;
        for (Dday dday : ddays) {
            String ddayDate = dday.getDdayDate();
            LocalDate date = LocalDate.parse(ddayDate, dataFormat);
            results[idx++] = DAYS.between(todayDate, date);
        }
        return results;
    }

    @Override
    public Dday getDday(Long ddayNo, Long userNo) {
        return ddayRepositorySupport.findDdayByDdayNoAndUserNo(ddayNo, userNo);
    }

    @Override
    public Dday getByDdayNo(Long ddayNo) {
        return ddayRepository.findByDdayNo(ddayNo).orElse(null);
    }

    @Override
    @Transactional
    public void updateDday(Dday dday, DdayReq ddayReq) {
        String ddayTitle = ddayReq.getDdayTitle();
        String ddayDate = ddayReq.getDdayDate();
        dday.initDday(ddayTitle, ddayDate);
    }

    @Override
    @Transactional
    public void deleteDday(Long ddayNo) {
        ddayRepository.deleteByDdayNo(ddayNo);
    }

}
