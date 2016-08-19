package com.transport.email;

import com.transport.ServiceApplication;
import com.transport.model.Client;
import com.transport.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;

/**
 * Created by AK on 7/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class SimpleOrderManagerTest {
    @Autowired
    private com.transport.email.SmtpMailSender smtpMailSender;

    Order order;

    @Before
    public void initOrder() {
        Client client = new Client();
        client.setMail("textmesweet@gmail.com");
        client.setFullName("<div dir=\"ltr\"><div><div>name: fullname<br></div>phone: 312321<br></div>mail: <a href=\"mailto:grinader@gmail.com\">grinader@gmail.com</a><br></div>");
        order = new Order();
        order.setOrderNumber(777);
        order.setClient(client);
    }
//    @Ignore
    @Test
    public void sendMail() throws MessagingException {
        smtpMailSender.sent(order);
    }
}
