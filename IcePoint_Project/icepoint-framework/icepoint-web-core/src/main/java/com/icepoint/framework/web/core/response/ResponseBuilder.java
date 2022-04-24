package com.icepoint.framework.web.core.response;

import com.icepoint.framework.core.util.MessageTemplates;
import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class ResponseBuilder<T, R extends Response<T>> {

    private final R response;

    private ResponseBuilder(R response) {
        this.response = response;
    }

    public ResponseBuilder<T, R> data(@Nullable T body) {
        response.setData(body);
        return this;
    }

    public ResponseBuilder<T, R> code(String code) {
        response.setCode(code);
        return this;
    }

    public ResponseBuilder<T, R> message(@Nullable String message) {
        response.setMessage(message);
        return this;
    }

    public R build() {
        Assert.isTrue(StringUtils.hasText(response.getCode()), MessageTemplates.notNull("code"));
        return response;
    }

    public static <T>  ResponseBuilder<Collection<T>, PageResponse<T>> page(@Nullable Page<T> page) {
        return configure(new PageResponse<>(page));
    }

    public static <T> ResponseBuilder<Collection<T>, CollectionResponse<T>> collection(@Nullable Collection<T> collection) {
        return configure(new CollectionResponse<>(collection));
    }

    public static <K, V> ResponseBuilder<Map<K, V>, MapResponse<K, V>> map(@Nullable Map<K, V> map) {
        return configure(new MapResponse<>(map));
    }

    public static <T> ResponseBuilder<T, Response<T>> any(@Nullable T data) {
        return configure(new DefaultResponse<>(data));
    }

    public static Response<Void> justOk(@Nullable String message) {
        return configure(new DefaultResponse<Void>())
                .code(CoreMessage.OK.getCode())
                .message(message)
                .build();
    }

    public static Response<Void> justOk() {
        return justOk(null);
    }

    public static <T> ResponseBuilder<Object, UnsafeResponse> unsafe(T data) {
        return configure(new UnsafeResponse(data));
    }

    public static <T, R extends Response<T>> ResponseBuilder<T, R> configure(R response) {
        return new ResponseBuilder<>(response);
    }
}
