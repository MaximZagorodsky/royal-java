package com.transport.service.iml;


import com.transport.enums.StatusEnum;
import com.transport.form.OrderForm;
import com.transport.form.PaymentDetailsForm;
import com.transport.model.Address;
import com.transport.model.Client;
import com.transport.model.Event;
import com.transport.model.Order;
import com.transport.repository.AddressRepository;
import com.transport.repository.ClientRepository;
import com.transport.repository.EventRepository;
import com.transport.repository.OrderRepository;
import com.transport.service.OrderService;
import com.transport.util.category.CategoryUtil;
import com.transport.util.time.StringToLongConverter;
import com.transport.util_entity.CountByStatus;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 1 on 6/10/2016.
 */

@Service
public class OrderServiceImpl implements OrderService {

    private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    EventRepository eventRepository;

    @Override
    @Transactional
    public Order addOrder(OrderForm orderForm, PaymentDetailsForm detailsForm) {

        Client client = clientRepository.findByMail(orderForm.getMail());
        if (client == null) {
            Client newClient = new Client();
            newClient.setFullName(orderForm.getFullName());
            newClient.setMail(orderForm.getMail());
            newClient.setPhone(orderForm.getPhoneNumber());
            client = clientRepository.save(newClient);
            log.info("-->>> new Client: " + newClient);
        }


        Order order = new Order();

        order.setDiscount(detailsForm.getDiscount());
        //complete sets

        order.setCompany(orderForm.getCompany());
        order.setAdvertisement(orderForm.getAdvertisement());
        order.setSizeOfMove(orderForm.getSizeOfMove());
        order.setLabor(orderForm.isLabor());
        order.setPhoneNumber(order.getPhoneNumber());
        order.setDiscount(order.getDiscount());

        methods(orderForm, order);
        order.setOrderDay(System.currentTimeMillis());
        order.setClient(client);
        //<editor-fold desc="hide">
        order.setEstimateDate(orderForm.getEstimateDate());
        order.setPackageDate(orderForm.getPackingDate());
        order.setPickUpDate(orderForm.getMoveDate());
        order.setDistance(orderForm.getDistance());

        //order.setStored(orderForm.isStored());
        // order.setDurationStorage(orderForm.getDurationStorage());
        //order.setBoxDeliveredDate(orderForm.getBoxDeliveredDate());
        //        order.setBoxQuantity(detailsForm.getBoxQuantity());
        //        order.setTotalPricePerFirstHours(orderForm.getTotalPricePerFirstHours());
//        order.setRateOrFlat(orderForm.getRateOrFlat());
//        order.setAddExtra(orderForm.getAddExtra());
        //        order.setFollowUpDate(orderForm.getFollowUpDate());

        order.setTruck(detailsForm.getTruck());
        order.setMovers(detailsForm.getMovers());
        order.setHeavyItemPrice(detailsForm.getHeavyItemPrice());
        order.setServiceCharge(detailsForm.getServiceCharge());
        order.setShrink(detailsForm.getShrink());
        order.setTape(detailsForm.getTape());
        order.setStatus(detailsForm.getStatus());

        order.setDdt(detailsForm.getDdt());


        order.setTotalPricePerFirstHours(detailsForm.getRatePerHour());


        order.setFieldForManagerComments(detailsForm.getFieldForManagerComments());
        order.setFieldForSalesmanComments(detailsForm.getFieldForSalesmanComments());
        //</editor-fold>

        long orderDay = System.currentTimeMillis();
        order.setOrderDay(orderDay);
        Order save = orderRepository.save(order);
        createEvent(
                save,
                orderRepository.findByOrderDay(orderDay).getId()
        );


        return save;

    }

