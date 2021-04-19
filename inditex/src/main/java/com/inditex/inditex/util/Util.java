package com.inditex.inditex.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static Date convertFromLocalToDateAddingMonths(int numberOfMonths){
        ZoneId zone = ZoneId.of( "Europe/Madrid" );
        LocalDate today = LocalDate.now( zone ).plusMonths(numberOfMonths);
        Date date = Date.from(today.atStartOfDay(zone).toInstant());

        return date;
    }

    public static String healtCheck(){
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        File file = new File("/");
        Long freeSpace = file.getFreeSpace();

        return "{\"freeMemory\""+":"+"\""+freeMemory+"\"" +"," + "\"freeSpace:\""+ "\""+ freeSpace + "\"" +"}";
    }

    public static Date convertStringtoDate(String dateInString) {
        //2020-06-14-00.00.00
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date convertToDate(String receivedDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        Date date = null;
        try {
            date = formatter.parse(receivedDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }


    public static boolean calculateDateSameDay(Date queryDate, Date initDate, Date endDate){
        //It wont work for time zone, we could pass the timezone/ No funciona para varias zonas horarias
        //Para esa caso, usar LocalDate y llevar el date a instant
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(queryDate);
        cal1.setTime(initDate);
        cal2.setTime(endDate);

        boolean sameDayAndValidRangeHour =
                        cal.get(Calendar.DAY_OF_YEAR) ==  cal1.get(Calendar.DAY_OF_YEAR) &&
                        cal.get(Calendar.DAY_OF_YEAR) ==  cal2.get(Calendar.DAY_OF_YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                        cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        //Validate Hour
                        cal.get(Calendar.HOUR_OF_DAY) >= cal1.get(Calendar.HOUR_OF_DAY) &&
                        cal.get(Calendar.HOUR_OF_DAY) <= cal2.get(Calendar.HOUR_OF_DAY);


        return sameDayAndValidRangeHour;
    }
}
