package com.alllink.sellerapp.seller.entity;

public class SellerOrderEntity {

    private int sellerOrderId;
    private int sellerId;
    private int admin_id;
    private int orderState;
    private double cost;
    private String createTime;
    private String payTime;
    private int paymentChannel;

    public int getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(int sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(int paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    @Override
    public String toString() {
        return "SellerOrderEntity{" +
                "sellerOrderId=" + sellerOrderId +
                ", sellerId=" + sellerId +
                ", admin_id=" + admin_id +
                ", orderState=" + orderState +
                ", cost=" + cost +
                ", createTime='" + createTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", paymentChannel=" + paymentChannel +
                '}';
    }
}
