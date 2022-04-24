package com.icepoint.framework.code.utils.xmlUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ck
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEntity {
    /**
     * 函数流
     */
    private List<ProcessEntity> processes;
    /**
     *函数组描述
     */
    private String abstracts;
    /**
     * 组名
     */
    private String name;
    /**
     * 描述
     */
    private String title;
}
