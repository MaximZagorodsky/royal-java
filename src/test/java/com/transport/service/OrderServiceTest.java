package com.transport.service;

import com.transport.ServiceApplication;
import com.transport.email.SmtpMailSender;
import com.transport.enums.StatusEnum;
import com.transport.model.Order;
import com.transport.util.time.StringToLongConverter;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;

/**
 * Created by Proxima on 13.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    SmtpMailSender mailSender;

    @Test
    public void findCountByStatus() {
        Integer status = orderService.findCountByStatus(StatusEnum.BOOKED);
        Integer status2 = orderService.findCountByStatus(StatusEnum.COMPLETED);
        System.out.println("StatusCOMPLETED:" + status2);
        System.out.println("StatusBOOKED:" + status);
    }

    @Test
    public void getOrderById() throws MessagingException {
        Order one = orderService.findOne(73);
        String dateToString = StringToLongConverter.getDateToString(one.getEstimateDate().getTime());
        String dateToString2 = StringToLongConverter.getDateAndTimeToString(one.getCreatedTime().getTime());

        DateTime dateTime = new DateTime(one.getMoveDate().getTime());
        System.out.println(dateTime.getDayOfWeek());
        System.out.println(dateToString);
        System.out.println(dateToString2);
        System.out.println(one);
//        System.out.println(one.getMoveDate());
//        System.out.println(one.getPackageDate());
//        System.out.println(one.getEstimateDate());


//        mailSender.sent(one);
    }

    @Test
    public void totalCount() {
        System.out.println(orderService.getTotalCount());
    }


}
