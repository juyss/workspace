package com.icepoint.base.web.resource.component.query;

import lombok.Data;

/**
 * match的选项
 *
 * TODO: 目前没有使用这里的选项，作为将来的扩展项目
 *
 * @author Jiawei Zhao
 */
@Data
public class MatchOption {

    private boolean ignoreNullValue = true;

    private boolean ignoreEmptyString = true;

    private boolean fieldToCamelCase = false;

    private boolean fieldToUnderScore = false;
}
