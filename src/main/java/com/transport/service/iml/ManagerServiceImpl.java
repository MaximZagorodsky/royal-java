package com.transport.service.iml;

import com.transport.dto.ManagerDTO;
import com.transport.model.Address;
import com.transport.model.Client;
import com.transport.model.Order;
import com.transport.repository.*;
import com.transport.service.ManagerService;
import com.transport.service.VehicleService;
import com.transport.util.time.StringToLongConverter;
import com.transport.util.time.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by AK on 7/19/2016.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    HelperRepository helperRepository;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    ForemenRepository foremenRepository;
    @Autowired
    VehicleService vehicleService;

    @Autowired
    MoverRepository moverRepository;

    int count = 0;

    @Override
    public List<ManagerDTO> getAllManagerDTO() {
        return null;
    }

    @Override
    public List<ManagerDTO> getAllManagerDTOByDate(Long date) {
        List<Order> orders = orderRepository.findByOrderDayBetween(
                TimeUtil.getStartOfTheDay(date), TimeUtil.getEndOfTheDay(date));
        List<ManagerDTO> managerDTOs = new ArrayList<>();
//        for (Order order : orders) {
//            ManagerDTO managerDTO = new ManagerDTO();
//            managerDTO.setOrderNumber(order.getOrderNumber());
//            managerDTO.setDate(StringToLongConverter.getDateToString(order.getOrderDay().getTime()));
//            System.out.println("managerDTO.getDate()" + StringToLongConverter.getDateToString(order.getOrderDay().getTime()));
//            System.out.println("managerDTO.getDate()" + managerDTO.getDate());
//            managerDTO.setOrderDay(managerDTO.getDate() + " " + order.getMoveDateTime());
//            managerDTO.setFullName(order.getClient().getFullName());
//            managerDTO.setPhoneNumber(order.getClient().getPhone());
//            managerDTO.setMail(order.getClient().getMail());
//            managerDTO.setFieldForManagerComments(order.getFieldForManagerComments());
//            List<Address> loadingAddresses = new ArrayList<>(order.getLoadingAddress());
//            managerDTO.setLoadingAddress(loadingAddresses);
//
//            List<Address> unloadingAddresses = new ArrayList<>(order.getLoadingAddress());
//
//            managerDTO.setUnloadingAddress(unloadingAddresses);
//            managerDTOs.add(managerDTO);
//
//            System.out.println("Sent:" + managerDTO);
//        }
        return managerDTOs;
    }

    @Override
    public List<ManagerDTO> getManagerDTOById(Integer id) {
        if (count == 0) {
        }
        Order order = orderRepository.findOne(id);
        System.out.println("Order from getManagerDTOById " + order);
        ManagerDTO managerDTO = new ManagerDTO();
//        managerDTO.setOrderNumber(order.getOrderNumber());
        managerDTO.setOrderNumber(order.getId());
        managerDTO.setOrderDay(StringToLongConverter.getDateToString(order.getOrderDay().getTime()) + " " + order.getMoveDateTime());
        managerDTO.setMovers(order.getMovers());
        managerDTO.setTruck(order.getTruck());
        managerDTO.setDate(StringToLongConverter.getDateToString(order.getOrderDay().getTime()));
        System.out.println("managerDTO.getDate()" + StringToLongConverter.getDateToString(order.getOrderDay().getTime()));
        System.out.println("managerDTO.getDate()" + managerDTO.getDate());

        Client client = order.getClient();
        System.out.println("CLietn!!!!" + client);

        managerDTO.setFullName(client.getFullName());
        managerDTO.setPhoneNumber(client.getPhone());
        managerDTO.setMail(client.getMail());
        managerDTO.setTotalPrice(order.getTotalPricePerFirstHours());
        managerDTO.setDistance(order.getDistance());
        managerDTO.setTotalPrice(order.getTotalPrice().intValue());

        //TODO Сделать отдеальные сущности для пацанов ниже, чисто для отправки на фронт-енд
        managerDTO.setDrivers(driverRepository.findByEnabledTrue());
        managerDTO.setForemans(foremenRepository.findByEnabledTrue());
        managerDTO.setHelpers(helperRepository.findByEnabledTrue());
        managerDTO.setMoverss(moverRepository.findByEnabledTrue());

        if (order.isLabor()) {
            managerDTO.setVehicles(vehicleService.findAvaliableVehicleByType("truck"));
        } else {
            managerDTO.setVehicles(vehicleService.findAvaliableVehicleByType("van"));
        }


        managerDTO.setFieldForManagerComments(order.getFieldForManagerComments());
        List<Address> loadingAddresses = new ArrayList<>(order.getLoadingAddress());
        managerDTO.setLoadingAddress(loadingAddresses);

        List<Address> unloadingAddresses = new ArrayList<>(order.getLoadingAddress());
        managerDTO.setUnloadingAddress(unloadingAddresses);

        return Collections.singletonList(managerDTO);
    }
}
