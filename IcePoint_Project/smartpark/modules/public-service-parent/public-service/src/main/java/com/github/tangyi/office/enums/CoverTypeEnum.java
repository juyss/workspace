package com.github.tangyi.office.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 封面类型枚举
 */
@Getter
@AllArgsConstructor
enum CoverTypeEnum {
    PICTURE(0, "图片"),
    VIDEO(1, "视频"),
    VR(2, "VR");


    private Integer value;
    private String name;

    /**
     * 根据 value 返回CoverTypeEnum
     */
    public static CoverTypeEnum matchByValue(Integer value){
        for (CoverTypeEnum coverTypeEnum : CoverTypeEnum.values()) {
            if(coverTypeEnum.value==value){
                return coverTypeEnum;
            }
        }
        return PICTURE;
    }

    /**
     * 根据 name 返回CoverTypeEnum
     *
     */
    public static CoverTypeEnum matchByName(String name){
        for (CoverTypeEnum coverTypeEnum : CoverTypeEnum.values()) {
            if(coverTypeEnum.name.equals(name)){
                return coverTypeEnum;
            }
        }
        return PICTURE;
    }


}
