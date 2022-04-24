package com.icepoint.framework.web.file.io;

import com.icepoint.framework.web.core.message.Message;
import lombok.Getter;

/**
 * @author Jiawei Zhao
 */
@Getter
public enum FileMessage implements Message {

    FILE_UPLOAD_FAILED("51001"),

    FILE_DELETE_FAILED("51003");

    private final String code;
    private final Class<?>[] exTypes;

    FileMessage(String code, Class<?>... exTypes) {
        this.code = code;
        this.exTypes = exTypes;
    }
}
