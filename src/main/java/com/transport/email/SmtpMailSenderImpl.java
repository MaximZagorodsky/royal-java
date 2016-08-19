package com.transport.email;

import com.transport.model.Address;
import com.transport.model.Order;
import com.transport.util.time.StringToLongConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by AK on 7/16/2016.
 */
@Component
public class SmtpMailSenderImpl implements SmtpMailSender {

    @Autowired
    private JavaMailSenderImpl mailSender;

    private SimpleMailMessage templateMessage;

    @Override
    public void sent(Order order) throws MessagingException {
//        String htmlPart = "<div><div>name: fullname<br></div>phone: 312321<br></div>mail: <a href=\"mailto:grinader@gmail.com\">grinader@gmail.com</a><br></div>";
        String htmlPart = "We do not have any hidden fees. You pay just for the moving hours!<br>\n" +
                "\n" +
                "                       \r\rRoyal Moving is Licensed bonded and Insured company.<br>\n" +
                "\n" +
                "                       \n\n\r\rDouble Drive Time states-<br>\n" +
                "           If your move is or over 10 miles distance, there is a DDT the double drive time charge.<br>\n" +
                "           Every licensed company must charge this - Calif. State regulations, " +
                "           the time spent on the road driving from <br> your pick up location to destination<br>\n" +
                "           one way has to be doubled and added ON TOP of minimum.\n" +
                "           For example: if it takes us 20 minutes to drive from your pick up to your drop off,<br>" +
                "           we would double it and <br> make it 40 minutes (same hourly rate apply)\n" +
                "           and add it on top of the minimum.<br>\n" +
                "\n" +
                "           Below are the links to our website, and our Yelp account so you can get a better idea of our company!<br>\n" +
                "\n" +
                "           http://royalmovingco.com/<br>\n" +
                "\n" +
                "           http://www.yelp.com/biz/royal-moving-co-los-angeles-8<br>\n" +
                "\n" +
                "           If you have any questions, please do not hesitate to contact me anytime!<br>\n" +
                "\n" +
                "\n" +
                "\n" +
                "           Sincerely,<br>\n" +
                "\n" +
                "           Admin<br>\n" +
                "           Moving Consultant<br>\n" +
                "           Royal Moving Co.<br>\n" +
                "           (888) 634 9582 <br>\n" +
                "           royalmovingco@gmail.com <br>\n" +
                "           http://www.royalmovingco.com <br> \n" +
                "           Mover Registration: MTR0191476 <br>";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        mimeMessage.setContent(htmlPart, "text/html; charset=utf-8");

        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);

        mailMsg.setFrom("geniusessay@gmail.com");
        mailMsg.setTo(order.getClient().getMail());

