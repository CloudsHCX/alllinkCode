package com.alllink.commons.utils;

import java.util.Date;
import java.util.Random;

public class CheckCodeUtil {

    public static String generateCheckCode(){
        String checkCode ="";
        final Integer NUM=6;
        Random r = new Random();
        for(int i=0;i<NUM;i++){
            checkCode = checkCode+r.nextInt(10);
        }
        return checkCode;
    }
}
