package com.icepoint.framework.web.core.rest;

import org.springframework.core.annotation.AliasFor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.lang.annotation.*;

/**
 * @author Jiawei Zhao
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RepositoryRestResource
public @interface MultiplyPathRepositoryRestResource {

    @AliasFor(annotation = RepositoryRestResource.class, attribute = "path")
    String path() default "";
}
