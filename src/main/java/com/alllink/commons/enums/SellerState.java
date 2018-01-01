package com.alllink.commons.enums;

public enum SellerState {

    WaitingRegister(0, "待注册"),
    Registered(1, "已注册"),
    commit(2, "商家提交法人信息"),
    verified(3, "法人信息审核通过");

    private int value;
    private String name;

    SellerState(int value, String name) {
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
        for (SellerState state : SellerState.values()) {
            if (state.value == (value)) {
                retName = state.name;
            }
        }

        return retName;
    }
}
