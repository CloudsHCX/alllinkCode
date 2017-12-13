package com.alllink.commons.enums;

/**
 * 订单评价状态枚举
 * @author zhangmanqing
 */
public enum OrderEvalState {

    OrderEvalDelete(-1,"评价删除"),
    OrderWaitingEval(0,"未评价"),
    OrderEvaled(1,"已评价");

    private  int value ;
    private  String name;

    private OrderEvalState(int value, String name) {
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
        for(OrderEvalState state : OrderEvalState.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }

}
