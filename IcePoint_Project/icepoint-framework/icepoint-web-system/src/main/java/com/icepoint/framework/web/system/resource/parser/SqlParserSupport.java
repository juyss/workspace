package com.icepoint.framework.web.system.resource.parser;

import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
public abstract class SqlParserSupport {

    protected String quotes(String str) {
        Assert.notNull(str, "str不能为空");
        str = str.trim();

        String prefix = "";
        String suffix = "";
        if (!str.startsWith("'")) {
            prefix = "'";
        }
        if (!str.endsWith("'")) {
            suffix = "'";
        }

        return prefix + str.trim() + suffix;
    }

    protected String withAlias(String alias, String str) {
        return alias + "." + str;
    }
}
