package com.alllink.commons.enums;

/**
 * 支付渠道枚举
 *
 * @author zhangmanqing
 */
public enum PaymentChannel {
    noWay(0, "无"),
    alipay(1, "支付宝"),
    weixin(2, "微信");

    private int value;
    private String name;

    PaymentChannel(int value, String name) {
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

    public static String getNameByValue(int value) {
        String retName = "";
        for (PaymentChannel state : PaymentChannel.values()) {
            if (state.value == (value)) {
                retName = state.name;
            }
        }

        return retName;
    }


}
