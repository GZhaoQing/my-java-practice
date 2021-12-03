package com.ufo.enums;

public enum ExampleOfEnum {
    item1("001","a"), item2("002","b");

    //enum是可以有属性的
    String code;
    String name;
    ExampleOfEnum(String code,String name){
        this.code=code;
        this.name=name;
    }
}
