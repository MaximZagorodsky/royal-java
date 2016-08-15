package com.transport.model;

import com.transport.form.OrderForm;
import com.transport.form.PaymentDetailsForm;
import lombok.Data;

/**
 * Created by Proxima on 29.07.2016.
 */
@Data
public class OrderHandler {
    private OrderForm orderForm;
    private PaymentDetailsForm paymentDetailsForm;


}
