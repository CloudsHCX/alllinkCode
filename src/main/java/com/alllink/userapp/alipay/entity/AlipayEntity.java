package com.alllink.userapp.alipay.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

public class AlipayEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private int alipayId;
    //合成的订单号：即为商户订单号out_trade_no
    private String orderIdStr;
    //支付宝交易号
    private String tradeNo;
    //需要退款的金额，该金额不能大于订单金额，必填
    private BigDecimal refundAmount;
    //退款的原因说明
    private String refundReason;
    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
    private String outRequestNo;
    //
    private String createTime;
    //付款状态: 0：不存在状态，1：已付款，-1：已退款
    private int alipaySign;
    //付款金额
    private BigDecimal amount;
    //退款时间
    private String refundTime;

    /**
     * 设置：
     */
    public void setAlipayId(int alipayId) {
        this.alipayId = alipayId;
    }

    /**
     * 获取：
     */
    public int getAlipayId() {
        return alipayId;
    }

    /**
     * 设置：合成的订单号：即为商户订单号out_trade_no
     */
    public void setOrderIdStr(String orderIdStr) {
        this.orderIdStr = orderIdStr;
    }

    /**
     * 获取：合成的订单号：即为商户订单号out_trade_no
     */
    public String getOrderIdStr() {
        return orderIdStr;
    }

    /**
     * 设置：支付宝交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取：支付宝交易号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置：需要退款的金额，该金额不能大于订单金额，必填
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取：需要退款的金额，该金额不能大于订单金额，必填
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置：退款的原因说明
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * 获取：退款的原因说明
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置：标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    /**
     * 获取：标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    public String getOutRequestNo() {
        return outRequestNo;
    }

    /**
     * 设置：
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置：付款状态: 0：不存在状态，1：已付款，-1：已退款
     */
    public void setAlipaySign(Integer alipaySign) {
        this.alipaySign = alipaySign;
    }

    /**
     * 获取：付款状态: 0：不存在状态，1：已付款，-1：已退款
     */
    public Integer getAlipaySign() {
        return alipaySign;
    }

    /**
     * 设置：付款金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取：付款金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置：退款时间
     */
    public void setRefundTime(String refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 获取：退款时间
     */
    public String getRefundTime() {
        return refundTime;
    }
}
