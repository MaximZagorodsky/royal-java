package com.transport.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by 1 on 6/4/2016.
 */
@Entity
@Table(name = "order_entity")
@Data
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "order_number")
    @GeneratedValue
    private Integer orderNumber;
    private String company; //enums


    private String advertisement;
    private String sizeOfMove; //enums
    private String storageSize;
    private boolean isLabor;

    private String status; //enums
    private Integer truck; //enums
    private Integer movers; //enums
    private Timestamp orderDay;
    private String heavyItemPrice; //enums
    private String serviceCharge;//enums
    private Integer shrink; //enums
    private Integer tape; //enums
    private Integer ddt;
    private Integer rateOrFlat;
    private BigDecimal priceForEachHour;
    private Integer totalPricePerFirstHours;
    private BigDecimal addExtra;
    private BigDecimal discount; //enums
    private String fieldForSalesmanComments;
    private String fieldForManagerComments;
    private Timestamp followUpDate;
    private String distance;

    private String moveDateTime;
    private String estimateDateTime;
//    private String p

    private Timestamp createdTime;
    private BigDecimal totalPrice;
    private Integer ddtPrice;
    private Integer totalHour;


    private BigDecimal workTime;        // salaryCount
    private Integer paymentMethod; // SalaryCount
    @Column(name = "end_pick_date") // salaryConut
    private Timestamp endPickUpDate; // salaryConut

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(targetEntity = com.transport.model.Address.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "unloading_address", referencedColumnName = "id")
    private Set<Address> unloadingAddress;

    @OneToMany(targetEntity = com.transport.model.Address.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "loading_address", referencedColumnName = "id")
    private Set<Address> loadingAddress;

    @Column(name = "box_delivered_date")
    private Long boxDeliveredDate;

    //Date for observing and planning of movement by supervisor

    @Column(name = "estimate_date")
    private Timestamp estimateDate;

    @Column(name = "package_date")
    private Timestamp packageDate;


    @Column(name = "start_pickUp_date")

    private Timestamp pickUpDate;

    @Column(name = "move_date")
    private Timestamp moveDate;

    @Column(name = "storage_date")
    private Timestamp storageDate;

    @Column(name = "is_stored")
    private boolean isStored;

    private Timestamp durationStorage;

    @Column(name = "box_quantity")
    private Integer boxQuantity;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n orderNumber=" + orderNumber +
                ",\n company='" + company + '\'' +
//                ",\n fullName='" + fullName + '\'' +
//                ",\n phoneNumber='" + phoneNumber + '\'' +
//                ",\n mail='" + mail + '\'' +
                ",\n advertisement='" + advertisement + '\'' +
                ",\n sizeOfMove='" + sizeOfMove + '\'' +
                ",\n storageSize='" + storageSize + '\'' +
                ",\n isLabor=" + isLabor +
                ",\n status=" + status +
                ",\n truck=" + truck +
                ",\n movers='" + movers + '\'' +
                ",\n orderDay=" + orderDay +
                ",\n heavyItemPrice='" + heavyItemPrice + '\'' +
                ",\n serviceCharge='" + serviceCharge + '\'' +
                ",\n shrink='" + shrink + '\'' +
                ",\n tape='" + tape + '\'' +
                ",\n ddt=" + ddt +
                ",\n rateOrFlat=" + rateOrFlat +
                ",\n priceForEachHour=" + priceForEachHour +
                ",\n addExtra=" + addExtra +
                ",\n discount=" + discount +
                ",\n totalPricePerFirstHours=" + totalPricePerFirstHours +
                ",\n fieldForSalesmanComments='" + fieldForSalesmanComments + '\'' +
                ",\n fieldForManagerComments='" + fieldForManagerComments + '\'' +
                ",\n followUpDate=" + followUpDate +
                ",\n workTime=" + workTime +
                ",\n paymentMethod=" + paymentMethod +
                ",\n endPickUpDate=" + endPickUpDate +
                ",\n client=" + client +
                ",\n unloadingAddress=" + unloadingAddress +
                ",\n loadingAddress=" + loadingAddress +
                ",\n boxDeliveredDate=" + boxDeliveredDate +
                ",\n estimateDate=" + estimateDate +
                ",\n packageDate=" + packageDate +
                ",\n pickUpDate=" + pickUpDate +
                ",\n moveDate=" + moveDate +
                ",\n storageDate=" + storageDate +
                ",\n isStored=" + isStored +
                ",\n durationStorage=" + durationStorage +
                ",\n boxQuantity=" + boxQuantity +
                ",\n moveDateTime=" + moveDateTime+
                ",\n estimateDateTime=" + estimateDateTime +
                ",\n totalPrice=" + totalPrice +
                '}';
    }
}
