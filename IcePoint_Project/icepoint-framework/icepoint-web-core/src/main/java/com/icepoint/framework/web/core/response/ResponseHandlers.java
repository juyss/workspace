package com.icepoint.framework.web.core.response;

import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * @author Jiawei Zhao
 */
public class ResponseHandlers {

    private ResponseHandlers() {}

    public static final ResponseProcessor<Response<?>> CODE_404 = new ResponseProcessor<Response<?>>() {

        @Override
        public Response<?> process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response) {
            body.setCode(CoreMessage.NOT_FOUND.getCode());
            return body;
        }

        @Override
        public boolean supports(Class<?> type) {
            return Response.class.isAssignableFrom(type);
        }
    };

    public static final ResponseProcessor<Response<?>> EMPTY = CODE_404;

    public static final ResponseProcessor<Response<?>> NULL = CODE_404;
}
