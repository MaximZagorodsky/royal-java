package com.transport.form;


import com.transport.model.Address;
import com.transport.util.time.StringToLongConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by 1 on 6/10/2016.
 */
//@Data
public class OrderForm implements Serializable {

    private String company;  //enums
    private String fullName;
    private String mail;
    private String phoneNumber;
    private String advertisement;//1
    private String sizeOfMove;
    private String storageSize;

    private String moveDateTime;

    public String getEstimateDateTime() {
        return estimateDateTime;
    }

    public void setEstimateDateTime(String estimateDateTime) {
        this.estimateDateTime = estimateDateTime;
    }

    public String getMoveDateTime() {
        return moveDateTime;
    }

    public void setMoveDateTime(String moveDateTime) {
        this.moveDateTime = moveDateTime;
    }

    private String estimateDateTime;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    private String distance;
        @DateTimeFormat(pattern="MM/dd/yyyy")
    private Timestamp moveDate;
        @DateTimeFormat(pattern="MM/dd/yyyy")
    private Timestamp packingDate;
        @DateTimeFormat(pattern="MM/dd/yyyy")
    private Timestamp estimateDate;
    //    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Timestamp storageDate;
    //    @DateTimeFormat(pattern="HH:mm")
//    private Timestamp moveDateTime;
    //    @DateTimeFormat(pattern="HH:mm")
//    private Timestamp packingDateTime;
    //    @DateTimeFormat(pattern="HH:mm")
//    private Timestamp estimateDateTime;

    private boolean isLabor;

    private Set<Address> loadingAddress;

    public Set<Address> getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(Set<Address> loadingAddress) {
        this.loadingAddress = loadingAddress;
    }


    private Set<Address> unloadingAddress;
    //    private String loadingAddress;

    public Set<Address> getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(Set<Address> unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    //    private String unloadingAddress;
    private String tariff;

    @Override
    public String toString() {
        return "OrderForm{" +
                "company='" + company + '\'' +
                ",\n fullName='" + fullName + '\'' +
                ",\n mail='" + mail + '\'' +
                ",\n phoneNumber='" + phoneNumber + '\'' +
                ",\n advertisement='" + advertisement + '\'' +
                ",\n sizeOfMove='" + sizeOfMove + '\'' +
                ",\n storageSize='" + storageSize + '\'' +
                ",\n moveDate=" + moveDate +
                ",\n packingDate=" + packingDate +
                ",\n estimateDate=" + estimateDate +
                ",\n storageDate=" + storageDate +
                ",\n moveDateTime=" + moveDateTime +
//                ",\n packingDateTime=" + packingDateTime +
                ",\n estimateDateTime=" + estimateDateTime +
                ",\n isLabor=" + isLabor +
                ",\n loadingAddress='" + loadingAddress + '\'' +
                ",\n unloadingAddress='" + unloadingAddress + '\'' +
                ",\n tariff='" + tariff + '\'' +
                '}';
    }


    public void setMoveDate(String moveDate) {
        if (moveDate != null) {
            this.moveDate = new Timestamp(StringToLongConverter.convertDate(moveDate));
        }
    }

    public void setPackingDate(String packingDate) {
        if (packingDate != null) {
            this.packingDate = new Timestamp(StringToLongConverter.convertDate(packingDate));
        }
    }

    public void setEstimateDate(String estimateDate) {
        if (estimateDate != null) {
            this.estimateDate = new Timestamp(StringToLongConverter.convertDate(estimateDate));
        }
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = new Timestamp(StringToLongConverter.convertDate(storageDate));
    }

//    public void setMoveDateTime(String moveDateTime) {
//        this.moveDateTime = new Timestamp(StringToLongConverter.converTimeFromSelectors(moveDateTime, "start"));
//    }
//
//    public void setPackingDateTime(String packingDateTime) {
//        this.packingDateTime = new Timestamp(StringToLongConverter.converTimeFromSelectors(packingDateTime, "start"));
//    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Timestamp getMoveDate() {
        return moveDate;
    }


    public Timestamp getPackingDate() {
        return packingDate;
    }

    public Timestamp getEstimateDate() {
        return estimateDate;
    }


    public Timestamp getStorageDate() {
        return storageDate;
    }


//    public Timestamp getMoveDateTime() {
//        return moveDateTime;
//    }
//
//
//    public Timestamp getPackingDateTime() {
//        return packingDateTime;
//    }
//
//
//    public Timestamp getEstimateDateTime() {
//        return estimateDateTime;
//    }


    public boolean isLabor() {
        return isLabor;
    }

    public void setLabor(boolean labor) {
        isLabor = labor;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

//    public void setEstimateDateTime(String estimateDateTime) {
//        this.estimateDateTime = new Timestamp(StringToLongConverter.converTimeFromSelectors(estimateDateTime, "start"));
//    }
}
