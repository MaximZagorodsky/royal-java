package com.transport.util.time;

import com.transport.enums.StartOrEndPeriodOfTimeEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Proxima on 29.07.2016.
 */
public class StringToLongConverter {

    public static Long convertDate(String originalDate) {
        if (originalDate==null){
            return 0L;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(originalDate);
            System.out.println(date);
            System.out.println("convertDate: " + date.getTime());
            System.out.println("convertDate" + new Date(date.getTime()));
            System.out.println(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDateToString(Long dateTo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateTo);
        String format = "";
        try {
            format = formatter.format(date);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     *
     * @param dateTo date to format
     * @return date in string in format 2016-08-22 09:22
     */
    public static String getDateAndTimeToString(Long dateTo) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date(dateTo);
        String format = "";
        try {
            format = formatter.format(date);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     *
     * @return date in string in format 2016-08-22
     */
    public static String getDateToString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String format = "";
        try {
            format = formatter.format(date);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format;
    }

    public static Long convertTime(String originalTime) {
        System.out.println("TimeTOConvert:" + originalTime);
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        try {
            date = formatter.parse(originalTime);
            System.out.println(date);
            System.out.println("convertDate: " + date.getTime());
            System.out.println("convertDate" + new Date(date.getTime()));
            System.out.println(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long converTimeFromSelectors(String time, String startOrEnd) {
        String toConvert = startOrEnd.equals("start") ? time.substring(0, 5) : time.substring(6, 11);

        String formatToParse = time.substring(12, 16).equals("a.m.") ? "Am" : "Pm";

        SimpleDateFormat formatterDateAndTime = new SimpleDateFormat("hh:mm a");
        Long returnValue = 0L;
        try {
            System.out.println(formatterDateAndTime.parse(toConvert + " " + formatToParse));
            returnValue = formatterDateAndTime.parse(toConvert + " " + formatToParse).getTime();

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