    private void methods(OrderForm orderForm, Order order) {
        order.setLoadingAddress(orderForm.getLoadingAddress());
        order.setUnloadingAddress(orderForm.getUnloadingAddress());
        log.info("LoadingAddress:" + order.getLoadingAddress());
        log.info("LoadingAddress From orderForm:" + orderForm.getLoadingAddress());
        log.info("UnLoadingAddress:" + order.getUnloadingAddress());
        log.info("UnLoadingAddress From orderForm:" + orderForm.getUnloadingAddress());
    }

    @Override
    public Order addOrder(OrderForm orderForm) {
        Address addressFrom = new Address();

//        addressFrom.setAddress(orderForm.getLoadingAddress());
        // addressFrom.setFloor(orderForm.getFloor());
        addressFrom = addressRepository.save(addressFrom);
        Address addressTo = new Address();
//        addressTo.setAddress(orderForm.getLoadingAddress());
        //addressTo.setFloor(orderForm.getFloor());
        addressTo = addressRepository.save(addressTo);

        Set<Address> loadingAddress = new HashSet<>();
        Set<Address> unloadingAddress = new HashSet<>();

        loadingAddress.add(addressFrom);
        unloadingAddress.add(addressTo);

        Client client = clientRepository.findByMail(orderForm.getMail());
        if (client == null) {
            Client newClient = new Client();
            newClient.setFullName(orderForm.getFullName());
            newClient.setMail(orderForm.getMail());
            newClient.setPhone(orderForm.getPhoneNumber());
            client = clientRepository.save(newClient);
        }


        Order order = new Order();

//        order.setDiscount(detailsForm.getDiscount());
        //complete sets

        order.setCompany(orderForm.getCompany());
        order.setFullName(orderForm.getFullName());
        order.setAdvertisement(orderForm.getAdvertisement());

        order.setSizeOfMove(orderForm.getSizeOfMove());
        order.setLabor(orderForm.isLabor());

        order.setLoadingAddress(loadingAddress);
        order.setLoadingAddress(unloadingAddress);

        order.setPackageDate(orderForm.getPackingDate());
        order.setMoveDate(orderForm.getMoveDate());
        order.setEstimateDate(orderForm.getEstimateDate());
        order.setMail(orderForm.getMail());
        order.setPhoneNumber(orderForm.getPhoneNumber());


        long orderDay = System.currentTimeMillis();
        order.setOrderDay(orderDay);
        order.setClient(client);


        Order save = orderRepository.save(order);
        createEvent(
                save,
                orderRepository.findByOrderDay(orderDay).getId()
        );

        return save;
    }


    @Override
    public Order findOne(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Integer considerPriceCategory(String date) {
        return CategoryUtil.getRoyalMovingPriceCategoryByDay(new DateTime(StringToLongConverter.convertDate(date)));
    }

    @Override
    public Integer findCountByStatus(StatusEnum status) {
        return orderRepository.countByStatus(status.getStatus());
    }

    @Override
    public Integer getTotalCount() {
        return orderRepository.countBy();
    }

    @Override
    public CountByStatus getTotalCountEntity() {
        CountByStatus countByStatus = new CountByStatus();
        countByStatus.setBooked(findCountByStatus(StatusEnum.BOOKED));
        countByStatus.setInProgress(findCountByStatus(StatusEnum.IN_PROGRESS));
        countByStatus.setCompleted(findCountByStatus(StatusEnum.COMPLETED));
        countByStatus.setSold(findCountByStatus(StatusEnum.SOLD));
        countByStatus.setTotalCount(countByStatus.getBooked() + countByStatus.getCompleted() +
                countByStatus.getInProgress() + countByStatus.getSold());
        return countByStatus;
    }


    private void createEvent(Order order, int orderId) {
        Event event = new Event();
        event.setStart(order.getPackageDate());
        event.setTitle(order.getClient().getFullName());
        event.setOrderId(orderId);
        event.setOrderStatus(order.getStatus());
        System.out.println("EVENT_STATUS:" + event.getOrderStatus());
        eventRepository.save(event);
    }
}
