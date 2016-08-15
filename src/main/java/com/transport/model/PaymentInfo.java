package com.transport.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by 1 on 6/4/2016.
 */
@Entity
@Table(name="payment_entity")
@Data
public class PaymentInfo implements Serializable {
    @Id
    @GeneratedValue
    private Integer id ;
    @Column(name="order_number")
    private Integer orderNumber;


    private String advertisement;
    private boolean isLabor;

    private Integer status; //enums
    private Integer truck; //enums
    private String movers; //enums
    private Long   orderDay;
    private String heavyItem; //enums
    private String serviceCharge;//enums
    private String shrink; //enums
    private String tape; //enums
    private Integer ddt;
    private boolean  rateOrFlat;
    private Double  addExtra;
    private Double  discount; //enums
    private Double  totalPricePerFirstHours;
    private String  fieldForSalesmanComments;
    private String  fieldForManagerComments;
    private Long    followUpDate;



    private Double workTime;        // salaryCount
    private Integer paymentMethod; // SalaryCount
    @Column(name="end_pick_date") // salaryConut
    private Long endPickUpDate; // salaryConut

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="person_id")
    private Client client;

    @OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name="unloading_address", referencedColumnName="id")
    private Set<Address> unloadingAddress;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name="loading_address", referencedColumnName="id")
    private Set<Address> loadingAddress;

    @Column(name="box_delivered_date")
    private Long boxDeliveredDate;

    //Date for observing and planning of movement by supervisor

    @Column(name="estimate_date")
    private Long estimateDate;

    @Column(name="package_date")
    private Long packageDate;

    @Column(name="start_pickUp_date")
    private Long pickUpDate;

    @Column(name="is_stored")
    private boolean isStored;
    private Long    durationStorage;

    @Column(name="box_quantity")
    private Integer boxQuantity;

}

