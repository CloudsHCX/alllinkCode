package com.alllink.commons.enums;

/**
 * 活动状态枚举
 * @author zhangmanqing
 */
public enum  ActivityState {

    ActivityNotPass(-1,"活动审核不通过"),
    ActivityWaitingCheck(0,"活动待审核"),
    ActivityChecked(1,"活动审核通过"), //发布状态
    ActivityCancel(2,"活动取消");


    private  int value ;
    private  String name;

    private ActivityState(int value, String name) {
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
        for(ActivityState state : ActivityState.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }

}
