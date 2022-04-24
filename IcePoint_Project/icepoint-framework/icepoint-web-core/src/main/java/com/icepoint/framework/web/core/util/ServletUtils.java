package com.icepoint.framework.web.core.util;

import com.icepoint.framework.core.util.RecursiveUtils;
import lombok.experimental.UtilityClass;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@UtilityClass
public class ServletUtils {

    public static HttpServletRequest getRequiredRequest() {
        return lookupRequest().orElseThrow(() -> new IllegalStateException("找不到Request对象"));
    }

    @Nullable
    public static HttpServletRequest getRequest() {
        return lookupRequest().orElse(null);
    }

    private static Optional<HttpServletRequest> lookupRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <R extends HttpServletRequest> R unwrapRequestForType(ServletRequest request, Class<R> requestType) {

        if (request.getClass() == requestType) {
            return (R) request;
        } else if (!(request instanceof HttpServletRequestWrapper)) {
            return null;
        }

        List<ServletRequest> list = new ArrayList<>();

        RecursiveUtils.execute(
                request,
                req -> {

                    if (req instanceof HttpServletRequestWrapper) {

                        return ((HttpServletRequestWrapper) req).getRequest();

                    } else if (req instanceof RequestFacade) {

                        Field field = ReflectionUtils.findField(RequestFacade.class, "request");
                        assert field != null;

                        ReflectionUtils.makeAccessible(field);

                        return (ServletRequest) ReflectionUtils.getField(field, req);

                    } else {

                        return null;
                    }

                },
                Objects::nonNull,
                list::add);

        return list.isEmpty()
                ? null
                : (R) list.stream()
                .filter(req -> req.getClass() == requestType)
                .findFirst()
                .orElse(null);
    }

    public static <R extends HttpServletRequest> R unwrapRequiredRequestForType(HttpServletRequest request, Class<R> requestType) {

        R req = unwrapRequestForType(request, requestType);
        Assert.notNull(req, "找不到对应的Request类型: " + requestType);

        return req;
    }
}
