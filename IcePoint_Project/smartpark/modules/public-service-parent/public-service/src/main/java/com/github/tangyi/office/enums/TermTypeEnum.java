package com.github.tangyi.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum TermTypeEnum {
        PHONE(0,"手机APP"),
        APPLET(1,"小程序"),
        WEB(2,"web端"),
        WECAT(3,"微信小程序");

        private Integer value;
        private String name;

    /**
     * 根据value 返回CoverTypeEnum
     */
    public static TermTypeEnum matchByValue(Integer value){
        for (TermTypeEnum termTypeEnum : TermTypeEnum.values()) {
            if(termTypeEnum.value==value){
                return termTypeEnum;
            }
        }
        return WEB;
    }

    /**
     * 根据name返回CoverTypeEnum
     *
     */
    public static TermTypeEnum matchByName(String name){
        for (TermTypeEnum termTypeEnum : TermTypeEnum.values()) {
            if(termTypeEnum.name.equals(name)){
                return termTypeEnum;
            }
        }
        return WEB;
    }
}
