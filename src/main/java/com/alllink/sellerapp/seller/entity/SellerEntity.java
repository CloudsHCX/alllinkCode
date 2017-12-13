package com.alllink.sellerapp.seller.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-11-05 15:57:37
 */
public class SellerEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //商家id
    private int sellerId;
    //商家用户名
    private String sellerName;
    //商家名称
    private String nickname;
    //密码
    private String password;
    //注册状态
    private int state;
    //地址
    private String address;

    //手机号
    private String phoneNumber;
    //QQ
    private String qqNumber;
    //微信
    private String wechatNumber;
    //邮箱
    private String email;
    //头像
    private Blob avatar;
    //余额
    private double accountBalance;
    //创建时间
    private Date createTime;

    //验证码创建时间
    private Timestamp codeCreatTime;
    //验证码
    private String verificationCode;
    //加密盐
    private String salt;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCodeCreatTime(Timestamp codeCreatTime) {
        this.codeCreatTime = codeCreatTime;
    }

    public Timestamp getCodeCreatTime() {
        return codeCreatTime;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public String getEmail() {
        return email;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "SellerEntity{" +
                "sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", email='" + email + '\'' +
                ", avatar=" + avatar +
                ", accountBalance=" + accountBalance +
                ", createTime=" + createTime +
                ", codeCreatTime=" + codeCreatTime +
                ", verificationCode='" + verificationCode + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
