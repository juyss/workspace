package com.juyss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: SecurityConfig
 * @package com.juyss.config
 * @project Spring-Security
 * @date 2021/12/16 0:50
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html") //登陆页面
                .loginProcessingUrl("/login") //登陆请求
                .successForwardUrl("/main") //登陆成功跳转链接
                .failureForwardUrl("/failure"); //登陆失败跳转链接

        http.authorizeRequests()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();
    }
}
