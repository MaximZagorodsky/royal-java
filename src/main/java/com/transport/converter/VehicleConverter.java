package com.transport.converter;

import com.transport.dto.VehicleDTO;
import com.transport.model.Vehicle;

import java.util.List;

/**
 * Created by Maksim Zagorodskiy on 7/15/2016.
 */
public interface VehicleConverter {
    VehicleDTO convertVehicleToVehicleDTO(Vehicle vehicle, Long data);

    public int getAvaliableCarsNumber(List<Vehicle> vehicleList, String type);


}
