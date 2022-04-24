package com.juyss.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: MyAspect
 * @Desc: AOP织入

try{
	try{
		//@Around
		//@Before
		method.invoke(..);
		//@Around
       }catch(){
		throw.....;
   }finally{
		//@After
   }
	//@AfterReturning
}catch(){
	//@AfterThrowing
}finally{
    //@Around
}
正常情况 @Around @Before 目标方法 @After @AfterReturning @Around;
异常情况 @Around @Before 目标方法(出现异常) @AfterThrowing @After @Around;


 * @package com.juyss.aspect
 * @project atguigu-Advanced
 * @date 2020/12/7 15:10
 */
@Component
@Aspect
public class MyAspect {

    //定义切入点
    @Pointcut("within(com.juyss.service.*)")
    public void pointcut(){}

    //环绕通知 ===> 正常情况执行在@Before和@After之前,如果执行过程中抛异常,只执行前置环绕通知,后置环绕不执行
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point){
        System.out.println("前置环绕通知!!!");
        Object proceed = null;
        try {
            System.out.println("point.proceed()执行前----------------------------");
            Signature signature = point.getSignature();
            System.out.println("获取类名:"+signature.getName());
            System.out.println("point.proceed()执行前----------------------------");
            proceed = point.proceed();
            System.out.println("point.proceed()执行后----------------------------");
            System.out.println("获取返回值:"+proceed);
            System.out.println("point.proceed()执行后----------------------------");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("后置环绕通知!!!");
        return proceed;
    }

    //前置通知 ===> 方法执行前
    @Before("pointcut()")
    public void before(){
        System.out.println("前置通知生效!!!");
    }

    //返回通知 ===> 方法正常执行完后执行,可以获取返回值.如果方法执行过程中抛异常,则不会执行
    @AfterReturning(value = "pointcut()",returning = "returnValue")
    public void afterReturning(Object returnValue){
        System.out.println("返回通知生效!!! ------返回值:"+returnValue);
    }

    //后置通知 ===> 在finally代码块中执行,无论如何都会执行的通知
    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        System.out.println("后置通知生效!!! ------ 方法名:"+signature.getName());
    }

    //异常通知 ===> 在执行过程中抛出异常时执行
    @AfterThrowing(value = "pointcut()",throwing = "e")
    public void afterThrowing(Exception e){
        System.out.println("异常通知生效!!! 异常信息:"+e.getMessage());
    }
}
