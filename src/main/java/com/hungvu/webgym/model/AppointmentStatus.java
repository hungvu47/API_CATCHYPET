package com.hungvu.webgym.model;

public enum AppointmentStatus {
    CHUA_XAC_NHAN("Chờ xác nhận"),
    DA_XAC_NHAN("Đã xác nhận"),
    HOAN_THANH("Hoàn thành"),
    HUY_BO("Hủy bỏ");

    private String value;

    AppointmentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
