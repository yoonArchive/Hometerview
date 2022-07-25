package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RecruitStatus {
    RECRUITING("모집 중"),
    COMPLETED("모집 완료");

    final private String status;

    private RecruitStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static RecruitStatus nameOf(String status) {
        for (RecruitStatus recruitStatus : RecruitStatus.values()) {
            if (recruitStatus.getStatus().equals(status)) {
                return recruitStatus;
            }
        }
        return null;
    }

}
