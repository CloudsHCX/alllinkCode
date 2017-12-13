package com.alllink.commons.enums;

/**
 * 用户状态枚举
 * @author zhangmanqing
 */
public enum UserState {

    WaitingRegister(0,"待注册"),
    Registered(1,"已注册");

    private  int value ;
    private  String name;

    private UserState(int value, String name) {
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
        for(UserState state : UserState.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }

}
