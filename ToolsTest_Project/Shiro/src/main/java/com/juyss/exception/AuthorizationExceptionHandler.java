package com.juyss.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AuthorizationExceptionHandler
 * @Desc: 认证异常处理类
 * @package com.juyss.exception
 * @project Shiro
 * @date 2021/1/4 14:33
 */
@ControllerAdvice
@Slf4j
public class AuthorizationExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }
}
