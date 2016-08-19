/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.transport;

/**
 * @author Oleg Zhurakousky
 */
public class GmailInboundPop3AdapterTestApp {
/*
    private static Logger logger = Logger.getLogger(GmailInboundPop3AdapterTestApp.class);

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/integration/gmail-pop3-config.xml");
        DirectChannel inputChannel = ac.getBean("receiveChannel", DirectChannel.class);
//        final MailReceiver receiver = new Pop3MailReceiver("pop3://textmesweet%40gmail.com:Rr123456@localhost/INBOX");


        inputChannel.subscribe(new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                logger.info("Message: " + message.getHeaders().get("mail_subject"));

                System.out.println(">>>>>>>>>>MEssage" + message.toString());
                System.out.println(">>>>>>>>>> TimeStamp" + message.getHeaders().getTimestamp());
                System.out.println(">>>>>>>>>>Mail_ID" + message.getHeaders().getId());
                System.out.println(">>>>>>>>>>Payload" + message.getPayload().toString());

            }
        });
    }*/
}
