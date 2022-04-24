package com.icepoint.framework.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.icepoint.framework.core.util.ApplicationContextUtils;
import org.springframework.data.util.Lazy;

/**
 * 方便获取各种不同文件或者数据类型Jackson序列化器的工具类
 *
 * @author Jiawei Zhao
 */
public class Serializers {

    private static final SerializationExceptionHandler DEFAULT_EXCEPTION_HANDLER =
            DefaultSerializationExceptionHandler.INSTANCE;

    private static final Lazy<JsonSerializer> JSON = Lazy.of(() -> ApplicationContextUtils
            .getContext().getBean(ObjectMapper.class))
            .or(ObjectMapper::new)
            .map(mapper -> new JsonSerializer(mapper, DEFAULT_EXCEPTION_HANDLER));

    private static final Lazy<XmlSerializer> XML = Lazy.of(() -> ApplicationContextUtils
            .getContext().getBean(XmlMapper.class))
            .or(XmlMapper::new)
            .map(mapper -> new XmlSerializer(mapper, DEFAULT_EXCEPTION_HANDLER));

    private Serializers() {
    }

    /**
     * 返回基于JSON的序列化器，其中包含序列化和反序列化的功能
     *
     * @return JsonSerializer
     */
    public static JsonSerializer json() {
        return JSON.get();
    }

    /**
     * 返回基于XML的序列化器，其中包含序列化和反序列化的功能
     *
     * @return JsonSerializer
     */
    public static XmlSerializer xml() {
        return XML.get();
    }

    /**
     * 返回处理序列化或者反序列化的异常处理器
     *
     * @return SerializerExceptionHandler
     */
    public static SerializationExceptionHandler exceptionHandler() {
        return DEFAULT_EXCEPTION_HANDLER;
    }
}
