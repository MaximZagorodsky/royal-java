package com.transport.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by maksim on 6/4/2016.
 */
@Entity
@Table(name = "address_entity")
@Data
public class Address implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String zip;
    private String address;
    private Integer floor;

    public Address(String address, String zip) {
        this.address = address;
        this.zip = zip;
    }

    public Address(){

    }
}
