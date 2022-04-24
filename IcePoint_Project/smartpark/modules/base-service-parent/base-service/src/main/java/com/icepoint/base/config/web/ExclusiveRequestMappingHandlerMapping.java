package com.icepoint.base.config.web;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 激活注解{@link ExcludedHandler}和{@link ExcludedHandlers}的HandlerMapping，
 * 提供屏蔽部分Handler的功能，主要作用于屏蔽父类Controller中的Handler请求映射
 *
 * @author Jiawei Zhao
 * @see ExcludedHandler
 * @see ExcludedHandlers
 */
@Deprecated // FIXME: jiawei: 有bug，会重复加载Controller中的Handler，待解决
public class ExclusiveRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(@NonNull Method method, Class<?> handlerType) {
        ExcludedHandler excludedHandler = handlerType.getAnnotation(ExcludedHandler.class);
        if (excludedHandler != null && isExcludedHandler(method, excludedHandler)) {
            return null;
        }

        ExcludedHandlers excludedHandlers = handlerType.getAnnotation(ExcludedHandlers.class);
        if (excludedHandlers != null) {
            ExcludedHandler[] handlers = excludedHandlers.value();
            if (ArrayUtils.isNotEmpty(handlers)) {
                for (ExcludedHandler handler : handlers) {
                    if (isExcludedHandler(method, handler))
                        return null;
                }
            }
        }

        return super.getMappingForMethod(method, handlerType);
    }

    private boolean isExcludedHandler(@NonNull Method method, ExcludedHandler excludedHandler) {
        String methodName = excludedHandler.methodName();
        Class<?>[] parameterTypes = excludedHandler.parameterTypes();
        return Objects.equals(methodName, method.getName())
                && Arrays.equals(parameterTypes, method.getParameterTypes());
    }
}
