package com.icepoint.framework.web.core.response;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * 用来转换响应数据的接口
 *
 * @param <R> 返回响应数据的类型
 * @author Jiawei Zhao
 */
public interface ResponseProcessor<R extends Response<?>> {

    /**
     * 实现响应数据转换的方法，参数是原响应数据，返回值是转换后的响应数据
     *
     * @param body     Response对象
     * @param request  ServerHttpRequest
     * @param response ServerHttpResponse
     * @return 响应数据的返回结果
     */
    R process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response);

    /**
     * 定义转换方法{@link #process}能够接受什么类型的参数进行转换，只有这个方法返回true才会执行{@link #process}
     *
     * @param type 原响应数据的Class对象
     * @return 如果是能支持的类型返回true，否则返回false
     */
    boolean supports(Class<?> type);
}
