package com.alllink.commons.utils;

import java.util.Random;

public class VerificationCodeUtil {
    /*
*
* 生成6位随机数字验证码
* */
    public static String CreateCheckcode(){
        Random random = new Random();
        double number = (1 + random.nextDouble()) * Math.pow(10, 6);
        String length = String.valueOf(number);
        return length.substring(1, 7);
    }
}
