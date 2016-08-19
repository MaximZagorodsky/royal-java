package com.transport.dto;

import com.transport.model.*;
import lombok.Data;

import java.util.List;

/**
 * Created by AK on 7/18/2016.
 */
@Data
public class ManagerDTO {
    private String date;
    private Integer orderNumber;
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private String mail;
    private String sizeOfMove; //enums
    private String storageSize;
    private boolean isLabor;
    private Integer totalPrice;
    private String distance;
    private Integer truck; //enums
    private Integer movers; //enums
    private String orderDay;
    private String heavyItem; //enums
    private String fieldForManagerComments;
    private List<Address> loadingAddress;
    private List<Address> unloadingAddress;

    private List<Foreman> foremans;
    private List<Driver> drivers;
    private List<Helper> helpers;
    private List<Mover> moverss;
    private List<Vehicle> vehicles;
}
