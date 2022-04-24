package com.icepoint.framework.web.core.message;

import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 根据{@link Response}中的code查找对应的message
 *
 * @author Jiawei Zhao
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class MessageResponseProcessor implements ResponseProcessor<Response<?>> {

    private final MessageContext messageContext;

    @Autowired
    public MessageResponseProcessor(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @Override
    public Response<?> process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response) {

        if (StringUtils.hasText(body.getMessage())) {
            return body;
        }

        String message = messageContext.getMessage(body.getCode());
        body.setMessage(message);

        return body;
    }

    @Override
    public boolean supports(Class<?> type) {
        return Response.class.isAssignableFrom(type);
    }
}
