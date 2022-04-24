package com.icepoint.framework.web.core.response;

import com.icepoint.framework.core.util.CastUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@ConditionalOnBean(ResponseProcessor.class)
@ControllerAdvice
public class ResponseProcessorAdvice extends AbstractMappingJacksonResponseBodyAdvice {

    private final List<ResponseProcessor<?>> processors;

    public ResponseProcessorAdvice(ObjectProvider<ResponseProcessor<?>> processors) {
        this.processors = processors.orderedStream()
                .collect(Collectors.toList());
    }

    @Override
    protected void beforeBodyWriteInternal(
            MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType,
            ServerHttpRequest request, ServerHttpResponse response) {

        Object value = bodyContainer.getValue();
        if (!(value instanceof Response))
            return;

        List<ResponseProcessor<?>> resolvedProcessors = new LinkedList<>();
        Response<?> body = process(CastUtils.cast(value), request, response, resolvedProcessors);
        bodyContainer.setValue(body);
    }

    private Response<?> process(Response<?> body, ServerHttpRequest request, ServerHttpResponse response,
            List<ResponseProcessor<?>> resolvedProcessors) {

        ResponseProcessor<?> processor = processors.stream()
                .filter(p -> p.supports(body.getClass()))
                .filter(p -> resolvedProcessors.stream().noneMatch(rp -> rp.equals(p)))
                .findAny()
                .orElse(null);

        if (processor != null) {
            resolvedProcessors.add(processor);
            return process(processor.process(body, request, response), request, response, resolvedProcessors);
        } else {
            return body;
        }
    }
}
