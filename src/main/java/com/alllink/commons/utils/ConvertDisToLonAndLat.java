package com.alllink.commons.utils;

import java.util.HashMap;
import java.util.Map;

public class ConvertDisToLonAndLat {
    private static final double EARTH_RADIUS = 6371;// 赤道半径(单位m)

    /**
     * 292      * 转化为弧度(rad)
     * 293      *
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static Map<String, Double> getLonAndLat(Double longitude, Double latitude, Double raidus) {
        Map<String, Double> map = new HashMap<>();
        Double degree = (24901 * 1609) / 360.0;// 赤道周长24901英里 1609是转换成米的系数
        double raidusMile = raidus;//半径

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;//最小维度
        Double maxLat = latitude + radiusLat;//最大纬度

        Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;//最小经度
        Double maxLng = longitude + radiusLng;//最大经度
        map.put("minLon", minLng);
        map.put("maxLon", maxLng);
        map.put("minLat", minLat);
        map.put("maxLat", maxLat);
        return map;
    }

    public static double getDistance(double lon1, double lat1, double lon2,
                                     double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000) / 1000.0;
        return s;
    }


}
