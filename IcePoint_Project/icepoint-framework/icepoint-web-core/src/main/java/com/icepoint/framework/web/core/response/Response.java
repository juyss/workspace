package com.icepoint.framework.web.core.response;

import org.springframework.lang.Nullable;

/**
 * Controller返回值顶级接口
 *
 * @author Jiawei Zhao
 */
public interface Response<T> {

    @Nullable
    T getData();

    void setData(@Nullable T data);

    @Nullable
    String getMessage();

    void setMessage(@Nullable String message);

    String getCode();

    void setCode(String code);

    boolean isEmpty();
}
