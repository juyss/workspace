package com.icepoint.framework.core.io;

/**
 * @author Jiawei Zhao
 */
public interface SerializationExceptionHandlerProvider {

    /**
     * 获取处理序列化异常的{@link SerializationExceptionHandler}
     *
     * @return SerializerExceptionHandler
     */
    SerializationExceptionHandler getExceptionHandler();
}
