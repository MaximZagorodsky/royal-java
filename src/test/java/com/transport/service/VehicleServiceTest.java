package com.transport.service;

import com.transport.ServiceApplication;
import com.transport.converter.VehicleConverter;
import com.transport.model.Vehicle;
import com.transport.util.category.CompatyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Proxima on 10.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class VehicleServiceTest {
    protected Logger log = LoggerFactory.getLogger(CompatyTest.class);

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleConverter vehicleConverter;

    @Test
    public void getAllAvaliableVehicle() {
        log.info("Available vehicles:" + vehicleService.findAvaliableVehicles("AM"));
        log.info("Available vehicles:" + vehicleService.findAvaliableVehicles("PM"));
    }

    @Test
    public void testVehicleConverter() {
//        System.out.println(vehicleService.getAvailableVehicleByPeriodByDate(1470873600000L));
        List<Vehicle> vehicles = Arrays.asList(
//                new Vehicle("truck", "REG_NUMBER1", false, "AM", 1471305600000L),
//                new Vehicle("truck", "REG_NUMBER2", true, "PM"),
//                new Vehicle("truck", "REG_NUMBER3", true, "AM"),
//                new Vehicle("fura", "REG_NUMBER4", false, "PM", 1471305600000L),
//                new Vehicle("van", "REG_NUMBER11", true, "PM"),
//                new Vehicle("van", "REG_NUMBER12", true, "PM"),
                new Vehicle("van", "REG_NUMBER21", false, "PM", new Timestamp(1471305600000L)
//                new Vehicle("van", "REG_NUMBER14", true, "PM"),
//                new Vehicle("van", "REG_NUMBER15", false, "PM", new Timestamp(1471305600000L)),
//                new Vehicle("van", "REG_NUMBER16", true, "AM"),
//                new Vehicle("van", "REG_NUMBER17", true, "AM"),
//                new Vehicle("van", "REG_NUMBER18", true, "PM", new Timestamp(1471305600000L)),
//                new Vehicle("van", "REG_NUMBER19", false, "AM", new Timestamp(1471305600000L)),
//                new Vehicle("van", "REG_NUMBER20", false, "AM", new Timestamp(1471305600000L))
        ));
        vehicleService.saveAll(vehicles);
    }

    @Test
    public void getAvaliableVehicleTest() {
        List<Vehicle> am = vehicleService.findAvaliableVehicles("AM");
        List<Vehicle> pm = vehicleService.findAvaliableVehicles("PM");

        System.out.println("Avaliable vans AM: "+vehicleConverter.getAvaliableCarsNumber(am,"van"));
        System.out.println("Avaliable truck AM: "+vehicleConverter.getAvaliableCarsNumber(am,"truck"));
        System.out.println("Avaliable vans PM: "+vehicleConverter.getAvaliableCarsNumber(pm,"van"));
        System.out.println("Avaliable trucksPM: "+vehicleConverter.getAvaliableCarsNumber(pm,"truck"));
    }

    @Test
    public void getVehicleByType(){
        List<Vehicle> truck = vehicleService.findAvaliableVehicleByType("truck");
        System.out.println(truck);
        List<Vehicle> van = vehicleService.findAvaliableVehicleByType("van");
        System.out.println(van);
    }
}
