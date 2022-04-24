package com.juyss.common.shiro;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.juyss.common.exception.BusinessException;
import com.juyss.common.exception.code.BaseResponseCode;
import com.juyss.common.utils.Constant;
import com.juyss.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import java.util.Collection;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: CustomRealm
 * @Desc:
 * @package com.juyss.common.shiro
 * @project manager
 * @date 2021/1/21 14:46
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Value("${spring.redis.key.prefix.userToken}")
    private String userTokenPrefix;

    @Lazy
    @Autowired
    private RedisService redisDb;

    /**
     * 执行授权逻辑
     */
    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String sessionInfoStr = redisDb.get(userTokenPrefix + principalCollection.getPrimaryPrincipal());
        if (StringUtils.isEmpty(sessionInfoStr)) {
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        JSONObject redisSession = JSON.parseObject(sessionInfoStr);
        if (redisSession == null) {
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }

        if (redisSession.get(Constant.ROLES_KEY) != null) {
            authorizationInfo.addRoles((Collection<String>) redisSession.get(Constant.ROLES_KEY));
        }
        if (redisSession.get(Constant.PERMISSIONS_KEY) != null) {
            authorizationInfo.addStringPermissions((Collection<String>) redisSession.get(Constant.PERMISSIONS_KEY));
        }
        return authorizationInfo;
    }


    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getPrincipal(), getName());
    }
}