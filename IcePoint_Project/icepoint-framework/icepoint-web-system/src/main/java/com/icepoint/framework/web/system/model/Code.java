package com.icepoint.framework.web.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/25 9:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Code  {
    /**
     * 请求路径
     */
    private String uri;
    /**
     * 实体名
     */
    private String entityName;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 字段
     */
    private List<Attr> attrs;
    /**
     * 表名
     */
    private String dataTable;

}
