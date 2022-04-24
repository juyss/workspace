package com.github.tangyi.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 展示类型枚举
 */
@AllArgsConstructor
@Getter
public enum DisplayTypeEnum {
    TAB(0,"tab"),
    TILED(1,"平铺");

    private Integer value;
    private String name;

    /**
     * 根据value DisplayTypeEnum
     */
    public static DisplayTypeEnum matchByValue(Integer value){
        for (DisplayTypeEnum displayTypeEnum : DisplayTypeEnum.values()) {
            if(displayTypeEnum.value==value){
                return displayTypeEnum;
            }
        }
        return TAB;
    }

    /**
     * 根据name返回 DisplayTypeEnum
     *
     */
    public static DisplayTypeEnum matchByName(String name){
        for (DisplayTypeEnum displayTypeEnum : DisplayTypeEnum.values()) {
            if(displayTypeEnum.name.equals(name)){
                return displayTypeEnum;
            }
        }
        return TAB;
    }

}
