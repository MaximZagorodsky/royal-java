package com.transport.util_entity;

import com.transport.model.Vehicle;
import lombok.Data;
import java.util.List;

/**
 * Created by Proxima on 14.08.2016.
 */
@Data
public class PriceCategory {
    public PriceCategory(int price,
                         List<Vehicle> vehicleToAM,
                         List<Vehicle> vehicleToPM) {
        this.vehicleToAM = vehicleToAM;
        this.vehicleToPM = vehicleToPM;
        this.priceCategory = price;
    }

    private int priceCategory;
    private List<Vehicle> vehicleToAM;
    private List<Vehicle> vehicleToPM;

    private int avaliableTruckAm;
    private int avaliableVanAm;

    private int avaliableTruckPm;
    private int avaliableVanPm;

    public PriceCategory(int price, int truckAm, int truckPm, int vanAm, int vanPm) {
        this.avaliableTruckAm = truckAm;
        this.avaliableTruckPm = truckPm;
        this.avaliableVanAm = vanAm;
        this.avaliableVanPm = vanPm;
        this.priceCategory = price;
    }
}
