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
import java.util.Objects;

/**
 * Created by Proxima on 29.07.2016.
 */
@Service
public class CalendarEventServiceImpl implements CalendarEventService {

    public static final String BOOKED = "Booked";
    public static final String COMPLETED = "Completed";
    public static final String IN_PROGRESS = "In progress";
    public static final String SOLD = "Sold";
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
    public List<CalendarEntity> getEventsByOrderCountStatus(final String booked, String completed, String inProgress, String sold) {
        List<CalendarEntity> events = getEvents();
        List<CalendarEntity> eventsByOrderStatus = new ArrayList<>();

        for (CalendarEntity calEntity : events) {
            if (Objects.equals(calEntity.getOrderStatus(), booked)) {
                eventsByOrderStatus.add(calEntity);
            } else if (Objects.equals(calEntity.getOrderStatus(), completed)) {
                eventsByOrderStatus.add(calEntity);
            } else if (Objects.equals(calEntity.getOrderStatus(), sold)) {
                eventsByOrderStatus.add(calEntity);
            } else if (Objects.equals(calEntity.getOrderStatus(), inProgress)) {
                eventsByOrderStatus.add(calEntity);
            }
        }
        return eventsByOrderStatus;
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
                case BOOKED:
                    booked++;
                    break;
                case COMPLETED:
                    completed++;
                    break;
                case IN_PROGRESS:
                    inProgress++;
                    break;
                case SOLD:
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
                calendarEntity.setStart(converLongToString(item.getStart().getTime()));
                calendarEntity.setOrderStatus(item.getOrderStatus());
                if (item.getEnd() != null) {
                    calendarEntity.setEnd(converLongToString(item.getEnd().getTime()));
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

