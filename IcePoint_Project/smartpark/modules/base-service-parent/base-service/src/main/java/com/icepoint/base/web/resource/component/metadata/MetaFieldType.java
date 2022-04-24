package com.icepoint.base.web.resource.component.metadata;

import java.sql.JDBCType;
import java.util.Arrays;

/**
 * 元数据表字段数据类型
 *
 * @author Jiawei Zhao
 */
public enum MetaFieldType {

    BYTE(1, new JDBCType[]{JDBCType.BIT}),
    SHORT(2, new JDBCType[]{JDBCType.SMALLINT}),
    INTEGER(3, new JDBCType[]{JDBCType.INTEGER, JDBCType.TINYINT}),
    LONG(4, new JDBCType[]{JDBCType.BIGINT}),
    FLOAT(5, new JDBCType[]{JDBCType.FLOAT}),
    DOUBLE(6, new JDBCType[]{JDBCType.DECIMAL}),
    BOOLEAN(7, new JDBCType[]{JDBCType.TINYINT}),
    STRING(8, new JDBCType[]{JDBCType.CHAR, JDBCType.VARCHAR, JDBCType.LONGVARCHAR}),
    DATE(9, new JDBCType[]{JDBCType.DATE}),
    DATETIME(10, new JDBCType[]{JDBCType.TIMESTAMP});

    /**
     * 对应字典中的值
     */
    private final int code;

    /**
     * 对应的JDBC类型
     */
    private final JDBCType[] jdbcTypes;

    MetaFieldType(int code, JDBCType[] jdbcTypes) {
        this.code = code;
        this.jdbcTypes = jdbcTypes;
    }

    public static MetaFieldType valueOf(int code) {
        return Arrays.stream(values())
                .filter(type -> type.code == code)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("未知数据类型值: " + code));
    }

    public int getCode() {
        return code;
    }

    public JDBCType[] getJdbcTypes() {
        return jdbcTypes;
    }
}
