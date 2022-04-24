package com.juyss.common.aop.annotation;

import java.lang.annotation.*;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DataScope
 * @Desc: Aspect通过解析包含此注解的方法,进行切入,控制数据权限
 * @package com.juyss.common.aop.annotation
 * @project manager
 * @date 2021/1/12 17:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
}
