package com.transport.service;

import com.transport.ServiceApplication;
import com.transport.enums.StatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Proxima on 13.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    public void findCountByStatus() {
        Integer status = orderService.findCountByStatus(StatusEnum.BOOKED);
        Integer status2 = orderService.findCountByStatus(StatusEnum.COMPLETED);
        System.out.println("StatusCOMPLETED:" + status2);
        System.out.println("StatusBOOKED:" + status);
    }

    @Test
    public void totalCount() {
        System.out.println(orderService.getTotalCount());
    }


}
