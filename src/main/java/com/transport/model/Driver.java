package com.transport.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Proxima on 09.08.2016.
 */

@Entity
@Table(name = "driver_entity")
public class Driver extends AbstractEmployeeEntity {
//    private boolean enabled;
    public Driver(String firstName, String lastName, String mail, String phone, boolean enabled) {
        super(firstName, lastName, mail, phone, enabled);
    }

    public Driver() {

    }

}
