package com.inditex.inditex.util;

import java.io.File;
import java.text.DateFormat;
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

}
