package com.github.tangyi.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 页面跳转类型枚举
 */
@Getter
@AllArgsConstructor
public enum JumpTypeEnum {
    PAGE(0, "页面"),
    APP(1, "app"),
    WECAT(2, "微信小程序"),
    PLATE(3, "板块");

    private Integer value;
    private String name;

    /**
     * 根据value JumpTypeEnum
     */
    public static JumpTypeEnum matchByValue(Integer value){
        for (JumpTypeEnum jumpTypeEnum : JumpTypeEnum.values()) {
            if(jumpTypeEnum.value==value){
                return jumpTypeEnum;
            }
        }
        return PLATE;
    }

    /**
     * 根据name返回JumpTypeEnum
     *
     */
    public static JumpTypeEnum matchByName(String name){
        for (JumpTypeEnum jumpTypeEnum : JumpTypeEnum.values()) {
            if(jumpTypeEnum.name.equals(name)){
                return jumpTypeEnum;
            }
        }
        return PLATE;
    }

}
