package com.transport.controller;


import com.transport.EmployeeServiceTestForInternalDataBase;
import com.transport.converter.OrderFormConverter;
import com.transport.converter.PaymentsDetailsConverter;
import com.transport.converter.VehicleConverter;
import com.transport.email.SmtpMailSender;
import com.transport.model.GoogleDistance;
import com.transport.model.OrderHandler;
import com.transport.model.Vehicle;
import com.transport.service.GoogleMapService;
import com.transport.service.ManagerService;
import com.transport.service.OrderService;
import com.transport.service.VehicleService;
import com.transport.service.iml.CalendarEventServiceImpl;
import com.transport.util_entity.CountByStatus;
import com.transport.util_entity.PriceCategory;
import com.transport.util_entity.SearchDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 1 on 6/9/2016.
 */
@RestController
@RequestMapping("api")
public class OrderController extends BaseController {
    @Autowired
    private SmtpMailSender smtpMailSender;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderFormConverter orderFormConverter;
    @Autowired
    private PaymentsDetailsConverter paymentsDetailsConverter;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private CalendarEventServiceImpl eventService;

    @Autowired
    VehicleConverter vehicleConverter;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    private GoogleMapService googleMapService;

    int val = 0;

    @Autowired
    EmployeeServiceTestForInternalDataBase emp;

    //    @CrossOrigin()
    @RequestMapping(value = "/order", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> saveOrder(@RequestBody OrderHandler order) throws AuthenticationException {
        System.out.println(order);
        orderService.addOrder(order.getOrderForm(), order.getPaymentDetailsForm());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @RequestMapping(value = "/order/categoryPrice{date}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceCategory> getOrder(@RequestParam String date) throws AuthenticationException {
        if (val == 0) {
            emp.invokeAllMethods();
            val++;
        }

        Integer priceCategory = this.orderService.considerPriceCategory(date);
        List<Vehicle> vehicleToAM = vehicleService.findAvaliableVehicles("AM");
        List<Vehicle> vehicleToPM = vehicleService.findAvaliableVehicles("PM");
        return new ResponseEntity<>(new PriceCategory(priceCategory,
                vehicleConverter.getAvaliableCarsNumber(vehicleToAM, "truck"),
                vehicleConverter.getAvaliableCarsNumber(vehicleToAM, "van"),
                vehicleConverter.getAvaliableCarsNumber(vehicleToPM, "truck"),
                vehicleConverter.getAvaliableCarsNumber(vehicleToPM, "van")), HttpStatus.OK);
    }


    @RequestMapping(value = "/distance", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GoogleDistance>> getDistance(@RequestBody SearchDistance searchDistance) {
        System.out.println(searchDistance);

        return new ResponseEntity<>(
                googleMapService.getDistance(
                        searchDistance.getLoadingAddress(),
                        searchDistance.getUnloadingAddress()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/orderCount", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountByStatus> getOrderCountByStatus() {
        return new ResponseEntity<>(orderService.getTotalCountEntity(), HttpStatus.OK);
    }
}



