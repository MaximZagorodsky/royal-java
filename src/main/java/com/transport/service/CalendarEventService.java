package com.transport.service;

import com.transport.util_entity.CalendarEntity;
import com.transport.model.Event;
import com.transport.util_entity.CountByStatus;

import java.util.List;

/**
 * Created by Proxima on 29.07.2016.
 */
public interface CalendarEventService {

    Event save(Event event);

    List<CalendarEntity> getEvents();

    CountByStatus createResponse(List<CalendarEntity> event);
}
