package com.transport.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Proxima on 11.08.2016.
 */
@Entity
@Table(name = "mover_entity")
public class Mover extends AbstractEmployeeEntity {
    public Mover(String firstName, String lastName, String mail, String phone, boolean enabled) {
        super(firstName, lastName, mail, phone, enabled);
    }

    public Mover(){

    }

}
