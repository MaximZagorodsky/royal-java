package com.transport.email;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.transport.EmailReceiver;
import com.transport.ServiceApplication;
import com.transport.model.Client;
import com.transport.model.Order;
import com.transport.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by AK on 7/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class SimpleOrderManagerTest {
    @Autowired
    private com.transport.email.SmtpMailSender smtpMailSender;

    @Autowired
    private OrderService orderService;

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
        Order one = orderService.findOne(73);
        smtpMailSender.sent(one);
    }

    @Test
    public void getAllEmails() throws IOException {
        String protocol = "imap";
        String host = "imap.gmail.com";
        String port = "993";


        String userName = "textmesweet@gmail.com";
        String password = "Rr123456";

        EmailReceiver receiver = new EmailReceiver();
        receiver.downloadEmails(protocol, host, port, userName, password);
    }

    @Test
    public void testGoogleGmailApi(){
//        Gmail gmail = new Gmail();
    }

    public static void sendMessage(Gmail service, String userId, MimeMessage email)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(email);
        message = service.users().messages().send(userId, message).execute();

        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
    }

    /**
     * Create a Message from an email
     *
     * @param email Email to be set to raw of message
     * @return Message containing base64 encoded email.
     * @throws IOException
     * @throws MessagingException
     */
    public static Message createMessageWithEmail(MimeMessage email)
            throws MessagingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        email.writeTo(baos);
        String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

}
