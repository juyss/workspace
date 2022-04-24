package com.icepoint.framework.code.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用于 添加函数->来自工程 请求的数据接收对象
 * @author Juyss
 * @version 1.0
 * @since 2021-06-11 10:51
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionDTO {

    /**
     * 工程id
     */
    private Long projId;

    /**
     * 模块id
     */
    private Long moduleId;

    /**
     * 表id
     */
    private Long tableId;

    /**
     * 表服务id
     */
    private Long tabServiceId;

    /**
     * 分组id
     */
    private Long groupId;

    /**
     * 函数名（不可重复）
     */
    private String nameEn;

    /**
     * 函数中文名
     */
    private String name;

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
    private String httpModel;

    /**
     * 服务命名空间
     */
    private String serviceNs;

}
