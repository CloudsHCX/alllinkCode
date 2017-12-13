package com.alllink.commons.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 0表示电脑
 * 1表示安卓
 */
public class CheckDevice {
    public static  int getDevice(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if(userAgent.indexOf("Android") != -1){
            //安卓
            return 1;

        }else{
            //电脑
            return 0;
        }
    }
}
