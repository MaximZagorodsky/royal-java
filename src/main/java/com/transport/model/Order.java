package com.transport.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;
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
    private String fullName;
    private String phoneNumber;
    @Email
    private String mail;

    private String advertisement;
    private String sizeOfMove; //enums
    private String storageSize;
    private boolean isLabor;

    private String  status; //enums
    private Integer truck; //enums
    private Integer movers; //enums
    private Long orderDay;
    private String heavyItemPrice; //enums
    private String serviceCharge;//enums
    private Integer shrink; //enums
    private Integer tape; //enums
    private Integer ddt;
    private Integer rateOrFlat;
    private Integer totalForFirstHours;
    private Double addExtra;
    private Double discount; //enums
    private Integer totalPricePerFirstHours;
    private String fieldForSalesmanComments;
    private String fieldForManagerComments;
    private Long followUpDate;
    private String distance;


    private Double workTime;        // salaryCount
    private Integer paymentMethod; // SalaryCount
    @Column(name = "end_pick_date") // salaryConut
    private Long endPickUpDate; // salaryConut

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
    private Long estimateDate;

    @Column(name = "package_date")
    private Long packageDate;


    @Column(name = "start_pickUp_date")

    private Long pickUpDate;

    @Column(name = "move_date")
    private Long moveDate;

    @Column(name = "storage_date")
    private Long storageDate;

    @Column(name = "is_stored")
    private boolean isStored;

    private Long durationStorage;

    @Column(name = "box_quantity")
    private Integer boxQuantity;


   /* public Integer getId() {
        return id;
    }

public void setEstimateDate(String date) {
        this.estimateDate = StringToLongConverter.convertDate(date);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }

    public String getSizeOfMove() {
        return sizeOfMove;
    }

    public void setSizeOfMove(String sizeOfMove) {
        this.sizeOfMove = sizeOfMove;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }

    public boolean isLabor() {
        return isLabor;
    }

    public void setLabor(boolean labor) {
        isLabor = labor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTruck() {
        return truck;
    }

    public void setTruck(Integer truck) {
        this.truck = truck;
    }

    public String getMovers() {
        return movers;
    }

    public void setMovers(String movers) {
        this.movers = movers;
    }

    public Long getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(Long orderDay) {
        this.orderDay = orderDay;
    }

    public String getHeavyItemPrice() {
        return heavyItemPrice;
    }

    public void setHeavyItemPrice(String heavyItemPrice) {
        this.heavyItemPrice = heavyItemPrice;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getShrink() {
        return shrink;
    }

    public void setShrink(String shrink) {
        this.shrink = shrink;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }

    public Integer getDdt() {
        return ddt;
    }

    public void setDdt(Integer ddt) {
        this.ddt = ddt;
    }

    public Integer getRateOrFlat() {
        return rateOrFlat;
    }

    public void setRateOrFlat(Integer rateOrFlat) {
        this.rateOrFlat = rateOrFlat;
    }

    public Integer getTotalForFirstHours() {
        return totalForFirstHours;
    }

    public void setTotalForFirstHours(Integer totalForFirstHours) {
        this.totalForFirstHours = totalForFirstHours;
    }

    public Double getAddExtra() {
        return addExtra;
    }

    public void setAddExtra(Double addExtra) {
        this.addExtra = addExtra;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPricePerFirstHours() {
        return totalPricePerFirstHours;
    }

    public void setTotalPricePerFirstHours(Double totalPricePerFirstHours) {
        this.totalPricePerFirstHours = totalPricePerFirstHours;
    }

    public String getFieldForSalesmanComments() {
        return fieldForSalesmanComments;
    }

    public void setFieldForSalesmanComments(String fieldForSalesmanComments) {
        this.fieldForSalesmanComments = fieldForSalesmanComments;
    }

    public String getFieldForManagerComments() {
        return fieldForManagerComments;
    }

    public void setFieldForManagerComments(String fieldForManagerComments) {
        this.fieldForManagerComments = fieldForManagerComments;
    }

    public Long getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(Long followUpDate) {
        this.followUpDate = followUpDate;
    }

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getEndPickUpDate() {
        return endPickUpDate;
    }

    public void setEndPickUpDate(Long endPickUpDate) {
        this.endPickUpDate = endPickUpDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Address> getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(Set<Address> unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public Set<Address> getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(Set<Address> loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public Long getBoxDeliveredDate() {
        return boxDeliveredDate;
    }

    public void setBoxDeliveredDate(Long boxDeliveredDate) {
        this.boxDeliveredDate = boxDeliveredDate;
    }

    public Long getEstimateDate() {
        return estimateDate;
    }


    public Long getPackageDate() {
        return packageDate;
    }

    public void setPackageDate(String packageDate) {
        this.packageDate = StringToLongConverter.convertDate(packageDate);
    }

    public Long getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Long pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public Long getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(String moveDate) {
        this.moveDate = StringToLongConverter.convertDate(moveDate);
    }

    public Long getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Long storageDate) {
        this.storageDate = storageDate;
    }

    public boolean isStored() {
        return isStored;
    }

    public void setStored(boolean stored) {
        isStored = stored;
    }

    public Long getDurationStorage() {
        return durationStorage;
    }

    public void setDurationStorage(Long durationStorage) {
        this.durationStorage = durationStorage;
    }

    public Integer getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(Integer boxQuantity) {
        this.boxQuantity = boxQuantity;
    }
*/

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ",\n orderNumber=" + orderNumber +
                ",\n company='" + company + '\'' +
                ",\n fullName='" + fullName + '\'' +
                ",\n phoneNumber='" + phoneNumber + '\'' +
                ",\n mail='" + mail + '\'' +
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
                ",\n totalForFirstHours=" + totalForFirstHours +
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
                '}';
    }
}
