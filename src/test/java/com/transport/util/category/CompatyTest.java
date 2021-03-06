package com.transport.util.category;

import com.transport.ServiceApplication;
import com.transport.converter.VehicleConverter;
import com.transport.enums.Company;
import com.transport.enums.ParserPatterEnum;
import com.transport.enums.StartOrEndPeriodOfTimeEnum;
import com.transport.model.Address;
import com.transport.model.GoogleDistance;
import com.transport.service.GoogleMapService;
import com.transport.service.VehicleService;
import com.transport.util.time.StringToLongConverter;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Proxima on 26.07.2016.
 */
//@RunWith(BlockJUnit4ClassRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServiceApplication.class)
@WebAppConfiguration
public class CompatyTest {
    protected Logger log = LoggerFactory.getLogger(CompatyTest.class);

    @Autowired
    GoogleMapService googleMapService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleConverter vehicleConverter;

    @Test
    public void getAllCompanies() {
        List<Company> actualListCompany = Arrays.asList(Company.values()).subList(0, Company.values().length - 1);
        log.info("Company list:" + actualListCompany);
    }

    @Test
    public void dataConverter() {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String dateInString = "12:23";

        try {

            Date date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println("dataConverter: " + date.getTime());
            Date date1 = new Date(date.getTime());
            System.out.println("dataConverter" + date1);
            System.out.println(formatter.format(date));
            System.out.println(formatter.format(date1));
            System.out.println("newDate: " + new Date(1468281600000L));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void timeConverter() {
        DateTime dateStart = new DateTime(
                converTimeAndDateFromSelectors("08:30-09:30 a.m.", "2016-08-06", StartOrEndPeriodOfTimeEnum.START));

        DateTime dateENd = new DateTime(
                converTimeAndDateFromSelectors("08:30-09:30 a.m.", "2016-08-06", StartOrEndPeriodOfTimeEnum.END));
        log.info("TimeAfterConvertSTART:" + dateStart);
        log.info("TimeAfterConvertEND:" + dateENd);
        log.info("Hour:" + dateStart.getHourOfDay());
        log.info("Minutes:" + dateStart.getMinuteOfHour());
        log.info("TimeAfterConvert:" + dateStart.getMillis());
        log.info("TimeStamp:" + new Timestamp(dateStart.getMillis()));
        log.info("TimeStamp:" + new Timestamp(dateENd.getMillis()));


//        VehicleDTO vehicleDTO = vehicleConverter.convertVehicleToVehicleDTO(
//                new Vehicle(16, "truck", "SA2231SA", true, "AM",
//                        new Timestamp(dateStart.getMillis())), dateStart.getMillis());
//        log.info("VehicalDTO" + vehicleDTO);


        System.out.println(CategoryUtil.getRoyalMovingPriceCategoryByDay(dateStart));
        System.out.println(StringToLongConverter.getDateToString(1470240671276L));
    }

    @Test
    public void testBigDecimal() {
        System.out.println(new BigDecimal("33.22").toString());
    }

    @Test
    public void testDateToString() {
        System.out.println(StringToLongConverter.getDateToString());
    }


    @Test
    public void googleApiTest() throws Exception {

        List<GoogleDistance> distance =
                googleMapService.getDistance(
                        Arrays.asList(
                                new Address("Kiev,Hmelnicka 10", "03115"),
                                new Address("Kiev,Hmelnicka 10", "03115")
                        ),
                        Arrays.asList(
                                new Address("Novokuzneck, klimenko 56", ""),
                                new Address("Odessa, bazarna 10", "65000"))
                );

        System.out.println(distance);

    }


    @Test
    public void checkBigDecimal() {


//        DateTime dateTime = new DateTime(aLong);
//        System.out.println(dateTime.getDayOfWeek());

    }


    @Test
    public void checkTime() throws ParseException {
//        ZonedDateTime departure
        SimpleDateFormat parserSDF = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
        Date date = parserSDF.parse("Wed Oct 16 00:00:00 CEST 2013");
        SimpleDateFormat formatterDateAndTime = new SimpleDateFormat(ParserPatterEnum.EEE_MMM_dd_HH_mm_ss_Z_yyyy.getParsFormat(), Locale.ENGLISH);
        System.out.println("First Date" + StringToLongConverter.
                getDateAndTimeToString(formatterDateAndTime.parse("Fri Aug 19 02:13:52 EEST 2016").getTime()));
        System.out.println(formatterDateAndTime.parse("Fri Aug 19 02:13:52 EEST 2016").getTime());
        System.out.println("date: " + date.toString());
    }

    /**
     * @param time - время, пришедшее с календаря
     * @return время в long
     * @throws ParseException
     */
    public static Long converTimeFromSelectors(String time) {
        time = "03:00-04:00 p.m.";
        time = "09:00-10:00 a.m";
        String startTime = time.substring(0, 5);
        System.out.println(startTime);
        String endTime = time.substring(6, 11);
        System.out.println(endTime);
        String formatToParse = time.substring(12, 16).equals("a.m.") ? "Am" : "Pm";

        SimpleDateFormat formatterDateAndTime = new SimpleDateFormat("hh:mm a");
        Long returnValue = 0L;
        try {
            System.out.println(formatterDateAndTime.parse(startTime + " " + formatToParse));
            returnValue = formatterDateAndTime.parse(startTime + " " + formatToParse).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static Long converTimeAndDateFromSelectors(String time, String date, StartOrEndPeriodOfTimeEnum period) {
        String startTime = time.substring(0, 5);
        System.out.println(startTime);
        String endTime = time.substring(6, 11);
        System.out.println(endTime);
        String formatToParse = time.substring(12, 16).equals("a.m.") ? "Am" : "Pm";
        Long returnValue = 0L;

        SimpleDateFormat formatterDateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        try {
            String startOrEndPeriod = period.equals(StartOrEndPeriodOfTimeEnum.START) ? startTime : endTime;
            System.out.println(formatterDateAndTime.parse(date + " " + startTime + " " + formatToParse));
            returnValue = formatterDateAndTime.parse(date + " " + startOrEndPeriod + " " + formatToParse).getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnValue;
    }


}
