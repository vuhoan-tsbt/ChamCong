package com.example.chamcong.enumtation;

public enum LoginTypeEnum {
    ACTIVE("ACTIVE",0),
    DELETED("DELETED",1);
    private  String name;
    private Integer value;
    LoginTypeEnum(String name, Integer value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public Integer getValue(){
        return this.value;
    }
}
