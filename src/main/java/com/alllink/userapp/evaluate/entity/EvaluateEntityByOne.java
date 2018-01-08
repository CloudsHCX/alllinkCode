package com.alllink.userapp.evaluate.entity;

public class EvaluateEntityByOne {
    //评价内容，活动名称，活动id,评价时间

    //活动id
    private int activityId;
    //活动名称
    private String activityName;

    //用户Id
    private int userId;
    //手机号
    private String phoneNumber;

    //评价等级
    private int evaluateLevel;
    //评价内容
    private String evaluateContent;

    //评价时间
    private String createTime;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(int evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 设置：手机号
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取：手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
