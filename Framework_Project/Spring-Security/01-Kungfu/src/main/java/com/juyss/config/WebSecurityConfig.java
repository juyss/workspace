package com.juyss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: WebSecurityConfig
 * @Desc: Spring-Security配置类
 * @package com.juyss.config
 * @project Spring-Security
 * @date 2020/10/19 20:43
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //设置请求相关
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //登陆相关
        http.formLogin()
                .loginPage("/index.jsp") //定义登陆页面
                .loginProcessingUrl("/login") //定义登陆请求
                .usernameParameter("username") //定义用户名
                .passwordParameter("password") //定义密码
                //.successForwardUrl("/main") 如果只存在这一个配置,登陆请求会转发到这个请求
                .defaultSuccessUrl("/toMain"); //定义成功重定向URL


        //权限相关
        http.authorizeRequests()
                .antMatchers("/index.jsp","/layui/**").permitAll() //放行匹配资源
                .antMatchers("/level2/1").hasAuthority("太极拳") //定义权限
                .antMatchers("/level3/2").hasAuthority("龟派气功")
                .antMatchers("/level?/1").hasAuthority("一级")
                .antMatchers("/level?/2").hasAuthority("二级")
                .antMatchers("/level?/3").hasAuthority("三级")
                .antMatchers("/level1/*").hasRole("学徒") //定义角色权限
                .antMatchers("/level2/*").hasRole("大师")
                .antMatchers("/level3/*").hasRole("宗师")
                .anyRequest().authenticated(); //所有请求必须认证

        //注销相关
        http.logout()
                .logoutUrl("/logout.do") //定义注销请求
                .logoutSuccessUrl("/index.jsp"); //定义注销成功的跳转URL

        //权限不足处理
        http.exceptionHandling().accessDeniedPage("/unauth"); //定义权限拒绝跳转页面

        //记住我
        http.rememberMe(); //记住我功能

        //http.csrf().disable(); //禁用csrf

    }

    //设置认证方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //内存中的用户认证方式
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password(new BCryptPasswordEncoder().encode("102850")).roles("学徒")
//                .and()
//                .withUser("liming").password(new BCryptPasswordEncoder().encode("000000")).roles("宗师");

        //数据库中用户认证方式，密码加密方式为BCrypt
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }
}
