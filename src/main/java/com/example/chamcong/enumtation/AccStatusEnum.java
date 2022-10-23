package com.example.chamcong.enumtation;




public enum AccStatusEnum {
    CREATED("CREATED",0),
    ACTIVATED("ACTIVATED",1),
    BANNED("BANNED",2);

    private String name;
    private Integer value;
    AccStatusEnum(String name, Integer value){
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
