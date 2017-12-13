package com.alllink.commons.exception;

import java.util.Map;

/**
 * 用于map返回的信息
 * @author: 张漫青  20171122 16:21
 *
 */
public class MapMessage {

    /**
     *  返回map信息
     * @param map
     * @param result
     * @param message
     * @param exception
     * @return
     */
   public static Map<String, Object> returnMapMessage(Map<String, Object> map,String result ,String message,String exception){
        map.put("result",result);
        map.put("message",message);
        map.put("exception",exception);
       return map;
   }
}
