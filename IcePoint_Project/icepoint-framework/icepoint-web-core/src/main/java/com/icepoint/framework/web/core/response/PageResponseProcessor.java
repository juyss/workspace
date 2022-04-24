package com.icepoint.framework.web.core.response;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@SuppressWarnings({ "rawtypes" })
@Component
public class PageResponseProcessor implements ResponseProcessor<Response<?>> {

    @Override
    public Response process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response) {

        if (!(body.getData() instanceof Page) || body instanceof PageResponse) {
            return body;
        }

        return PageResponse.of(CastUtils.cast(body));
    }

    @Override
    public boolean supports(Class<?> type) {
        return Response.class.isAssignableFrom(type);
    }
}
