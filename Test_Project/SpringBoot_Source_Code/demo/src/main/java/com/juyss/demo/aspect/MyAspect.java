package com.juyss.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: MyAspect
 * @package com.juyss.demo.aspect
 * @project demo
 * @date 2022/1/27 17:11
 */
@Aspect
@Component
public class MyAspect {

    @Before("@annotation(com.juyss.demo.annotation.MyPointCut)")
    public void myBefore(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        System.out.println("Before Aspect:" + methodName);
    }

}
