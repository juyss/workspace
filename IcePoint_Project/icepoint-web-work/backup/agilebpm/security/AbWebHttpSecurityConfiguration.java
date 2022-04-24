package com.icepoint.framework.workorder.configuration.agilebpm.security;

import com.dstz.base.core.jwt.JWTService;
import com.dstz.org.api.context.ICurrentContext;
import com.dstz.security.authentication.AccessDecisionManagerImpl;
import com.dstz.security.authentication.FilterInvocationSecurityMetadataSourceImpl;
import com.dstz.security.authentication.JWTAuthenticationFilter;
import com.dstz.security.authentication.SecurityInterceptor;
import com.dstz.security.filter.EncodingFilter;
import com.dstz.security.filter.RefererCsrfFilter;
import com.dstz.security.filter.RequestThreadFilter;
import com.dstz.security.filter.XssFilter;
import com.dstz.security.forbidden.DefaultAccessDeniedHandler;
import com.dstz.security.forbidden.DefualtAuthenticationEntryPoint;
import com.dstz.security.login.CustomPwdEncoder;
import com.dstz.security.login.UserDetailsServiceImpl;
import com.dstz.security.login.context.LoginContext;
import com.dstz.security.login.logout.DefualtLogoutSuccessHandler;
import com.dstz.sys.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@EnableConfigurationProperties({AbSecurityProperties.class})
@Configuration
public class AbWebHttpSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AbSecurityProperties abSecurityProperties;

    CustomPwdEncoder customPwdEncoder = new CustomPwdEncoder();

    @Bean
    public LoginContext loginContext() {
        return new LoginContext();
    }

    @Bean
    public ContextUtil contextUtil(ICurrentContext loginContext) {
        ContextUtil context = new ContextUtil();
        context.setCurrentContext(loginContext);
        return context;
    }

    public XssFilter xssFilter() {
        XssFilter xssFilter = new XssFilter();
        List<String> ignores = new ArrayList<>();
        String ignoresConfig = this.abSecurityProperties.getXssIgnores();
        if (StringUtils.isNotEmpty(ignoresConfig)) {
            ignores = Arrays.asList(ignoresConfig.split(","));
        }

        xssFilter.setIngores(ignores);
        return xssFilter;
    }

    public RefererCsrfFilter csrfFilter() {
        RefererCsrfFilter filter = new RefererCsrfFilter();
        List<String> ingores = new ArrayList<>();
        String ignoresConfig = this.abSecurityProperties.getCsrfIgnores();
        if (StringUtils.isNotEmpty(ignoresConfig)) {
            ingores = Arrays.asList(ignoresConfig.split(","));
        }

        filter.setIngores(ingores);
        return filter;
    }

    public DefualtLogoutSuccessHandler logoutSuccessHandler() {
        return new DefualtLogoutSuccessHandler();
    }

    public DefaultAccessDeniedHandler accessDeniedHandler() {
        return new DefaultAccessDeniedHandler();
    }

    public DefualtAuthenticationEntryPoint authenticationLoginEntry() {
        return new DefualtAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(new DefualtAuthenticationEntryPoint());
        http.rememberMe().key("rememberPrivateKey");
        http.logout().logoutSuccessHandler(new DefualtLogoutSuccessHandler());
        http.addFilterAt(this.csrfFilter(), CsrfFilter.class);
        SecurityInterceptor securityInterceptor = this.abSecurityInterceptor();
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
        http.addFilterBefore(new RequestThreadFilter(), CsrfFilter.class);
        http.addFilterBefore(new EncodingFilter(), CsrfFilter.class);
        http.addFilterBefore(this.jwtAuthenticationFilter(), LogoutFilter.class);
        http.exceptionHandling().accessDeniedHandler(this.accessDeniedHandler());
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

    @Bean({"abJWTAuthenticationFilter"})
    protected JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean({"abJWTService"})
    protected JWTService abJWTService() {
        return new JWTService();
    }

    @Bean
    protected AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

    @Bean
    protected FilterInvocationSecurityMetadataSource securityMetadataSource() {
        FilterInvocationSecurityMetadataSourceImpl securityMetadataSource = new FilterInvocationSecurityMetadataSourceImpl();
        List<String> ingores = new ArrayList<>();
        String ignoresConfig = this.abSecurityProperties.getAuthIgnores();
        if (StringUtils.isNotEmpty(ignoresConfig)) {
            ingores = Arrays.asList(ignoresConfig.split(","));
        }

        securityMetadataSource.setIngores(ingores);
        return securityMetadataSource;
    }

    @Override
    @Bean({"userDetailsService"})
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService()).passwordEncoder(this.customPwdEncoder);
    }

    @Override
    @Bean({"authenticationManager"})
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected SecurityInterceptor abSecurityInterceptor() {
        SecurityInterceptor intercept = new SecurityInterceptor();
        intercept.setAccessDecisionManager(new AccessDecisionManagerImpl());
        intercept.setSecurityMetadataSource(this.securityMetadataSource());
        return intercept;
    }

    @Bean({"localeResolver"})
    public CookieLocaleResolver cookieLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.CHINA);
        return cookieLocaleResolver;
    }
}
