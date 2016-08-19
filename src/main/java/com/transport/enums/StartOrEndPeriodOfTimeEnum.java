package com.transport.enums;

/**
 * Created by Proxima on 18.08.2016.
 */
public enum StartOrEndPeriodOfTimeEnum {
    START("start"), END("end");

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    private String period;

    StartOrEndPeriodOfTimeEnum(String period) {
        this.period = period;
    }
}
