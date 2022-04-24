package com.icepoint.framework.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json序列化器
 *
 * @author Jiawei Zhao
 */
public class JsonSerializer extends ObjectMapperSerializer<ObjectMapper> {

    public JsonSerializer(ObjectMapper source, SerializationExceptionHandler exceptionHandler) {
        super(source, exceptionHandler);
    }

}
