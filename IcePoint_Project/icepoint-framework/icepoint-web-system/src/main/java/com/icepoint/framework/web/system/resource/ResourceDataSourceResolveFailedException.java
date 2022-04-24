package com.icepoint.framework.web.system.resource;

import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * @author Jiawei Zhao
 */
public class ResourceDataSourceResolveFailedException extends RuntimeException {

    private final String requiredType;

    private final String [] availableTypes;

    public ResourceDataSourceResolveFailedException(String requiredType, @Nullable String[] availableTypes) {
        this(parseMessage(requiredType, availableTypes), requiredType, availableTypes);
    }

    public ResourceDataSourceResolveFailedException(String message, String requiredType, String[] availableTypes) {
        super(message);
        this.requiredType = requiredType;
        this.availableTypes = availableTypes;
    }

    public ResourceDataSourceResolveFailedException(String message, Throwable cause, String requiredType,
            String[] availableTypes) {
        super(message, cause);
        this.requiredType = requiredType;
        this.availableTypes = availableTypes;
    }

    private static String parseMessage(String requiredType, @Nullable String[] availableTypes) {
        String messageTemplate = "数据访问源解析异常, 无法获取对应的类型的数据访问源对象, 要获取的类型: %s";
        String message;
        if (availableTypes != null) {
            messageTemplate += ", 支持的类型: %s";
            message = String.format(messageTemplate, requiredType, Arrays.toString(availableTypes));
        } else {
            message = String.format(messageTemplate, requiredType);
        }
        return message;
    }

    public String getRequiredType() {
        return requiredType;
    }

    public String[] getAvailableTypes() {
        return availableTypes;
    }
}
