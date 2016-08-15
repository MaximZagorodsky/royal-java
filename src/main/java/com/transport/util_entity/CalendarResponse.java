package com.transport.util_entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Proxima on 07.08.2016.
 */
@Data
public class CalendarResponse {
    private List<CalendarEntity> calendarEntity;
    private String currentDate;
    private CountByStatus countByStatus;
}
