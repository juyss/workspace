package com.springAPI.log;


import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: BeforeLog
 * @Desc: 前置功能
 * @package com.springAPI.log
 * @project KuangStudy-Spring
 * @date 2020/9/4 21:16
 */
public class BeforeLog implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("日志: 前置功能");
        System.out.println("日志: "+target.getClass().getName()+"类-->"+method.getName()+"方法执行前");
    }

}
