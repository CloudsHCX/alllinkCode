package com.alllink.sellerapp.seller.entity;

import com.alllink.commons.utils.TimeUtil;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class SellerActivityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //活动id
    private int activityId;
    //商家id
    private int sellerId;
    //经度
    private double longitude;
    //纬度
    private double latitude;
    //活动名称
    private String activityName;
    //总人数
    private int totalNumber;
    //费用
    private double cost;
    //活动照片  放文件夹
    private String activityPhoto;
    //活动类型
    private int activityType;
    //活动开始时间
    private String beginTime;
    //活动结束时间
    private String endTime;
    //已报名人数
    private int enrollNumber;
    //活动信息
    private String activityInfo;
    //活动状态
    private int activityState;
    //活动创建时间
    private Date createTime;
    //活动修改时间
    private String modifiedTime;
    //活动审核时间
    private Date auditTime;
    //活动地址
    private String address;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getActivityId() {
        return activityId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getActivityName() {
        return activityName;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public double getCost() {
        return cost;
    }

    public String getActivityPhoto() {
        return activityPhoto;
    }

    public int getActivityType() {
        return activityType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getEnrollNumber() {
        return enrollNumber;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public int getActivityState() {
        return activityState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public String getAddress() {
        return address;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setActivityPhoto(String activityPhoto) {
        this.activityPhoto = activityPhoto;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setEnrollNumber(int enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public void setActivityState(int activityState) {
        this.activityState = activityState;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "activityId=" + activityId +
                ", sellerId=" + sellerId +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", activityName='" + activityName + '\'' +
                ", totalNumber=" + totalNumber +
                ", cost=" + cost +
                ", activityPhoto='" + activityPhoto + '\'' +
                ", activityType=" + activityType +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", enrollNumber=" + enrollNumber +
                ", activityInfo='" + activityInfo + '\'' +
                ", activityState=" + activityState +
                ", createTime=" + createTime +
                ", modifiedTime=" + modifiedTime +
                ", auditTime=" + auditTime +
                ", address='" + address + '\'' +
                '}';
    }
}
