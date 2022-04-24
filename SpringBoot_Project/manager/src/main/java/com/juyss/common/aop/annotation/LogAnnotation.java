package com.juyss.common.aop.annotation;

import java.lang.annotation.*;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LogAnnotation
 * @Desc: Aspect通过解析此注解控制日志操作切入
 * @package com.juyss.common.aop.annotation
 * @project manager
 * @date 2021/1/12 17:08
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    String action() default "";
}
