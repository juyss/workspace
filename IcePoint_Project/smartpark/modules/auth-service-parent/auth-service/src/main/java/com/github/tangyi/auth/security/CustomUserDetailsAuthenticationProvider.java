package com.github.tangyi.auth.security;

import com.github.tangyi.auth.model.CustomUserDetails;
import com.github.tangyi.common.core.exceptions.TenantNotFoundException;
import com.github.tangyi.common.core.utils.SpringContextHolder;
import com.github.tangyi.common.security.core.CustomUserDetailsService;
import com.github.tangyi.common.security.core.GrantedAuthorityImpl;
import com.github.tangyi.common.security.event.CustomAuthenticationFailureEvent;
import com.github.tangyi.common.security.event.CustomAuthenticationSuccessEvent;
import com.github.tangyi.common.security.tenant.TenantContextHolder;
import com.github.tangyi.common.security.ty.MainAccessToken;
import com.github.tangyi.common.security.ty.MainUserWithRole;
import com.github.tangyi.common.security.ty.TyAuthService;
import com.github.tangyi.model.MainRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证Provider，提供获取用户信息、认证、授权等功能
 *
 * @author tangyi
 * @date 2019/5/28 21:26
 */
@Slf4j
public class CustomUserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    private CustomUserDetailsService userDetailsService;

    private String userNotFoundEncodedPassword;

    private TyAuthService tyAuthService;//统一认证

    public void setTyAuthService(TyAuthService tyAuthService) {
        this.tyAuthService = tyAuthService;
    }

    public CustomUserDetailsAuthenticationProvider(PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.debug("Authentication failed: password is blank");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "密码为空"));
        }
        // 获取密码
        String presentedPassword = authentication.getCredentials().toString();
        // 匹配密码
        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            log.debug("Authentication failed: invalid password");
            //如果密码匹配失败，走 统一认证密码授权模式，偿试是否匹配
            boolean match = tyPasswordModeMatche(authentication,userDetails);
            if(!match) {
                SpringContextHolder.publishEvent(new CustomAuthenticationFailureEvent(authentication, userDetails));
                throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "用户名或密码错误"));
            }
        }
        if(!userDetails.isEnabled()){
            log.debug("Authentication failed: user  is disabled");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "用户被禁用"));
        }
        SpringContextHolder.publishEvent(new CustomAuthenticationSuccessEvent(authentication, userDetails));
	}
    // 统一认证密码授权模式，偿试是否匹配
    private boolean tyPasswordModeMatche(UsernamePasswordAuthenticationToken authentication, UserDetails userDetails) {
        String  username = authentication.getPrincipal().toString();
        String  password = authentication.getCredentials().toString();
        try {
            MainAccessToken accessToken = tyAuthService.getAccessTokenByPasswordMode(username, password);
            MainUserWithRole userInfo = tyAuthService.getUserInfo(accessToken.getAccessToken());
            Field authorities = User.class.getDeclaredField("authorities");
            authorities.setAccessible(true);
            Set<GrantedAuthorityImpl> collect = userInfo.getRoles().stream().map(item -> new GrantedAuthorityImpl(item.getRoleCode().toUpperCase())).collect(Collectors.toSet());
            authorities.set(userDetails,collect);//给userDetail 设置角色权限
        } catch (Exception e) {
            log.info(e.toString());
            return false;
        }
        return true;
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        if (this.userDetailsService == null)
            throw new IllegalArgumentException("UserDetailsService can not be null");
        this.userNotFoundEncodedPassword = this.passwordEncoder.encode("userNotFoundPassword");
    }

    /**
     * 加载用户信息
     *
     * @param username       username
     * @param authentication authentication
     * @return UserDetails
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException, TenantNotFoundException{
        UserDetails loadedUser;
        try {
            // 加载用户信息
            loadedUser = this.userDetailsService.loadUserByIdentifierAndTenantCode(TenantContextHolder.getTenantCode(), authentication.getPrincipal().toString());
        } catch (UsernameNotFoundException notFound) {
            if (authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString();
                passwordEncoder.matches(presentedPassword, userNotFoundEncodedPassword);
            }
            throw notFound;
        } catch (Exception tenantNotFound) {
			throw new InternalAuthenticationServiceException(tenantNotFound.getMessage(), tenantNotFound);
        }
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("get user information failed");
        }
        return loadedUser;
    }
}
