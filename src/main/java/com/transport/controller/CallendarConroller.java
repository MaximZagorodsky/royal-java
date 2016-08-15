package com.transport.controller;

import com.transport.service.iml.CalendarEventServiceImpl;
import com.transport.util.time.StringToLongConverter;
import com.transport.util_entity.CalendarEntity;
import com.transport.util_entity.CalendarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Victoria on 6/23/2016.
 */
@Controller
@RequestMapping("api")
public class CallendarConroller {

    @Autowired
    CalendarEventServiceImpl calendarEventService;

    //    @CrossOrigin()
    @RequestMapping(
            value = {"/events"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public ResponseEntity<CalendarResponse> getCalendarData() {
        CalendarResponse calendarResponse = new CalendarResponse();
        List<CalendarEntity> events = calendarEventService.getEvents();
        calendarResponse.setCalendarEntity(events);
        calendarResponse.setCountByStatus(calendarEventService.createResponse(events));
        calendarResponse.setCurrentDate(StringToLongConverter.getCurrentDateTOString());
        return new ResponseEntity(calendarResponse, HttpStatus.OK);
    }

    //    @CrossOrigin()
    @RequestMapping(
            value = {"/currentDate"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    public ResponseEntity<List<CalendarEntity>> getCurrentDate() {
        CalendarResponse calendarResponse = new CalendarResponse();
        calendarResponse.setCurrentDate(StringToLongConverter.getCurrentDateTOString());
        return new ResponseEntity(calendarResponse, HttpStatus.OK);
    }
}
