package com.alllink.commons.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /*
    * 生成当前时间
    * */
    public static Timestamp getCurrentTime() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        Timestamp currentTime = Timestamp.valueOf(date);
        /*String date = dateFormat.format(new Date());
        Date currentTime = dateFormat.parse(date);*/
        return currentTime;
    }

    public static String timestampToString(Timestamp time){
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            str = sdf.format(time);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String DateToString(Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    public static Date StringToDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

//    public static Date TimestampToDate(Timestamp time){
//        Date date = new Date();
//        try {
//            date = time;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return date;
//    }

}
