package com.github.tangyi.common.security.ty;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class TySecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    public TySecurityConfigurer(TyAuthService tyAuthService) {
        this.tyAuthService = tyAuthService;
    }

    private TyAuthService tyAuthService;


    public TyAuthenticationManager tyAuthenticationManager() {
        return  new TyAuthenticationManager(tyAuthService);
    }

    public TyAuthFilter tyAuthFilter(){
        TyAuthFilter tyAuthFilter = new TyAuthFilter(tyAuthenticationManager(),new TyTokenExtractor());
        return tyAuthFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(tyAuthFilter(), OAuth2AuthenticationProcessingFilter.class);
    }
}
