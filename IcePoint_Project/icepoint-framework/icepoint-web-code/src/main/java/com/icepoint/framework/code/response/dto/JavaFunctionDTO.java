package com.icepoint.framework.code.response.dto;

import com.icepoint.framework.code.xml.entity.Input;
import com.icepoint.framework.code.xml.entity.Output;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @since 2021-06-11 11:07
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaFunctionDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 函数类型
     */
    private Integer funType;

    /**
     * 函数名（不可重复）
     */
    private String nameEn;

    /**
     * 函数中文名
     */
    private String name;

    /**
     * 函数全类名
     */
    private String className;

    /**
     * 函数描述
     */
    private String description;

    /**
     * 服务地址
     */
    private String url;

    /**
     * 网络请求类型
     */
    private Integer httpModel;

    /**
     * 服务命名空间
     */
    private String serviceNs;

    /**
     * 输入参数
     */
    private List<Input> inputs;

    /**
     * 输出参数
     */
    private List<Output> outputs;


}
