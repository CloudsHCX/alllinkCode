package com.alllink.userapp.evaluate.entity;

import java.io.Serializable;
import java.util.Date;

public class EvaluateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //评价id
    private int evaluateId;
    //活动id
    private int activityId;
    //订单id
    private int orderId;
    //用户id
    private int userId;
    //评价等级
    private int evaluateLevel;
    //评价内容
    private String evaluateContent;

    //创建时间
    private String createTime;

    /**
     * 设置：评价id
     */
    public void setEvaluateId(int evaluateId) {
        this.evaluateId = evaluateId;
    }

    /**
     * 获取：评价id
     */
    public int getEvaluateId() {
        return evaluateId;
    }

    /**
     * 设置：活动id
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取：活动id
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * 设置：订单id
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取：订单id
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * 设置：用户id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 获取：用户id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 设置：评价等级
     */
    public void setEvaluateLevel(int evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    /**
     * 获取：评价等级
     */
    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    /**
     * 设置：评价内容
     */
    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    /**
     * 获取：评价内容
     */
    public String getEvaluateContent() {
        return evaluateContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
