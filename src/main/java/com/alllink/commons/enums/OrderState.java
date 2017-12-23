package com.alllink.commons.enums;

/**
 * 订单状态枚举
 * @author zhangmanqing
 */
public enum OrderState {

    OrderCancel(-1,"订单取消"),
    OrderNotPay(0,"订单未支付"),
    OrderPayed(1,"订单已支付"),
    OrderRefundCheck(2,"订单退款审核中"),
    OrderRefund(3,"订单已退款"),
    OrderRefundNotPass(4,"订单退款未通过");

    private  int value ;
    private  String name;

    private OrderState(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByValue(int value){
        String retName = "";
        for(OrderState state : OrderState.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }

}
