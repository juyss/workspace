package com.icepoint.framework.code.utils.xmlUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 函数输出
 * @author ck
 */
@Data
@AllArgsConstructor
@Builder
public class OutPutEntity {
    private String abstracted;
    private String title;
    private String type;
    private String name;

}
