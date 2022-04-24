package com.icepoint.framework.web.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * HTTP请求方式类型枚举
 *
 * @author Juyss
 * @version 1.0
 * @ClassName HttpQueryType
 * @since 2021-06-17 11:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum HttpQueryTypeEnum {

    GET("GET", 1),
    POST("POST", 2),
    PUT("PUT", 3),
    DELETE("DELETE", 4),
    PATCH("PATCH", 5);


    private String name;
    private Integer code;

    /**
     * 根据名称获取编码
     *
     * @param name
     * @return Integer
     * @author Juyss
     */
    public static Integer getCodeByName(String name) {
        String upperCase = name.toUpperCase();
        return HttpQueryTypeEnum.valueOf(upperCase).getCode();
    }

    /**
     * 根据编码获取名称
     *
     * @param code
     * @return String
     * @author Juyss
     */
    public static String getNameByCode(Integer code) {
        HttpQueryTypeEnum[] values = HttpQueryTypeEnum.values();
        List<HttpQueryTypeEnum> enumList = Arrays.asList(values);
        HttpQueryTypeEnum target = enumList.stream()
                .filter(entity -> entity.getCode().equals(code))
                .findAny()
                .orElse(null);
        Assert.notNull(target, "名称不匹配");
        return target.getName();
    }
}
