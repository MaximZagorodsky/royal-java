package com.transport.enums;

/**
 * Created by Proxima on 14.08.2016.
 */
public enum StatusEnum {
    BOOKED("booked"),
    COMPLETED("completed"),
    IN_PROGRESS("in progress"),
    SOLD("sold");

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    StatusEnum(String status) {
        this.status = status;
    }

    private String status;
}
