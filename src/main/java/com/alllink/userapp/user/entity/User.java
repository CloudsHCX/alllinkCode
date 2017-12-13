package com.alllink.userapp.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;



import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author zhangmanqing
 * @email
 * @date
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户id
    private int userId;
    //用户名
    private String userName;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String photo;
    //年龄
    private int age;
    //性别
    private int gender;
    //手机号
    private String phoneNumber;
    //qq
    private String qqNumber;
    //微信
    private String wechatNumber;
    //邮箱
    private String email;
    //总积分
    private int totalPoints;
    //余额
    private BigDecimal accountBalance;
    //创建时间
    private String createTime;
    //验证码
    private int identifyingCode;
    //验证码生成时间
    private String codeCreatTime;
    //修改时间
    private String modifiedTime;
    //注册状态
    private int state;

    /**
     * 设置：用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * 获取：用户id
     */
    public Integer getUserId() {
        return userId;
    }
    /**
     * 设置：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取：用户名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    /**
     * 获取：昵称
     */
    public String getNickname() {
        return nickname;
    }
    /**
     * 设置：头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    /**
     * 获取：头像
     */
    public String getPhoto() {
        return photo;
    }
    /**
     * 设置：年龄
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * 获取：年龄
     */
    public Integer getAge() {
        return age;
    }
    /**
     * 设置：性别
     */
    public void setGender(int gender) {
        this.gender = gender;
    }
    /**
     * 获取：性别
     */
    public int getGender() {
        return gender;
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
    /**
     * 设置：qq
     */
    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }
    /**
     * 获取：qq
     */
    public String getQqNumber() {
        return qqNumber;
    }
    /**
     * 设置：微信
     */
    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }
    /**
     * 获取：微信
     */
    public String getWechatNumber() {
        return wechatNumber;
    }
    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置：总积分
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    /**
     * 获取：总积分
     */
    public int getTotalPoints() {
        return totalPoints;
    }
    /**
     * 设置：余额
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
    /**
     * 获取：余额
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：创建时间
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * 设置：验证码
     */
    public void setIdentifyingCode(int identifyingCode) {
        this.identifyingCode = identifyingCode;
    }
    /**
     * 获取：验证码
     */
    public Integer getIdentifyingCode() {
        return identifyingCode;
    }
    /**
     * 设置：验证码生成时间
     */
    public void setCodeCreatTime(String codeCreatTime) {
        this.codeCreatTime = codeCreatTime;
    }
    /**
     * 获取：验证码生成时间
     */
    public String getCodeCreatTime() {
        return codeCreatTime;
    }
    /**
     * 设置：修改时间
     */
    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    /**
     * 获取：修改时间
     */
    public String getModifiedTime() {
        return modifiedTime;
    }
    /**
     * 设置：注册状态
     */
    public void setState(int state) {
        this.state = state;
    }
    /**
     * 获取：注册状态
     */
    public int getState() {
        return state;
    }
}
