package com.transport.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Victoria on 6/26/2016.
 */
@Entity
@Data
@Table(name = "vehicle_entity")
@EqualsAndHashCode()
public class Vehicle {
    @Id
    @GeneratedValue
    private Integer id;
    private String type;
    private String vehicleRegNumber;
    private boolean isAvaliable;
    private Timestamp bookingDate;
    private String periodOfDay;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<BookedTime> bookedTime;

    public Vehicle(String type, String regNumber, boolean isAvaliable, String periodOfDay) {
        this.vehicleRegNumber = regNumber;
        this.isAvaliable = isAvaliable;
        this.type = type;
        this.periodOfDay = periodOfDay;

    }

    public Vehicle(Integer id, String type, String regNumber,
                   boolean isAvaliable, String periodOfDay, Timestamp bookingDate) {
        this.id = id;
        this.vehicleRegNumber = regNumber;
        this.isAvaliable = isAvaliable;
        this.type = type;
        this.periodOfDay = periodOfDay;
        this.bookingDate = bookingDate;
    }

    public Vehicle(String type, String regNumber, boolean isAvaliable,
                   String periodOfDay, Timestamp bookingDate) {
        this.vehicleRegNumber = regNumber;
        this.isAvaliable = isAvaliable;
        this.type = type;
        this.periodOfDay = periodOfDay;
        this.bookingDate = bookingDate;
    }

    public Vehicle() {

    }
}

