package com.transport.service;

import com.transport.ServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Proxima on 15.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class CalendarServiceTest {
    @Autowired
    CalendarEventService eventService;

    @Test
    public void check() {
        System.out.println(eventService.createResponse(eventService.getEvents()));
    }
}
