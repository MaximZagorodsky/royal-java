package com.transport.util_entity;

import lombok.Data;

/**
 * Created by Proxima on 14.08.2016.
 */
@Data
public class CountByStatus {
    private int completed;
    private int booked;
    private int inProgress;
    private int sold;
    private int totalCount;

    public CountByStatus() {
    }

    public CountByStatus(int completed, int booked, int inProgress, int sold, int totalCount) {
        this.completed = completed;
        this.booked = booked;

        this.inProgress = inProgress;
        this.sold = sold;
        this.totalCount = totalCount;
    }
}
