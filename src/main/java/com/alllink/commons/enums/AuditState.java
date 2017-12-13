package com.alllink.commons.enums;

/**
 * 认证（审核）状态枚举
 * @author zhangmanqing
 */
public enum  AuditState {

    AuditNotPass(-1,"审核不通过"),
    AuditWaitCheck(0,"待审核"),
    AuditPass(1,"审核通过");

    private  int value ;
    private  String name;

    private AuditState(int value, String name) {
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
        for(AuditState state : AuditState.values()){
            if(state.value ==(value)){
                retName = state.name;
            }
        }

        return retName;
    }


}
