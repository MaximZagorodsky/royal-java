package com.transport.enums;

/**
 * Created by Proxima on 19.08.2016.
 */
public enum ParserPatterEnum {
    EEE_MMM_dd_HH_mm_ss_Z_yyyy("EEE MMM dd HH:mm:ss Z yyyy");

    private String parsFormat;

    public String getParsFormat() {
        return parsFormat;
    }

    public void setParsFormat(String parsFormat) {
        this.parsFormat = parsFormat;
    }

    ParserPatterEnum(String parsFormat) {

        this.parsFormat = parsFormat;
    }
}
