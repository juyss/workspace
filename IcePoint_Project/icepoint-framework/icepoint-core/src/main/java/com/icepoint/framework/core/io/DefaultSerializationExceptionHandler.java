package com.icepoint.framework.core.io;

import java.io.IOException;

/**
 * @author Jiawei Zhao
 */
public class DefaultSerializationExceptionHandler implements SerializationExceptionHandler {

    public static final DefaultSerializationExceptionHandler INSTANCE = new DefaultSerializationExceptionHandler();

    @Override
    public void handle(IOException exception) {
        throw new NestedSerializationException("Bean对象序列化或反序列化时发生异常, 原因: ", exception);
    }
}
