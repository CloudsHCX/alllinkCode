package com.alllink.commons.utils;

import java.sql.Timestamp;
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

}
