package com.icepoint.framework.data;

import com.icepoint.framework.data.annotation.ReadTransaction;
import com.icepoint.framework.data.annotation.WriteTransaction;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * 切入所有@Service的方法，判断是读取还是写入方法，来套入不同的事务类型
 *
 * @author Jiawei Zhao
 */
@Slf4j
@Aspect
@Component
public class TransactionAspect {

    private final TransactionAspectExecutor executor;

    private final TransactionConfiguration configuration;

    @Autowired
    public TransactionAspect(TransactionAspectExecutor executor, TransactionConfiguration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }

    @Around("execution(* com.icepoint..*.*(..)) " +
            "&& @target(org.springframework.stereotype.Service) " +
            "|| this(org.springframework.data.repository.Repository)")
    public Object aspectOf(ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        // Object的方法放行
        if (ReflectionUtils.isObjectMethod(method)) {
            return point.proceed(point.getArgs());
        }

        // 如果有事务，则直接判断是否为符合规范的读写事务，如果没有事务，则放行，并输出提示日志
        String methodName = method.getName();
        if (TransactionSynchronizationManager.isActualTransactionActive()) {

            // 如果是只读事务，并且是写方法，那就执行写事务
            // 如果是非只读事务，并且是读方法，执行读事务
            // 其余情况直接放行
            boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            if (isWriteMethod(methodName) && isReadOnly) {
                return executor.write(point);
            } else if (isReadMethod(methodName) && !isReadOnly) {
                return executor.read(point);
            } else {
                return point.proceed(point.getArgs());
            }
        }
        // 如果没有事务
        else {

            // 如果方法上有事务注解，以注解为准
            if (AnnotationUtils.findAnnotation(method, Transactional.class) != null) {
                return point.proceed(point.getArgs());
            }

            // 如果没有事务则根据方法名进行判断，来执行读或者写事务
            // 如果读写方法都不匹配，则输出日志提示并放行
            if (isReadMethod(methodName)) {
                return executor.read(point);
            } else if (isWriteMethod(methodName)) {
                return executor.write(point);
            } else {
                return processWithWarnLog(methodName, point);
            }
        }
    }

    private boolean isReadMethod(String methodName)  {
        for (String readMethod : configuration.getReadMethods()) {
            if (methodName.startsWith(readMethod)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWriteMethod(String methodName)  {
        for (String readMethod : configuration.getWriteMethods()) {
            if (methodName.startsWith(readMethod)) {
                return true;
            }
        }
        return false;
    }

    private Object processWithWarnLog(String methodName, ProceedingJoinPoint point) throws Throwable {
        log.warn("方法名不符合规范, 未被纳入事务层管理, 方法名: [ " + methodName + " ]");
        return point.proceed(point.getArgs());
    }

    /**
     * 通过注解定义事务，在切面中执行不同的方法来区别使用不同的事务
     *
     * @author Jiawei Zhao
     */
    @Component
    public static class TransactionAspectExecutor {

        @ReadTransaction
        public Object read(ProceedingJoinPoint point) throws Throwable {
            return execute(point);
        }

        @WriteTransaction
        public Object write(ProceedingJoinPoint point) throws Throwable {
            return execute(point);
        }

        private Object execute(ProceedingJoinPoint point) throws Throwable {
            Object[] args = point.getArgs();
            return point.proceed(args);
        }
    }


}