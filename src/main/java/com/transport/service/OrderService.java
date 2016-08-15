package com.transport.service;

import com.transport.enums.StatusEnum;
import com.transport.form.OrderForm;
import com.transport.form.PaymentDetailsForm;
import com.transport.model.Order;
import com.transport.util_entity.CountByStatus;

/**
 * Created by 1 on 6/7/2016.
 */
public interface OrderService {

    Order addOrder(OrderForm orderForm, PaymentDetailsForm paymentDetailsForm);

    Order addOrder(OrderForm order);

    Order findOne(Integer id);

    Integer considerPriceCategory(String date);

    Integer findCountByStatus(StatusEnum status );

    Integer getTotalCount();

    CountByStatus getTotalCountEntity();

}
