package com.juyss.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: SecurityConfig
 * @Desc:
 * @package com.juyss.config
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 13:27
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("username") //定义用户名参数
                .passwordParameter("password") //定义密码参数
                .loginPage("/login") //定义登录页,需要对此请求或者页面放行
                .loginProcessingUrl("/login") //定义登录的请求路径
                .defaultSuccessUrl("/index",true) //登陆成功重定向URL
                .permitAll(); //放行登陆相关请求

        http.authorizeRequests()
                .antMatchers("/index","/","/index.html").permitAll()
                .antMatchers("/level1/**").hasAnyRole("vip1","vip2","vip3")
                .antMatchers("/level2/**").hasAnyRole("vip2","vip3")
                .antMatchers("/level3/**").hasRole("vip3")
                .anyRequest().authenticated();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //使用GET请求执行注销
                //.logoutUrl("/logout") // POST请求执行注销操作
                .logoutSuccessUrl("/index")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("102850")).roles("vip3","vip2","vip1");
    }
}
