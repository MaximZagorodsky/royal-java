package com.transport.service;

import com.transport.ServiceApplication;
import com.transport.model.Driver;
import com.transport.model.Foreman;
import com.transport.model.Helper;
import com.transport.model.Mover;
import com.transport.repository.DriverRepository;
import com.transport.repository.ForemenRepository;
import com.transport.repository.HelperRepository;
import com.transport.repository.MoverRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Proxima on 11.08.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class EmployeeServiceTest {

    @Autowired
    HelperRepository helperRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    ForemenRepository foremenRepository;

    @Autowired
    MoverRepository moverRepository;


    @Test
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

    @Test
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

    @Test
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

    @Test
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


    @Test
    public void findAllEnabledTrue() {
        System.out.println(driverRepository.findByEnabledTrue());
        System.out.println(helperRepository.findByEnabledTrue());
        System.out.println(foremenRepository.findByEnabledTrue());
    }

}
