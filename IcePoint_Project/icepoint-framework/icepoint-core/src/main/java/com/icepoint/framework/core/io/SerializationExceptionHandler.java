package com.icepoint.framework.core.io;

import java.io.IOException;

/**
 * 处理序列化或者反序列化操作时发生的异常
 *
 * @author Jiawei Zhao
 */
public interface SerializationExceptionHandler {

    /**
     * 处理{@link IOException}，并包装成一个{@link NestedSerializationException}进行再抛出
     *
     * @param exception IOException
     * @throws NestedSerializationException 包装后的IOE异常
     */
    void handle(IOException exception) throws NestedSerializationException;
}
