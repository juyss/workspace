package com.icepoint.framework.code.utils.xmlUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 函数输入
 * @author ck
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputEntity {
    /**
     * 详细描述
     */
    private String abstracted;

    /**
     * 条件名称
     */
    private String name;

    /**
     * 描述
     */
    private String title;

    /**
     *参数类型
     */
    private String type;
}
