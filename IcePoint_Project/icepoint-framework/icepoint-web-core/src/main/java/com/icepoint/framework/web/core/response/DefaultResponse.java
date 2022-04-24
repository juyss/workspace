package com.icepoint.framework.web.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
@JsonInclude
public class DefaultResponse<T> implements Response<T> {

    @Nullable
    private T data;

    private String message;

    private String code;

    public DefaultResponse() {
    }

    public DefaultResponse(@Nullable T data) {
        this.data = data;
    }

    @Nullable
    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(@Nullable T data) {
        this.data = data;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean isEmpty() {
        return data == null;
    }
}
