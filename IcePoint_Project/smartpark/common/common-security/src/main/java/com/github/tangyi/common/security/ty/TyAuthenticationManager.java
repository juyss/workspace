package com.github.tangyi.common.security.ty;

import com.github.tangyi.common.security.core.GrantedAuthorityImpl;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TyAuthenticationManager implements AuthenticationManager, InitializingBean {

    private TyAuthService tyAuthService;
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    public TyAuthenticationManager(TyAuthService tyAuthService) {
        this.tyAuthService = tyAuthService;
    }

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(tyAuthService != null, "tyAuthService is required");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication == null) {
            throw new InvalidTokenException("Invalid token (token not found)");
        }
        String token = (String) authentication.getPrincipal();
        MainAccessToken accessToken = tyAuthService.getAccessTokenFromRedis(token);
        if (accessToken == null) throw new OAuth2AccessDeniedException("token invalid");

        MainUserWithRole userInfo = null;
        try {
            userInfo = tyAuthService.getUserInfo(accessToken.getAccessToken());
        } catch (Exception e) {
            String msg = e.getMessage();
            if(e.getMessage().contains("Column 'user_id' cannot be null")) msg = "用户不存在，请先同步用户信息";
            throw new OAuth2Exception(msg);
        }

        ClientDetails clientDetails = new BaseClientDetails();
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, "clientId", new HashSet(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
                MainUserWithRole.IDENTIFIER_PREFIX + userInfo.getLoginName(), authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(getAuthority(userInfo)));
        OAuth2Authentication oauth2Authentication = new OAuth2Authentication(oAuth2Request, result);


//        result.setAuthenticated(true);
        return oauth2Authentication;
    }

    private Set<GrantedAuthority> getAuthority(MainUserWithRole userInfo) {
        return userInfo.getRoles()
                .stream()
                .map(role -> new GrantedAuthorityImpl(role.getRoleCode().toUpperCase()))
                .collect(Collectors.toSet());
    }

}
