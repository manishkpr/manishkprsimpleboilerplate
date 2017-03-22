package com.manishkprboilerplate.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Munish on 1/3/17.
 */

public class DateTime {

    public static boolean isDateGreater(String date_1, String date_2){
        return compare(date_1,date_2,new SimpleDateFormat("yyyy-MM-dd"));
    }

    public static boolean isFirstDateTimeGreater(String date_1, String date_2){
        return compare(date_1,date_2,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    static boolean compare(String date_1, String date_2,SimpleDateFormat sdf){


        try {
            Date date2 = sdf.parse(date_1);
            Date date3 = sdf.parse(date_2);
            if(date3.compareTo(date2) < 0){
                return true;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static String getDateTime() {
        return getDateTimeFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateTimeFormat(String format){
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
    }

    public static String getConvertedDate(String finalDate){

        return getConvertedDate(finalDate,"yyyy-MM-dd","MMM yyyy",Locale.ENGLISH);

    }

    public static String getConvertedDate(String finalDate,String dateFormat,String desireDateFormat,Locale locale){


        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(finalDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new SimpleDateFormat(desireDateFormat, locale).format(date);

    }

}
