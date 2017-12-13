package com.alllink.userapp.activity.entity;

public class ActivitySearch {
    Double lng; //经度
    Double lat; //纬度
    Double minLon;
    Double maxLon;
    Double minLat;
    Double maxLat;
    String searchContent; //搜索内容
    Integer activityType; //活动类型(从)
    Double distance; //距离
    Integer sortParams;//0：综合排序   1：距离由近到远   2：费用6从低到高 3：费用从高到低
    public ActivitySearch(){
        this.sortParams = 0;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getSortParams() {
        return sortParams;
    }

    public void setSortParams(Integer sortParams) {
        this.sortParams = sortParams;
    }

    public Double getMinLon() {
        return minLon;
    }

    public void setMinLon(Double minLon) {
        this.minLon = minLon;
    }

    public Double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(Double maxLon) {
        this.maxLon = maxLon;
    }

    public Double getMinLat() {
        return minLat;
    }

    public void setMinLat(Double minLat) {
        this.minLat = minLat;
    }

    public Double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Double maxLat) {
        this.maxLat = maxLat;
    }
}
