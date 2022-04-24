package com.juyss.demo.annotation;

import java.lang.annotation.*;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: MyPointCut
 * @package com.juyss.demo.annotation
 * @project demo
 * @date 2022/1/27 17:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MyPointCut {

    String message() default "";
}