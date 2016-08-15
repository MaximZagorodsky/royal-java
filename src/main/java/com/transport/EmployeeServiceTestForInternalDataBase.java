package com.transport;

import com.transport.model.*;
import com.transport.repository.DriverRepository;
import com.transport.repository.ForemenRepository;
import com.transport.repository.HelperRepository;
import com.transport.repository.MoverRepository;
import com.transport.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Proxima on 11.08.2016.
 */


@Component
public class EmployeeServiceTestForInternalDataBase {

    @Autowired
    HelperRepository helperRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    ForemenRepository foremenRepository;

    @Autowired
    MoverRepository moverRepository;

    @Autowired
    VehicleService vehicleService;


    public void createHelpers() {
        List<Helper> helpers = new ArrayList<>();
        boolean enabled = true;
        for (int i = 0; i < 10; i++) {

            if (i % 2 == 0) {
                enabled = false;
            } else {
                enabled = true;
            }
            System.out.println("i= " + i + "," + enabled);
            helpers.add(new Helper("Helper" + i, "Last" + i, "Helper" + i + "@gmail.com", "+13239441709", enabled));
        }

//        helperRepository.save(helpers);
    }


    public void createDrivers() {
        List<Driver> helpers = new ArrayList<>();
        boolean enabled = true;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                enabled = false;
            } else {
                enabled = true;
            }
            helpers.add(new Driver("Driver" + i, "Last" + i, "Driver" + i + "@gmail.com", "+13239441709", enabled));
        }

        driverRepository.save(helpers);
    }


    public void createForemens() {
        List<Foreman> helpers = new ArrayList<>();
        boolean enabled = true;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                enabled = false;
            } else {
                enabled = true;
            }
            helpers.add(new Foreman("Foreman" + i, "Last" + i, "Foreman" + i + "@gmail.com", "+13239441709", enabled));
        }

        foremenRepository.save(helpers);
    }


    public void createMovers() {
        List<Mover> movers = new ArrayList<>();
        boolean enabled = true;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                enabled = false;
            } else {
                enabled = true;
            }
            movers.add(new Mover("Mover" + i, "Last" + i, "Mover" + i + "@gmail.com", "+13239441709", enabled));
        }

        moverRepository.save(movers);
    }

    public void invokeAllMethods() {
        createDrivers();
        createForemens();
        createHelpers();
        createMovers();
        createVehicles();
    }

    public void createVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                vehicles.add(new Vehicle("van", "REG_NUMBER" + i, false, "PM", new Timestamp(1471305600000L)));
            } else {
                if (i < 10) {
                    vehicles.add(new Vehicle("truck", "REG_NUMBER" + i, true, "PM"));
                } else {
                    vehicles.add(new Vehicle("van", "REG_NUMBER" + i, true, "AM"));
                }

            }

        }
        vehicleService.saveAll(vehicles);
    }


    public void findAllEnabledTrue() {
        System.out.println(driverRepository.findByEnabledTrue());
        System.out.println(helperRepository.findByEnabledTrue());
        System.out.println(foremenRepository.findByEnabledTrue());
    }

}
