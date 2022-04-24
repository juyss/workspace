package com.github.tangyi.common.security.ty;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.security.constant.SecurityConstant;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.mapper.RbacMapper;
import com.github.tangyi.model.MainRole;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author xh
 * @Description 主系统统一认证 授权码模式
 * @Date 22:37 2020/11/5
 * @Param
 * @return
 **/
@Service
public class TyAuthService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TyAuthProperties tyAuthProperties;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private RbacMapper rbacMapper;

    /**
     * code 换 token
     *
     * @param code
     * @return
     */
    public MainAccessToken getAccessToken(String code) {
        StringBuilder url = new StringBuilder();
        url.append(tyAuthProperties.getTokenUrl()).append("?")
                .append("clientId=").append(tyAuthProperties.getClientId()).append("&")
                .append("grantType=").append(tyAuthProperties.getGrantType()).append("&")
                .append("redirectUri=").append(tyAuthProperties.getRedirectUri()).append("&")
                .append("clientSecret=").append(tyAuthProperties.getClientSecret()).append("&")
                .append("code=").append(code);

        String result = restTemplate.getForObject(url.toString(), String.class);
        logger.info("callback 返回: {}", result);

        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            MainAccessToken accessToken = JsonUtils.toObject(json, MainAccessToken.class);
            accessToken.setCreateTime(System.currentTimeMillis());
            redisTemplate.opsForValue().set(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + accessToken.getAccessToken(), JsonUtils.toString(accessToken), accessToken.getExpiresIn() * 2, TimeUnit.SECONDS);

            return accessToken;
        } else {
            throw new CommonException("统一认证，授权码模式，获取token 失败");
        }
    }

    /**
     * 刷新token
     *
     * @param accessToken
     * @return
     */
    public MainAccessToken refreshToken(MainAccessToken accessToken) {
        StringBuilder url = new StringBuilder();
        url.append(tyAuthProperties.getTokenUrl()).append("?")
                .append("clientId=").append(tyAuthProperties.getClientId()).append("&")
                .append("grantType=refresh_token").append("&")
                .append("refreshToken=").append(accessToken.getRefreshToken()).append("&")
                .append("clientSecret=").append(tyAuthProperties.getClientSecret());
        String result = restTemplate.getForObject(url.toString(), String.class);
        logger.info("统一认证，授权码模式，刷新token: {}", result);

        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            MainAccessToken accessToken2 = JsonUtils.toObject(json, MainAccessToken.class);
            accessToken2.setCreateTime(System.currentTimeMillis());
            redisTemplate.opsForValue().set(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + accessToken.getAccessToken(), JsonUtils.toString(accessToken2), accessToken2.getExpiresIn() * 10, TimeUnit.SECONDS);
            return accessToken2;
        } else {
            throw new CommonException("统一认证，客户端模式，刷新token 失败");
        }
    }

    /**
     * 从redis 缓存中 取出
     *
     * @param token
     * @return
     */
    public MainAccessToken getAccessTokenFromRedis(String token) {
        Object o = redisTemplate.opsForValue().get(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + token);
        if (o == null) return null;
        MainAccessToken accessToken = JsonUtils.toObject(o.toString(), MainAccessToken.class);
        if (accessToken.getCreateTime() + accessToken.getExpiresIn() * 1000 <= System.currentTimeMillis()) {
            accessToken = refreshToken(accessToken);
        }
        return accessToken;
    }

    /**
     * 删除token
     *
     * @param token
     * @return
     */
    public boolean removeAccessToken(String token) {
        logout(token);//  金证统一认证中心无法通过api调用方式 携带token完成登陆
        return redisTemplate.delete(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + token);
    }

    public boolean removeAccessTokenByTgc(String tgc) {
        Set keys = redisTemplate.keys(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + "*");
        for (Object key : keys) {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) continue;
            MainAccessToken accessToken = JsonUtils.toObject(o.toString(), MainAccessToken.class);
            if (StringUtils.equals(accessToken.getTgc(), tgc)) {
                return redisTemplate.delete(key);
            }
        }
        return false;
    }

    /**
     * 退出登陆
     *
     * @param token
     * @return
     */
    public void logout(String token) {
        MainAccessToken mainAccessToken = getAccessTokenFromRedis(token);
        String url = tyAuthProperties.getLogoutUrl() + "?redirectUri=" + tyAuthProperties.getRedirectUri();
        String result = get(url, token, mainAccessToken.getTgc());
        logger.info("登出 url:{} tgc: {} 返回： {}", url, mainAccessToken.getTgc(), result);
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @Cacheable(value = "ty_user_info#300", key = "#token") //缓存5分钟（300秒）
    public MainUserWithRole getUserInfo(String token) {
        String result = get(tyAuthProperties.getGetUserInfoUrl(), token, null);
        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            logger.info("获取统一认证的用户信息：{}", json);
            MainUserWithRole mainUserWithRole = JsonUtils.toObject(json, MainUserWithRole.class);

            String username = MainUserWithRole.IDENTIFIER_PREFIX + mainUserWithRole.getLoginName();
            List<String> roles = rbacMapper.getRolesByUsername(username);
            if (mainUserWithRole.getRoles().size() > 0 || roles.size() > 0) {
                List<String> allRoles = rbacMapper.getAllRoles();
                for (MainRole role : mainUserWithRole.getRoles()) {
                    if (!roles.contains(role.getRoleCode()) && allRoles.contains(role.getRoleCode())) {
                        rbacMapper.addUserRole(username, role.getRoleCode(), IdGen.snowflakeId());
                    }
                }
                List<String> roles2 = mainUserWithRole.getRoles().stream().map(MainRole::getRoleCode).collect(Collectors.toList());
                for (String roleCode : roles) {
                    if (!roles2.contains(roleCode)) {
                        rbacMapper.delUserRole(username, roleCode);
                    }
                }
            }
            return mainUserWithRole;
        } else {
            throw new CommonException("获取用户信息失败");
        }
    }

    private String get(String uri, String token, String tgc) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        if (tgc != null) headers.set("cookie", "tgc=" + tgc + ";_tgc=" + tgc);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

    /**
     * 密码模式获取access_token
     *
     * @param username
     * @param password
     * @return
     */
    public MainAccessToken getAccessTokenByPasswordMode(String username, String password) {
//        StringBuilder url = new StringBuilder();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();

        params.add("clientId", tyAuthProperties.getClientId());
        params.add("clientSecret", tyAuthProperties.getClientSecret());
        params.add("grantType", "password");
        params.add("loginName", username);
        params.add("password", encode(password));
        params.add("loginType4passwordGrant", "1");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

//        url.append("clientId=").append(tyAuthProperties.getClientId()).append("&")
//                .append("clientSecret=").append(tyAuthProperties.getClientSecret()).append("&")
//                .append("grantType=").append("password").append("&")
//                .append("loginName=").append(username).append("&")
//                .append("password=").append(encode(password));

        ResponseEntity<String> exchange = restTemplate.exchange(tyAuthProperties.getTokenUrl(), HttpMethod.POST, requestEntity, String.class);
        String result = exchange.getBody();
        logger.info("callback 返回: {}", result);

        JsonNode jsonNode = JsonUtils.toJsonNode(result);
        if (jsonNode.get("code").asInt() == 200) {
            String json = jsonNode.get("data").toString();
            MainAccessToken accessToken = JsonUtils.toObject(json, MainAccessToken.class);
            accessToken.setCreateTime(System.currentTimeMillis());
            redisTemplate.opsForValue().set(SecurityConstant.TOKEN_REDIS_KEY_PREFIX + accessToken.getAccessToken(), JsonUtils.toString(accessToken), accessToken.getExpiresIn() * 2, TimeUnit.SECONDS);

            return accessToken;
        } else {
            throw new CommonException("统一认证，密码模式，获取token 失败");
        }
    }

    //md5(md5(原密码)+"kingdom") 1加密后为 88200a3d7666ec117affd38f7ff75bda
    private String encode(String password) {
        String s = DigestUtils.md5DigestAsHex(password.getBytes()).toLowerCase();
        return DigestUtils.md5DigestAsHex((s + "kingdom").getBytes()).toLowerCase();
    }
}
