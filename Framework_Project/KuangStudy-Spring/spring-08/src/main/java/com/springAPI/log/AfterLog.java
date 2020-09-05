package com.springAPI.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AfterLog
 * @Desc: 后置功能
 * @package com.springAPI.log
 * @project KuangStudy-Spring
 * @date 2020/9/4 21:17
 */
public class AfterLog implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("日志: "+target.getClass().getName()+"类-->"+method.getName()+"方法执行后");
        System.out.println("日志: 方法返回值-->"+returnValue);
    }

}
