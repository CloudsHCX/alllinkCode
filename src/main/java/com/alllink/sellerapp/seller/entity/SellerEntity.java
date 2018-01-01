package com.alllink.sellerapp.seller.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
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
    //支付宝账号
    private String alipayNumber;
    //邮箱
    private String email;
    //头像
    private String photo;
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

    public int getState() {
        return state;
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

    public String getPhoto() {
        return photo;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Timestamp getCodeCreatTime() {
        return codeCreatTime;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getSalt() {
        return salt;
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

    public void setState(int state) {
        this.state = state;
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

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCodeCreatTime(Timestamp codeCreatTime) {
        this.codeCreatTime = codeCreatTime;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlipayNumber() {
        return alipayNumber;
    }

    public void setAlipayNumber(String alipayNumber) {
        this.alipayNumber = alipayNumber;
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
                ", alipayNumber='" + alipayNumber + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", accountBalance=" + accountBalance +
                ", createTime=" + createTime +
                ", codeCreatTime=" + codeCreatTime +
                ", verificationCode='" + verificationCode + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
