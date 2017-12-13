package com.alllink.commons.enums;

/**
 * 活动类型枚举
 * @author zhangmanqing
 */
public enum ActivityType {

    English(0,"英语"),
    Music(1,"音乐"),
    Art(2,"美术"),
    VocTechnology(3,"职业技术");

    private  int value ;
    private  String name;

    private ActivityType(int value, String name) {
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
        for(ActivityType state : ActivityType.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }

}
