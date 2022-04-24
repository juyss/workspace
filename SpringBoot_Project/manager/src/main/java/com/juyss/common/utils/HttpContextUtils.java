package com.juyss.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: HttpContextUtils
 * @Desc:
 * @package com.juyss.common.utils
 * @project manager
 * @date 2021/1/12 19:04
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.contains("application/json")
                || (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))
        ));
    }
}
