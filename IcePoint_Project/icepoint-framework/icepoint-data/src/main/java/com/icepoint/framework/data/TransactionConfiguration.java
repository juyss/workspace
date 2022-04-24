package com.icepoint.framework.data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 事务配置
 *
 * @author Jiawei Zhao
 */
@Configuration(proxyBeanMethods = false)
public class TransactionConfiguration implements InitializingBean {

    /**
     * 读取的方法开头
     * TODO: 目前先写死，到时候加到配置文件中，支持热更新
     */
    private static final String[] READ_METHODS = new String[]{
            "find", "get", "query", "select", "list", "page", "tree", "count", "exists"
    };

    /**
     * 写入的方法开头
     * TODO: 目前先写死，到时候加到配置文件中，支持热更新
     */
    private static final String[] WRITE_METHODS = new String[]{
            "save", "add", "create", "new", "insert", "upload",
            "update", "set", "change",
            "delete", "remove"
    };

    private final String[] readMethods = READ_METHODS;

    private final String[] writeMethods = WRITE_METHODS;

    @Override
    public void afterPropertiesSet() {

        if (readMethods.length > 0 && writeMethods.length > 0) {

            for (String readMethod : readMethods) {
                if (Arrays.asList(writeMethods).contains(readMethod)) {
                    throw new IllegalStateException("写方法与读方法的方法名重复: " + readMethod);
                }
            }
        }
    }

    public String[] getReadMethods() {
        return readMethods;
    }

    public String[] getWriteMethods() {
        return writeMethods;
    }
}
