package com.icepoint.framework.web.system.resource.source;

/**
 * 物理表sql解析异常
 *
 * @author Jiawei Zhao
 */
public class SpecificTableSqlParseException extends RuntimeException {

    public SpecificTableSqlParseException() {
    }

    public SpecificTableSqlParseException(String message) {
        super(message);
    }

    public SpecificTableSqlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecificTableSqlParseException(Throwable cause) {
        super(cause);
    }

}
