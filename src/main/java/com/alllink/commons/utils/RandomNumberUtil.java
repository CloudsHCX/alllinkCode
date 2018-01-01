package com.alllink.commons.utils;

import java.util.Random;
import java.util.UUID;

public class RandomNumberUtil {
    /*
    *
    * 生成6位不包含零的随机数字验证码
    * */
    public static String CreateVerificationCode(){
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(9) + 1;
            str += number;
        }
        return str;
    }

    /*
    * 生成盐，长度1~10，用于用户密码加密,
    * */
    public static String CreateSalt(){
        String uid = UUID.randomUUID().toString();
        Random random = new Random();
        int number = random.nextInt(10);
        number += 1;
        String salt = uid.substring(number, 2 * number);
        return salt;
    }

    /*
    * 生成只有字母和数字的UUID
    * */
    public static String CreateUUID(){
        String uid = UUID.randomUUID().toString().replaceAll("-","");
        return uid;
    }
}
