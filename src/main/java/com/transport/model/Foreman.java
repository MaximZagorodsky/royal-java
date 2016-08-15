package com.transport.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Proxima on 09.08.2016.
 */

@Entity
@Table(name = "foremen_entity")
public class Foreman extends AbstractEmployeeEntity {
    public Foreman(String firstName, String lastName, String mail, String phone,boolean enabled) {
        super(firstName, lastName, mail, phone,enabled);
    }

    public Foreman() {

    }
}
