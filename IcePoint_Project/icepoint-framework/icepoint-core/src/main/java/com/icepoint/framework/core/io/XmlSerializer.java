package com.icepoint.framework.core.io;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * xml序列化器
 *
 * @author Jiawei Zhao
 */
public class XmlSerializer extends ObjectMapperSerializer<XmlMapper> {

    public XmlSerializer(XmlMapper source, SerializationExceptionHandler exceptionHandler) {
        super(source, exceptionHandler);
    }

}
