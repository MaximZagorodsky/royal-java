package com.transport.service;

import javax.mail.internet.MimeMessage;

/**
 * Created by Proxima on 17.08.2016.
 */
public class EmailReceiverService {
    public void receive(MimeMessage mimeMessage) {
        System.out.println("recevied mail " + mimeMessage);
    }
}
