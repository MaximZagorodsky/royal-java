package com.transport.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "calendar_events")
public class Event {
    private int orderId;
    private String title;
    private Long start;
    private Long end;
    private String orderStatus;

    @Id
    @GeneratedValue
    private int id;

}
