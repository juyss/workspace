package com.icepoint.base.config.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 打印异常用
 *
 * @author Jiawei Zhao
 */
@RestControllerAdvice
public class GlobalExceptionPrinter {

    @ExceptionHandler(Exception.class)
    public void handle(Exception e) throws Exception {
        e.printStackTrace();
        throw e;
    }
}
