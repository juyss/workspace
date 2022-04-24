package com.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: AnnoPointCut
 * @Desc: 使用注解标注这个类是一个切面,环绕功能遵循先进先出原则
 * @package com.annotation
 * @project KuangStudy-Spring
 * @date 2020/9/5 19:19
 */
@Aspect
public class AnnoPointCut {

    @Before("execution(* com.service.UserServiceImpl.*(..))")
    public void Before(){
        System.out.println("注解切面功能前置");
    }

    @After("execution(* com.service.UserServiceImpl.*(..))")
    public void After(){
        System.out.println("注解切面功能后置");
    }

    @Around("execution(* com.service.UserServiceImpl.*(..))")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("注解切面功能环绕前");
        Object proceed = pjp.proceed();
        System.out.println("注解切面功能环绕后");
        return proceed;
    }
}
