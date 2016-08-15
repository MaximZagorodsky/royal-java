package com.transport.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

/**
 * Created by Proxima on 10.08.2016.
 */

@MappedSuperclass
@Data
public abstract class AbstractEmployeeEntity {
    @Id
    @GeneratedValue
    private Integer id;

    public AbstractEmployeeEntity() {
    }

    public AbstractEmployeeEntity(String firstName, String lastName, String mail, String phone,boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.enabled = enabled;

    }

    @Column(name = "full_name")
    private String firstName;
    private String lastName;
    private String position;
    private boolean enabled;
    @Email
    @Column(name = "mail", unique = false)
    private String mail;
    private String phone;
    private String password;
//    @OneToMany(targetEntity = Address.class)
//    @JoinColumn(name = "employee_address", referencedColumnName = "id")
//    private Set<Order> order;

    private Double rate;
    private Double workTime;

    private BigDecimal fine;
    private BigDecimal expenses;
    private BigDecimal salary;
    private BigDecimal bonuses;
}
