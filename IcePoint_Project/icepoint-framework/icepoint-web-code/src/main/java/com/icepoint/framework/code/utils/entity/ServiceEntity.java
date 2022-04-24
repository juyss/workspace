package com.icepoint.framework.code.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 服务实体
 *
 * @author ck
 * @version 1.0
 * @date 2021/5/21 17:36
 */
@Data
@AllArgsConstructor
@Builder
public class ServiceEntity {
    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 请求地址
     */
    private String ipAddress;
    /**
     * 包名
     */
    private String pageName;

}
