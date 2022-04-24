package com.icepoint.base.util;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ServletUtils {

    @Nullable
    public static HttpServletRequest getRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .orElse(null);
    }

    /**
     * 获取Request请求对象
     * @return 返回Request对象
     */
    public static HttpServletRequest getRequiredRequest() {
        HttpServletRequest request = getRequest();
        Assert.notNull(request, "找不到Request对象");
        return request;
    }
}
