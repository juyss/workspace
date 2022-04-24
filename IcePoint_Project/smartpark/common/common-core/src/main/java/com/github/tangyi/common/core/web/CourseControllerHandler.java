package com.github.tangyi.common.core.web;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 日期类型转换绑定
 */
@ControllerAdvice
public class CourseControllerHandler {
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
    if (genericConversionService != null) {
      genericConversionService.addConverter(new CourseDateConverter());
    }
  }
}