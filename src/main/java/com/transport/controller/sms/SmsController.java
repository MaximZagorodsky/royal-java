package com.transport.controller.sms;

import com.transport.repository.ClientRepository;
import com.transport.service.ClientService;
import com.transport.service.PersonService;
import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by maksim on 8/20/2016.
 */
@Controller
@Log4j
public class SmsController {
    @Autowired
    private ClientService clientService;

    //Method handle incoming requests for example from Twilio
    @RequestMapping(value = "/sms")
    public void handleSmsRequest(HttpServletRequest request) throws IOException {
        //This a content of sms
        //TODO There is can be an issue when auth will be turn on. If it will be don't covered by Spring Security, it can be a window for SQL injection or some request for hack.
        String smsBody = request.getParameter("Body");
        String senderNumber = request.getParameter("From");

        //In incoming sms expects the Number type, therefor we have to check body for reject any other types
        try {
            Integer.valueOf(smsBody.trim());
            log.info("Incoming message from " + clientService.getClientNameByPhone(senderNumber) + " phone: " + senderNumber);
        } catch (NumberFormatException e) {
            log.error("Message text has invalid type");
        }
    }
}


