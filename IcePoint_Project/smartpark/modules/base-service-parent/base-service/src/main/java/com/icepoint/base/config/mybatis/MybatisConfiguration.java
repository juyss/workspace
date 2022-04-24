package com.icepoint.base.config.mybatis;

import com.icepoint.base.config.mybatis.pageable.MybatisPageableSupports;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Mybatis的配置，基于spring-boot-starter-mybatis之上，做一些自定义的配置
 *
 * @see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
 */
@Import(MybatisPageableSupports.class)
@EnableAutoConfiguration(excludeName = "com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration")
public class MybatisConfiguration {

}
