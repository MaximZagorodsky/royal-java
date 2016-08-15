package com.transport.service.iml;

import com.transport.model.Event;
import com.transport.repository.EventRepository;
import com.transport.service.CalendarEventService;
import com.transport.util_entity.CalendarEntity;
import com.transport.util_entity.CountByStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Proxima on 29.07.2016.
 */
@Service
public class CalendarEventServiceImpl implements CalendarEventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<CalendarEntity> getEvents() {
        return convertEvents();
    }

    @Override
    public CountByStatus createResponse(List<CalendarEntity> events) {
        int completed = 0;
        int booked = 0;
        int inProgress = 0;
        int sold = 0;
        System.out.println("orderStatuses:" + events);


        for (CalendarEntity ev : events) {
            System.out.println(ev.getOrderStatus());
            switch (ev.getOrderStatus()) {
                case "booked":
                    booked++;
                    break;
                case "completed":
                    completed++;
                    break;
                case "in progress":
                    inProgress++;
                    break;
                case "sold":
                    sold++;
                    break;
            }
        }
        return new CountByStatus(completed, booked, inProgress, sold, completed + booked + sold + inProgress);
    }


    private List<CalendarEntity> convertEvents() {
        List<Event> all = eventRepository.findAll();

        List<CalendarEntity> calendarEntityList = new ArrayList<>();
        if (all.isEmpty()) {
            return calendarEntityList;
        } else {
            all.isEmpty();
            for (Event item : all) {
                CalendarEntity calendarEntity = new CalendarEntity();
                calendarEntity.setOrderId(item.getOrderId());
                calendarEntity.setTitle(item.getTitle());
                calendarEntity.setStart(converLongToString(item.getStart()));
                calendarEntity.setOrderStatus(item.getOrderStatus());
                if (item.getEnd() != null) {
                    calendarEntity.setEnd(converLongToString(item.getEnd()));
                }
                calendarEntityList.add(calendarEntity);
            }
            return calendarEntityList;
        }
    }

    private String converLongToString(Long toConvert) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = "";
        try {

//            Date date = formatter.parse(dateInString);
//            System.out.println(date);
//            System.out.println("dataConverter: " + date.getTime());
            format = formatter.format(new Date(toConvert));
            System.out.println(format);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return format;
    }
}

