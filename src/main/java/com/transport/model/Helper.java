package com.transport.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Proxima on 09.08.2016.
 */

@Entity
@Table(name = "helper_entity")
public class Helper extends AbstractEmployeeEntity {
    public Helper(String firstName, String lastName, String mail, String phone,boolean enabled) {
        super(firstName, lastName, mail, phone,enabled);
    }

    public Helper(){
    }
}
