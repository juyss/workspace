package com.icepoint.framework.data.util;

import com.icepoint.framework.core.util.CaseUtils;

/**
 * // TODO: 后面做成可配置的
 *
 * @author Jiawei Zhao
 */
public class SqlUtils {

    private SqlUtils() {

    }

    public static String toColumn(String field) {
        return CaseUtils.toLowerUnderScore(field);
    }

    public static String toEntityField(String column) {
        return CaseUtils.toLowerCamel(column);
    }
}
