package com.github.tangyi.common.security.authorize;

import com.github.tangyi.common.core.properties.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)
public class IgnoresAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        String[] ignores = new String[filterIgnorePropertiesConfig.getUrls().size()];
        filterIgnorePropertiesConfig.getUrls().toArray(ignores);
        config.antMatchers(ignores)
                //放行
                .permitAll();
    }

}