        mailMsg.setSubject(order.getClient().getFullName() + ", order number:" + order.getId());
        mailMsg.setText(getRoyal(order), true);
        mailSender.send(mimeMessage);
        System.out.println("---Done---");


/*        mailSender = new JavaMailSenderImpl();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }*/
    }

    private String getDayOfWeek(int dayOfWeek) {
        String day = "";
        switch (dayOfWeek) {

        }
        return day;
    }

    private String getRoyal(Order order) {
        System.out.println("ORDER FROm getRoyal()" + order);
        System.out.println("CLIENT: " + order.getClient());
        StringBuilder addressFrom = new StringBuilder();
        StringBuilder addressTo = new StringBuilder();

        for (Address addr : order.getLoadingAddress()) {
            addressFrom.append(addr.getAddress()).append(", ").append(addr.getZip()).append(";<br>");
        }
        for (Address addr : order.getUnloadingAddress()) {
            addressTo.append(addr.getAddress()).append(", ").append(addr.getZip()).append(";<br>");
        }
        String customerName = "";
        String moveType = "";//меньше 40 = LocalMove
        String estimateValue = "6300 lbs. (900 cf.)";//TODO Доделать estimateValue
        String dayOfWeek = getDayOfWeek(new DateTime(order.getMoveDate()).getDayOfWeek());

        /**
         * created time of order
         */
        String createdTime = StringToLongConverter.getDateAndTimeToString(order.getCreatedTime().getTime());


        String moveDateToString = StringToLongConverter.getDateToString(order.getMoveDate().getTime());
        if (order.getMoveDate() != null) {
            StringToLongConverter.getDateToString(order.getMoveDate().getTime());
        }
        String advertisement = order.getAdvertisement();
        String moveTime = order.getMoveDateTime();
//                order.getMoveTime();
        String dateToString1 = "";
        if (order.getEstimateDate() != null) {
            dateToString1 = StringToLongConverter.getDateToString(order.getEstimateDate().getTime());
        }
        BigDecimal discount = order.getDiscount().setScale(2, RoundingMode.CEILING);
        BigDecimal perHourPrice = order.getPriceForEachHour().setScale(2, RoundingMode.CEILING);
        return "" +
                "<table style=\"font-family:Times;border-collapse:collapse\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "       width=\"700\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td align=\"center\" width=\"100%\"><img class=\"CToWUd\"\n" +
                "                                             src=\"https://ci4.googleusercontent.com/proxy/5VO-7OmShelET7TD9Tv6__fvuNoJH0dpEm1jyla6SIcm8iLb5kkfd4S-ulZCRgRaKbigoTDwtsewhvT9BV5eg6wihx6DJ6G5oPr4Zi4=s0-d-e1-ft#http://eagle.hellomoving.com/ROYALMOVI/IMAGES/logoR.gif\"\n" +
                "                                             border=\"0\" height=\"100\" width=\"100%\"></td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<table style=\"font-family:Times\" align=\"CENTER\" border=\"1\" width=\"700\">" +
                "<tbody>\n" +
                "<tr>\n" +
                "    <td><br>\n" +
                "        <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"750px;\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td colspan=\"2\" width=\"100%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" width=\"20%\">&nbsp;</td>\n" +
                "                            <td align=\"center\" width=\"50%\"><b><font color=\"#000084\" face=\"Verdana\" size=\"4\">Moving\n" +
                "                                Estimate</font></b></td>\n" +
                "                            <td align=\"right\" width=\"30%\"><b><font face=\"Verdana\" size=\"2\">Job No:&nbsp;<font\n" +
                "                                    color=\"#FF0000\">L" + order.getId() + "</font></font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td valign=\"top\" width=\"48%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td width=\"100%\"><font face=\"Arial\"><b>Lions Moving</b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b>4102 Melrose Ave</b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td valign=\"top\" width=\"52%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Customer<br>Rep:</b></font></td>\n" +
                "                            <td width=\"79%\"><font face=\"Arial\" size=\"2\"><b>" + order.getClient().getFullName() + "</b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Phone:</b></font></td>\n" +
                "                            <td width=\"79%\"><font face=\"Arial\" size=\"2\"><b><a href=\"tel:" + order.getClient().getPhone() + "\"\n" +
                "                                                                              value=\"+18886463439\" target=\"_blank\">(888)\n" +
                "                                646-3439</a>&nbsp;</b></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Email:</b></font></td>\n" +
                "                            <td width=\"79%\"><font face=\"Arial\" size=\"2\"><a href=\"mailto:" + order.getClient().getMail() + "\"\n" +
                "                                                                           target=\"_blank\">lionsmovingcompany@gmail.com</a></font>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Web:</b></font></td>\n" +
                "                            <td width=\"79%\"><font face=\"Arial\" size=\"2\"><a\n" +
                "                                    href=\"https://fox.hellomoving.com/lionsmovingcompany.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=https://fox.hellomoving.com/lionsmovingcompany.com&amp;source=gmail&amp;ust=1471547785939000&amp;usg=AFQjCNF1FD0yTysD0vnO-98YznDbYhd8vQ\">lionsmovingcompany.com</a></font>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <br>\n" +
                "        <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"750px;\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=\"48%\"><font color=\"#000080\" face=\"Verdana\"><b>Moving From</b></font></td>\n" +
                "                <td align=\"center\" width=\"52%\"><font color=\"#000080\" face=\"Verdana\"><b>Moving To</b></font></td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td valign=\"top\" width=\"48%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td><b><font face=\"Verdana\" size=\"2\">" + order.getClient().getFullName() + "&nbsp;</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\">" + addressFrom.toString() + "&nbsp;</font></td>\n" +
                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\">, Apt. #: 10</font></td>\n" +
//                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\">Canyon Country, CA&nbsp;91387&nbsp;</font></td>\n" +
//                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\">Phone: <a href=\"tel:" + order.getClient().getPhone() + "\" value=\"+18184247180\"\n" +
                "                                                                        target=\"_blank\">" + order.getClient().getPhone() + "</a></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td valign=\"top\" width=\"52%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td><b><font face=\"Verdana\" size=\"2\">" + order.getClient().getFullName() + "</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\">" + addressTo + "</font></td>\n" +
                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
//                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\">Newhall, CA&nbsp;91321</font></td>\n" +
//                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
//                "                        </tr>\n" +
//                "                        <tr>\n" +
//                "                            <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
//                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td><font face=\"Verdana\" size=\"2\"><a href=\"mailto:" + order.getClient().getMail() + "\" target=\"_blank\">" + order.getClient().getFullName() + "</a></font>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <br>\n" +
                "        <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"750px;\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td valign=\"top\" width=\"48%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"1\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td colspan=\"2\" align=\"CENTER\" width=\"100%\"><b><font color=\"#000080\" face=\"Verdana\">Relocation\n" +
                "                                Details</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Job No:</font></b></td>\n" +
                "                            <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"4\">L" + order.getId() + "</font></b>&nbsp;\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"50%\"><b><font face=\"Arial\" size=\"2\">Estimate Date:</font></b></td>\n" +
                "                            <td width=\"50%\"><b><font face=\"Arial\" size=\"2\">" + dateToString1 + "</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Representative:</font></b></td>\n" +
                "                            <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">" + customerName + "&nbsp;</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><font face=\"Arial\" size=\"2\"><b>Move Type:&nbsp;<font\n" +
                "                                    size=\"1\">Residential</font></b></font></td>\n" +
                "                            <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">" + moveType + " " + order.getDistance() + "+&nbsp;</font></b>&nbsp;\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"48%\"><b><font face=\"Arial\" size=\"2\">Estimated Volume:</font></b></td>\n" +
                "                            <td width=\"52%\"><b><font face=\"Arial\" size=\"2\">" + estimateValue + "&nbsp;<br></font></b>&nbsp;\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Day:</font></b></td>\n" +
                "                            <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"3\">" + dayOfWeek + "</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Date:</font>&nbsp;</b></td>\n" +
                "                            <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"3\">" + moveDateToString + "</font></b>&nbsp;\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Time:</font>&nbsp;</b></td>\n" +
                "                            <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">" + moveTime + "</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Reference By:</font></b></td>\n" +
                "                            <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">" + advertisement + "&nbsp;</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Created on:</font></b></td>\n" +
                "                            <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">" +createdTime + "</font></b>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "                <td valign=\"top\" width=\"52%\">\n" +
                "                    <table style=\"border-collapse:collapse\" border=\"1\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                        <tbody>\n" +
                "                        <tr>\n" +
                "                            <td colspan=\"2\" align=\"center\" width=\"100%\"><font color=\"#000080\" face=\"Verdana\"><b>Relocation\n" +
                "                                Rate</b></font>&nbsp;</td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"275\"><b><font face=\"Arial\" size=\"2\">" + order.getTruck() + " Truck/s,&nbsp;" + order.getMovers() +
                "&nbsp;Workers, $" + perHourPrice + "/hrs\n" +
                "                                3.0&nbsp;hours</font></b></td>\n" +
                "                            <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$" + perHourPrice + "</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>10% off if paid by cash</b></font>&nbsp;</td>\n" +
                "                            <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$" + discount + "</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>Service charge 1 time payment</b></font>&nbsp;\n" +
                "                            </td>\n" +
                "                            <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$30.00</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>DDT-Yes (if the distance 10 miles or\n" +
                "                                more)</b></font>&nbsp;</td>\n" +
                "                            <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$" + order.getDdtPrice() + "</font></b></td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>Total Moving Estimate</b></font></td>\n" +
                "                            <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$" + order.getTotalPrice().setScale(2, RoundingMode.CEILING) + "</font></b></td>\n" +
                "                        </tr>\n" +
                "                        </tbody>\n" +
                "                    </table>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <br>\n" +
                "        <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"750px;\">\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\" width=\"100%\"><font color=\"#000080\" face=\"Verdana\"><b>Understanding Your Estimate</b></font>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td width=\"100%\"><font face=\"Verdana\" size=\"2\">Gate code 094<br>\n" +
                "                    <p><span style=\"background-color:yellow\">Please review this link <a\n" +
                "                            href=\"http://www.cpuc.ca.gov/hhgfaq/default.htm\" target=\"_blank\"\n" +
                "                            data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=http://www.cpuc.ca.gov/hhgfaq/default.htm&amp;source=gmail&amp;ust=1471547785939000&amp;usg=AFQjCNEUa3PGRcThDqgrWD3FShSfbx-dbQ\">http://www.cpuc.ca.gov/hhgfaq/<wbr>default.htm</a> You will have to sign that you read this information on the day of the move. Starting cost includes the first 3 hours with our moving crew. If applicable, any additional labor time needed to complete the move after the first 3 hours will be billed on top of 3 hours minimum.</span>\n" +
                "                    </p>\n" +
                "                    <p>Please note that it is your responsibility to find a parking for the truck(s). You will be\n" +
                "                        responsible for parking tickets.</p>\n" +
                "                    <p>If you would like to keep any of the packing materials such as small/medium/large cardboard\n" +
                "                        boxes, blankets, wardrobe boxes, here is the prices :.</p>\n" +
                "                    <p>-Heavy duty moving blanket $20 (it is free during the move, however you can purchase at this\n" +
                "                        price)</p>\n" +
                "                    <p>-Plastic Box $30 (it is free during the move, however you can purchase at this price)</p>\n" +
                "                    <p>-Small heavy duty boxes $3 each (it is free during the move, however you can purchase at this\n" +
                "                        price)</p>\n" +
                "                    <p>-Medium heavy duty boxes $4 each (it is free during the move, however you can purchase at this\n" +
                "                        price)</p>\n" +
                "                    <p>-Large heavy duty boxes $5 each (it is free during the move, however you can purchase at this\n" +
                "                        price)</p>\n" +
                "                    <p>-Wardrobe box $20 each (it is free during the move, however you can purchase at this price)</p>\n" +
                "                    <p>If your move is or over 10 miles distance, there is a DDT the double drive time charge . Every\n" +
                "                        licensed company must charge this - Calif. State regulations, the time spent on the road driving\n" +
                "                        from your pick up location to destination one way has to be doubled and added ON TOP of minimum\n" +
                "                        For example: if it takes us 20 minutes to drive from your pick up to your drop off ,we would\n" +
                "                        double it and make it 40 minutes ( same hourly rate apply ) and add it on top of the\n" +
                "                        minimum.</p>\n" +
                "                    <p></p>\n" +
                "                    <p>Lions Moving Co's liability for lost or damaged items is limited to $0.60 per pound per article.\n" +
                "                        Additional insurance may be purchased at <a href=\"http://movinginsurance.com\" target=\"_blank\"\n" +
                "                                                                    data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=http://movinginsurance.com&amp;source=gmail&amp;ust=1471547785939000&amp;usg=AFQjCNF3k9ZEgbFawEJAy0idsB36TTTrzQ\">movinginsurance.com</a>.\n" +
                "                        Upon the purchase Lions Moving must be notified less than 72 hours prior to the day of the move.\n" +
                "                        Unless all household goods are properly packed by the customer, packing be done by Lions Moving\n" +
                "                        employees and company materials, in order to get coverage.In order to save the client time and\n" +
                "                        money we do not typically take an inventory of items to be moved. If you would like us to,\n" +
                "                        please let us know before hand. Lions Moving shall not be responsible for the damage of any\n" +
                "                        plants. Lions Moving shall not be liable for any loss or damage, unless it is made in writing by\n" +
                "                        mail supported by proof and ownership together with substation of value, and weight. As a\n" +
                "                        condition precedent, all outstanding monies due to the mover must be in full before a claim can\n" +
                "                        be submitted to the company within 9 months after the date the goods are delivered.</p>\n" +
                "                    <div><br></div>\n" +
                "                </font></td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </td>\n" +
                "</tr>\n" +
                "</tbody>";
    }

    String getLionMovingEstimate(Order order) {
        return "<table style=\"font-family:Times;border-collapse:collapse\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "       width=\"700\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td align=\"center\" width=\"100%\"><img class=\"CToWUd\"\n" +
                "                                             src=\"https://ci5.googleusercontent.com/proxy/L_7xXlKWXbeIZM9-J1wGCw_onr3UYqjrApUCB1JsQQBkCXyFGePJ3YgT4HBGDj7ml-2OGqBuxD6BoDkmtlazRNx1YuILZ-8WLgqVDdU=s0-d-e1-ft#http://eagle.hellomoving.com/ROYALMOVI/IMAGES/logoL.gif\"\n" +
                "                                             border=\"0\" height=\"100\" width=\"100%\"></td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<table style=\"font-family:Times\" align=\"CENTER\" border=\"1\" width=\"700\">\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td><br>\n" +
                "            <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   width=\"98%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td colspan=\"2\" width=\"100%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td align=\"center\" width=\"20%\">&nbsp;</td>\n" +
                "                                <td align=\"center\" width=\"50%\"><b><font color=\"#000084\" face=\"Verdana\" size=\"4\">Moving\n" +
                "                                    Estimate</font></b></td>\n" +
                "                                <td align=\"right\" width=\"30%\"><b><font face=\"Verdana\" size=\"2\">Job No:&nbsp;<font\n" +
                "                                        color=\"#FF0000\">L3724636</font></font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td valign=\"top\" width=\"48%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\"><font face=\"Arial\"><b>Lions Moving</b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b>4102 Melrose Ave</b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"100%\"><font face=\"Arial\" size=\"2\"><b></b></font></td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td valign=\"top\" width=\"52%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"3\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Customer<br>Rep:</b></font></td>\n" +
                "                                <td width=\"79%\"><font face=\"Arial\" size=\"2\"><b>Ally</b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Phone:</b></font></td>\n" +
                "                                <td width=\"79%\"><font face=\"Arial\" size=\"2\"><b><a href=\"tel:%28888%29%20646-3439\"\n" +
                "                                                                                  value=\"+18886463439\" target=\"_blank\">(888)\n" +
                "                                    646-3439</a>&nbsp;</b></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Email:</b></font></td>\n" +
                "                                <td width=\"79%\"><font face=\"Arial\" size=\"2\"><a\n" +
                "                                        href=\"mailto:lionsmovingcompany@gmail.com\" target=\"_blank\">lionsmovingcompany@gmail.com</a></font>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"21%\"><font face=\"Arial\" size=\"2\"><b>Web:</b></font></td>\n" +
                "                                <td width=\"79%\"><font face=\"Arial\" size=\"2\"><a\n" +
                "                                        href=\"https://fox.hellomoving.com/lionsmovingcompany.com\" target=\"_blank\"\n" +
                "                                        data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=https://fox.hellomoving.com/lionsmovingcompany.com&amp;source=gmail&amp;ust=1471549818236000&amp;usg=AFQjCNHa1btUP-_9K-9BHZttjFUTN5Yi1w\">lionsmovingcompany.com</a></font>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <br>\n" +
                "            <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   width=\"98%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=\"48%\"><font color=\"#000080\" face=\"Verdana\"><b>Moving From</b></font></td>\n" +
                "                    <td align=\"center\" width=\"52%\"><font color=\"#000080\" face=\"Verdana\"><b>Moving To</b></font></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td valign=\"top\" width=\"48%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td><b><font face=\"Verdana\" size=\"2\">" + order.getLoadingAddress() + "&nbsp;</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">17943 Lost Canyon Rd&nbsp;</font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">, Apt. #: 10</font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">Canyon Country, CA&nbsp;91387&nbsp;</font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">Phone: <a href=\"tel:818-424-7180\" value=\"+18184247180\"\n" +
                "                                                                            target=\"_blank\">818-424-7180</a></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td valign=\"top\" width=\"52%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td><b><font face=\"Verdana\" size=\"2\">Kathy</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">Storage&nbsp;18650 Vía Princessa</font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\">Newhall, CA&nbsp;91321</font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"></font></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td><font face=\"Verdana\" size=\"2\"><a href=\"mailto:azuuca70@icloud.com\" target=\"_blank\">azuuca70@icloud.com</a></font>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <br>\n" +
                "            <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   width=\"98%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td valign=\"top\" width=\"48%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"1\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td colspan=\"2\" align=\"CENTER\" width=\"100%\"><b><font color=\"#000080\" face=\"Verdana\">Relocation\n" +
                "                                    Details</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Job No:</font></b></td>\n" +
                "                                <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"4\">L3724636</font></b>&nbsp;\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"50%\"><b><font face=\"Arial\" size=\"2\">Estimate Date:</font></b></td>\n" +
                "                                <td width=\"50%\"><b><font face=\"Arial\" size=\"2\">08/17/2016</font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Representative:</font></b></td>\n" +
                "                                <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">Ally&nbsp;</font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><font face=\"Arial\" size=\"2\"><b>Move Type:&nbsp;<font size=\"1\">Residential</font></b></font>\n" +
                "                                </td>\n" +
                "                                <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">Local Move, 16 miles&nbsp;</font></b>&nbsp;\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"48%\"><b><font face=\"Arial\" size=\"2\">Estimated Volume:</font></b></td>\n" +
                "                                <td width=\"52%\"><b><font face=\"Arial\" size=\"2\">6300 lbs. (900 cf.)&nbsp;<br></font></b>&nbsp;\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Day:</font></b></td>\n" +
                "                                <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"3\">Sunday</font></b>&nbsp;\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Date:</font>&nbsp;</b></td>\n" +
                "                                <td width=\"58%\"><b><font color=\"#FF0000\" face=\"Arial\" size=\"3\">08/28/2016</font></b>&nbsp;\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Move Time:</font>&nbsp;</b></td>\n" +
                "                                <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">08-09AM</font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Reference By:</font></b></td>\n" +
                "                                <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">YELP&nbsp;</font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"42%\"><b><font face=\"Arial\" size=\"2\">Created on:</font></b></td>\n" +
                "                                <td width=\"58%\"><b><font face=\"Arial\" size=\"2\">08/17/2016</font></b>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                    <td valign=\"top\" width=\"52%\">\n" +
                "                        <table style=\"border-collapse:collapse\" border=\"1\" cellpadding=\"2\" cellspacing=\"1\" width=\"100%\">\n" +
                "                            <tbody>\n" +
                "                            <tr>\n" +
                "                                <td colspan=\"2\" align=\"center\" width=\"100%\"><font color=\"#000080\" face=\"Verdana\"><b>Relocation\n" +
                "                                    Rate</b></font>&nbsp;</td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"275\"><b><font face=\"Arial\" size=\"2\">1 Truck/s,&nbsp;3&nbsp;Workers,\n" +
                "                                    $145.00/hrs 3.0&nbsp;hours</font></b></td>\n" +
                "                                <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$435.00</font></b>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>10% off if paid by cash</b></font>&nbsp;\n" +
                "                                </td>\n" +
                "                                <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$0.01</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>Service charge 1 time payment</b></font>&nbsp;\n" +
                "                                </td>\n" +
                "                                <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$30.00</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>DDT-Yes (if the distance 10 miles or\n" +
                "                                    more)</b></font>&nbsp;</td>\n" +
                "                                <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$0.01</font></b></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td width=\"275\"><font face=\"Arial\" size=\"2\"><b>Total Moving Estimate</b></font></td>\n" +
                "                                <td align=\"RIGHT\" width=\"76\"><b><font face=\"Arial\" size=\"2\">&nbsp;$465.02</font></b>\n" +
                "                                </td>\n" +
                "                            </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "            <br>\n" +
                "            <table style=\"border-collapse:collapse\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                   width=\"98%\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" width=\"100%\"><font color=\"#000080\" face=\"Verdana\"><b>Understanding Your\n" +
                "                        Estimate</b></font></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td width=\"100%\"><font face=\"Verdana\" size=\"2\">Gate code 094<br>\n" +
                "                        <p><span style=\"background-color:yellow\">Please review this link <a\n" +
                "                                href=\"http://www.cpuc.ca.gov/hhgfaq/default.htm\" target=\"_blank\"\n" +
                "                                data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=http://www.cpuc.ca.gov/hhgfaq/default.htm&amp;source=gmail&amp;ust=1471549818237000&amp;usg=AFQjCNFFyEO_TF7i_dDijPzbnVZAve2WXw\">http://www.cpuc.ca.gov/hhgfaq/<wbr>default.htm</a> You will have to sign that you read this information on the day of the move. Starting cost includes the first 3 hours with our moving crew. If applicable, any additional labor time needed to complete the move after the first 3 hours will be billed on top of 3 hours minimum.</span>\n" +
                "                        </p>\n" +
                "                        <p>Please note that it is your responsibility to find a parking for the truck(s). You will be\n" +
                "                            responsible for parking tickets.</p>\n" +
                "                        <p>If you would like to keep any of the packing materials such as small/medium/large cardboard\n" +
                "                            boxes, blankets, wardrobe boxes, here is the prices :.</p>\n" +
                "                        <p>-Heavy duty moving blanket $20 (it is free during the move, however you can purchase at this\n" +
                "                            price)</p>\n" +
                "                        <p>-Plastic Box $30 (it is free during the move, however you can purchase at this price)</p>\n" +
                "                        <p>-Small heavy duty boxes $3 each (it is free during the move, however you can purchase at this\n" +
                "                            price)</p>\n" +
                "                        <p>-Medium heavy duty boxes $4 each (it is free during the move, however you can purchase at\n" +
                "                            this price)</p>\n" +
                "                        <p>-Large heavy duty boxes $5 each (it is free during the move, however you can purchase at this\n" +
                "                            price)</p>\n" +
                "                        <p>-Wardrobe box $20 each (it is free during the move, however you can purchase at this\n" +
                "                            price)</p>\n" +
                "                        <p>If your move is or over 10 miles distance, there is a DDT the double drive time charge .\n" +
                "                            Every licensed company must charge this - Calif. State regulations, the time spent on the\n" +
                "                            road driving from your pick up location to destination one way has to be doubled and added\n" +
                "                            ON TOP of minimum For example: if it takes us 20 minutes to drive from your pick up to your\n" +
                "                            drop off ,we would double it and make it 40 minutes ( same hourly rate apply ) and add it on\n" +
                "                            top of the minimum.</p>\n" +
                "                        <p></p>\n" +
                "                        <p>Lions Moving Co's liability for lost or damaged items is limited to $0.60 per pound per\n" +
                "                            article. Additional insurance may be purchased at <a href=\"http://movinginsurance.com\"\n" +
                "                                                                                 target=\"_blank\"\n" +
                "                                                                                 data-saferedirecturl=\"https://www.google.com/url?hl=ru&amp;q=http://movinginsurance.com&amp;source=gmail&amp;ust=1471549818237000&amp;usg=AFQjCNEZ1DUWE-2mV6Im1OLSLCDJFjHASg\">movinginsurance.com</a>.\n" +
                "                            Upon the purchase Lions Moving must be notified less than 72 hours prior to the day of the\n" +
                "                            move. Unless all household goods are properly packed by the customer, packing be done by\n" +
                "                            Lions Moving employees and company materials, in order to get coverage.In order to save the\n" +
                "                            client time and money we do not typically take an inventory of items to be moved. If you\n" +
                "                            would like us to, please let us know before hand. Lions Moving shall not be responsible for\n" +
                "                            the damage of any plants. Lions Moving shall not be liable for any loss or damage, unless it\n" +
                "                            is made in writing by mail supported by proof and ownership together with substation of\n" +
                "                            value, and weight. As a condition precedent, all outstanding monies due to the mover must be\n" +
                "                            in full before a claim can be submitted to the company within 9 months after the date the\n" +
                "                            goods are delivered.</p>\n" +
                "                        <div><br></div>\n" +
                "                    </font></td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>";
    }
}
